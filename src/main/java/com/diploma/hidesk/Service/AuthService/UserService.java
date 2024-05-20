package com.diploma.hidesk.Service.AuthService;

import com.diploma.hidesk.Model.AuthModel.Role;
import com.diploma.hidesk.Model.AuthModel.UserModel;
import com.diploma.hidesk.Model.DTO.UserDto;
import com.diploma.hidesk.Repo.AuthRepo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.diploma.hidesk.Configuration.SecurityConfig.passwordEncoder;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;
    public void registerUser(UserDto dto){
            UserModel user = new UserModel();
            user.setName(dto.getName());
            user.setLastname(dto.getLastname());
            user.setSurname(dto.getSurname());
            user.setAge(dto.getAge());
            user.setEmail(dto.getEmail());
            user.setNumber(dto.getNumber());
            user.setPassword(passwordEncoder().encode(dto.getPassword()));
            if (dto.getRole().equals("Teacher")) {
                user.setRole(Collections.singleton(Role.ADMIN));
            } else if (dto.getRole().equals("Student")) {
                user.setRole(Collections.singleton(Role.STUDENT));
            }
            repo.save(user);
    }
    public UserModel getUserByName(String name){
        return repo.findByEmail(name);
    }
    public List<UserModel> getAllUser(){
        return repo.findAll();
    }
}
