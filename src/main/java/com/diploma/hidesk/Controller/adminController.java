package com.diploma.hidesk.Controller;

import com.diploma.hidesk.Service.AdminService.AdminService;
import com.diploma.hidesk.Service.AuthService.UserService;
import com.diploma.hidesk.Service.CourseService.CourseService;
import com.diploma.hidesk.Service.CourseService.ExerciseService;
import com.diploma.hidesk.Service.CourseService.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/")
public class adminController {
    private final UserService userService;
    private final CourseService courseService;
    private final LessonService lessonService;
    private final ExerciseService exerciseService;
    private final AdminService adminService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("adminPage")
    public String adminPage(Model model){
        model.addAttribute("userList", userService.getAllUser());
        model.addAttribute("courseList", courseService.findAll());
        model.addAttribute("lessonList", lessonService.getAll());
        model.addAttribute("exerciseList", exerciseService.getAll());
        return "admin/adminPage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("deleteUser")
    public String deleteUser(@RequestParam(value = "userId", required = false) Long userId){
        adminService.deleteUser(userId);
        return "redirect:/admin/adminPage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("deleteCourse")
    public String deleteCourse(@RequestParam(value = "courseId", required = false) Long courseId){
        adminService.deleteCourse(courseId);
        return "redirect:/admin/adminPage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("deleteLesson")
    public String deleteLesson(@RequestParam(value = "lessonId", required = false) Long lessonId){
        adminService.deleteLesson(lessonId);
        return "redirect:/admin/adminPage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("deleteExercise")
    public String deleteExercise(@RequestParam(value = "exerciseId", required = false) Long exerciseId){
        adminService.deleteExercise(exerciseId);
        return "redirect:/admin/adminPage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("appointRoleAdmin")
    public String appointRoleAdmin(@RequestParam(value = "userId", required = false) Long userId){
        adminService.appointAdmin(userId);
        return "redirect:/admin/adminPage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("appointRoleTeacher")
    public String appointRoleTeacher(@RequestParam(value = "userId", required = false) Long userId){
        adminService.appointTeacher(userId);
        return "redirect:/admin/adminPage";
    }

}
