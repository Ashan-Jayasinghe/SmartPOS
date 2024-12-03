package com.pos.system.smartpos.service.serviceIMPL;

import com.pos.system.smartpos.dto.requests.UserLoginDTO;
import com.pos.system.smartpos.dto.requests.UserSignUpDTO;
import com.pos.system.smartpos.dto.requests.UserUpdateRequestDTO;
import com.pos.system.smartpos.dto.response.UserLoginResponseDTO;
import com.pos.system.smartpos.dto.response.UserUpdateResponseDTO;
import com.pos.system.smartpos.entity.User;
import com.pos.system.smartpos.exception.NotFoundException;
import com.pos.system.smartpos.repository.UserRepo;
import com.pos.system.smartpos.service.UserService;
import com.pos.system.smartpos.utility.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String addUser(UserSignUpDTO userSignUpDTO) {
        User user = userMapper.signupdtoToEntity(userSignUpDTO);
        boolean existence = userRepo.existsByUserEmail(user.getUserEmail());
        if (existence) {
            return "User already exists";
        }else{
            userRepo.save(user);
            return "User added successfully";
        }
    }

    @Override
    public UserLoginResponseDTO login(UserLoginDTO userLoginDTO) {

        boolean existence = userRepo.existsByUserEmail(userLoginDTO.getUserEmail());
        if (existence) {
            User user =  new User();
            user = userRepo.getUserByUserEmailEquals(userLoginDTO.getUserEmail());
            if(user.getPassword().equals(userLoginDTO.getPassword())){
                UserLoginResponseDTO userLoginResponseDTO= userMapper.userEntityToLoginResponseDTO(user);
                return userLoginResponseDTO;
            }else{
                throw new RuntimeException("Wrong password");
            }

        }else{
            throw new NotFoundException("User does not exist");
        }

    }

    @Override
    public UserLoginResponseDTO getUser(int userId) {
        if(userRepo.existsById(userId)){
            User user = userRepo.getUserByUserIdEquals(userId);
            UserLoginResponseDTO userLoginResponseDTO= userMapper.userEntityToLoginResponseDTO(user);
            return userLoginResponseDTO;
        }else {
            throw new NotFoundException("User does not exist");
        }
    }

    @Override
    public UserUpdateResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        if(userRepo.existsById(userUpdateRequestDTO.getUserId())) {
            User existingUser = new User();
            existingUser = userRepo.getReferenceById(userUpdateRequestDTO.getUserId());
            // Update only non-empty fields
            if (userUpdateRequestDTO.getUserEmail() != null && !userUpdateRequestDTO.getUserEmail().isEmpty()) {
                existingUser.setUserEmail(userUpdateRequestDTO.getUserEmail());
            }
            if (userUpdateRequestDTO.getPassword() != null && !userUpdateRequestDTO.getPassword().isEmpty()) {
                existingUser.setPassword(userUpdateRequestDTO.getPassword());
            }
            if (userUpdateRequestDTO.getUserFirstName() != null && !userUpdateRequestDTO.getUserFirstName().isEmpty()) {
                existingUser.setUserFirstName(userUpdateRequestDTO.getUserFirstName());
            }
            if (userUpdateRequestDTO.getUserLastName() != null && !userUpdateRequestDTO.getUserLastName().isEmpty()) {
                existingUser.setUserLastName(userUpdateRequestDTO.getUserLastName());
            }
            if (userUpdateRequestDTO.getContactNumber() != null && !userUpdateRequestDTO.getContactNumber().isEmpty()) {
                existingUser.setContactNumber(userUpdateRequestDTO.getContactNumber());
            }
            User updatedUser = userRepo.save(existingUser);
            UserUpdateResponseDTO userUpdateResponseDTO= userMapper.userUpdateEntityToUpdateResponseDTO(updatedUser);
            return userUpdateResponseDTO;
        }else{
            throw new NotFoundException("User does not exist");
        }
    }

    @Override
    public String deleteUser(int userId) {
        if(userRepo.existsById(userId)){
            userRepo.deleteById(userId);
            return "User deleted successfully: "+ userId;
        }else{
            throw new NotFoundException("User does not exist");
        }
    }
}

