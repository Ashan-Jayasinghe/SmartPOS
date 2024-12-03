package com.pos.system.smartpos.entity;

import com.pos.system.smartpos.entity.enums.Role;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@TypeDefs(
        @TypeDef(name = "json", typeClass = JsonType.class)
)
public class User {
    @Id
    @Column(name = "user_id", length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "user_fname", length = 100, nullable = false )
    private String userFirstName;

    @Column(name = "user_lname", length = 100, nullable = false)
    private String userLastName;

    @Column(name = "email", length = 255, nullable = false,unique = true)
    private String userEmail;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role",length = 25, nullable = false)
    private Role role;

    @Type(type = "json")
    @Column(name = "contact_numbers", columnDefinition = "json",length = 10)
    private ArrayList contactNumber;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

}


