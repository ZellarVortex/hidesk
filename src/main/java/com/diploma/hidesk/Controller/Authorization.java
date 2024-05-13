package com.diploma.hidesk.Controller;

import com.diploma.hidesk.Model.DTO.UserDto;
import com.diploma.hidesk.Service.AuthService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class Authorization {
    private final UserService userService;
    @PostMapping("signup")
    public String userRegister(UserDto dto){
        userService.registerUser(dto);
        return "redirect:/main";
    }

}
