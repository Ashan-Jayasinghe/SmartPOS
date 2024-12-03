package com.pos.system.smartpos.utility.mappers;


import com.pos.system.smartpos.dto.requests.UserLoginDTO;
import com.pos.system.smartpos.dto.requests.UserSignUpDTO;
import com.pos.system.smartpos.dto.requests.UserUpdateRequestDTO;
import com.pos.system.smartpos.dto.response.UserLoginResponseDTO;
import com.pos.system.smartpos.dto.response.UserUpdateResponseDTO;
import com.pos.system.smartpos.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //UserSignUpDTO --> User
    User signupdtoToEntity(UserSignUpDTO userSignUpDTO);
    UserLoginResponseDTO userEntityToLoginResponseDTO(User user);
    UserUpdateResponseDTO userUpdateEntityToUpdateResponseDTO(User updatedUser);
}
