package com.diploma.hidesk.Model.DTO;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserDto {
    @Email
    private String email;
    private String password;
    private String name;
    private String surname;
    private String lastname;
    private int age;
    private String number;
    private String role;
}
