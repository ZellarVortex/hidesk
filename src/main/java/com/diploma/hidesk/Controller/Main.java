package com.diploma.hidesk.Controller;

import com.diploma.hidesk.Model.AuthModel.UserModel;
import com.diploma.hidesk.Service.AuthService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class Main {
    private final UserService userService;
    @GetMapping("main")
    public String mainPage(@AuthenticationPrincipal User user){
        return "main";
    }
    @GetMapping("profile")
    public String userProfile(@AuthenticationPrincipal User user, Model model){
        UserModel userModel = userService.getUserByName(user.getUsername());
        model.addAttribute("userInfo", userModel);
        return "account/profile";
    }
}
