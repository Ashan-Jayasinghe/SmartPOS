package com.pos.system.smartpos.controller;

import com.pos.system.smartpos.dto.requests.UserLoginDTO;
import com.pos.system.smartpos.dto.requests.UserSignUpDTO;
import com.pos.system.smartpos.dto.requests.UserUpdateRequestDTO;
import com.pos.system.smartpos.dto.response.UserLoginResponseDTO;
import com.pos.system.smartpos.dto.response.UserUpdateResponseDTO;
import com.pos.system.smartpos.service.UserService;
import com.pos.system.smartpos.utility.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(
            path = {"/signup"}
    )
    public ResponseEntity<StandardResponse> addUser(@RequestBody UserSignUpDTO userSignUpDTO) {
        String message = userService.addUser(userSignUpDTO);
        StandardResponse standardResponse = new StandardResponse(201, "Success", message);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                standardResponse,
                HttpStatus.CREATED
        );
        return response;
    }

    @PostMapping(
            path = {"/login"}
    )
    public ResponseEntity<StandardResponse> login(@RequestBody UserLoginDTO userLoginDTO) {
        UserLoginResponseDTO message = userService.login(userLoginDTO);
        StandardResponse standardResponse = new StandardResponse(200, "Success", message);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                standardResponse,
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping(
            path = {"/profile"},
            params = "id"
    )
    public ResponseEntity<StandardResponse> getUser(@RequestParam(name = "id") int userId) {
        UserLoginResponseDTO message = userService.getUser(userId);
        StandardResponse standardResponse = new StandardResponse(200, "Success", message);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                standardResponse,
                HttpStatus.OK
        );
        return response;
    }

    @PutMapping(
            path = {"/profile-update"}
    )
    public ResponseEntity<StandardResponse> updateUser(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO){
        UserUpdateResponseDTO message = userService.updateUser(userUpdateRequestDTO);
        StandardResponse standardResponse = new StandardResponse(200, "Success", message);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                standardResponse,
                HttpStatus.OK
        );
        return response;
    }

    @DeleteMapping(
            path = {"/delete"},
            params = "id"
    )
    public ResponseEntity<StandardResponse> deleteUser(@RequestParam(name = "id") int userId) {
        String message = userService.deleteUser(userId);
        StandardResponse standardResponse = new StandardResponse(204, "Success", message);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                standardResponse,
                HttpStatus.NO_CONTENT
        );
        return response;
    }



}

