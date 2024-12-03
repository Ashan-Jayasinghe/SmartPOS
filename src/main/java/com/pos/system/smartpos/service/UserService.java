package com.pos.system.smartpos.service;

import com.pos.system.smartpos.dto.requests.UserLoginDTO;
import com.pos.system.smartpos.dto.requests.UserSignUpDTO;
import com.pos.system.smartpos.dto.requests.UserUpdateRequestDTO;
import com.pos.system.smartpos.dto.response.UserLoginResponseDTO;
import com.pos.system.smartpos.dto.response.UserUpdateResponseDTO;

public interface UserService {
    public String addUser(UserSignUpDTO userSignUpDTO);

    public UserLoginResponseDTO login(UserLoginDTO userLoginDTO);

    public UserLoginResponseDTO getUser(int userId);

    public UserUpdateResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO);

    public String deleteUser(int userId);
}
