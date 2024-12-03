package com.pos.system.smartpos.dto.response;

import com.pos.system.smartpos.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginResponseDTO {
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String password;
    private Role role;
    private ArrayList contactNumber;
    private boolean activeState;
}
