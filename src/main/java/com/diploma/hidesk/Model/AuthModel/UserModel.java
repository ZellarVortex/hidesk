package com.diploma.hidesk.Model.AuthModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;
    private String password;

    private String name;
    private String surname;
    private String lastname;
    private int age;
    private String number;

    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    public String getFio() {
        return String.format("%s %s. %s.",
                surname,
                name != null && !name.isEmpty() ? name.charAt(0) : "",
                lastname != null && !lastname.isEmpty() ? lastname.charAt(0) : ""
        );
    }
}
