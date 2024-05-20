package com.diploma.hidesk.Controller;

import com.diploma.hidesk.Service.CourseService.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class SubscribersController {
    private final SubscribeService subscribeService;

    @GetMapping("userSubscriptions")
    public String getAllSubscriptionsCourses(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("courses", subscribeService.getAllSubscriptionsCourse(user.getUsername()));
        return "usersPage/subscriptions";
    }
    @GetMapping("subscribe")
    public String subscribeUser(@RequestParam(value = "courseId", required = false) Long courseId, @AuthenticationPrincipal User user){
        subscribeService.SubscribeUser(user.getUsername(), courseId);
        return "redirect:/coursePage?courseId=" + courseId;
    }
    @GetMapping("unsubscribe")
    public String unsubscribeUser(@RequestParam(value = "courseId", required = false) Long courseId, @AuthenticationPrincipal User user){
        subscribeService.deleteSubscribe(user.getUsername(), courseId);
        return "redirect:/coursePage?courseId=" + courseId;
    }
}
