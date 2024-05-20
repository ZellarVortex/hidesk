package com.diploma.hidesk.Controller;

import com.diploma.hidesk.Model.AuthModel.UserModel;
import com.diploma.hidesk.Model.CourseModel.Course;
import com.diploma.hidesk.Model.CourseModel.Exercise;
import com.diploma.hidesk.Model.CourseModel.Lesson;
import com.diploma.hidesk.Service.AuthService.UserService;
import com.diploma.hidesk.Service.CourseService.CourseService;
import com.diploma.hidesk.Service.CourseService.ExerciseService;
import com.diploma.hidesk.Service.CourseService.LessonService;
import com.diploma.hidesk.Service.CourseService.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class Main {
    private final UserService userService;
    private final CourseService courseService;
    private final SubscribeService subscribeService;
    private final LessonService lessonService;
    private final ExerciseService exerciseService;
    @GetMapping("profile")
    public String userProfile(@AuthenticationPrincipal User user, Model model){
        UserModel userModel = userService.getUserByName(user.getUsername());
        model.addAttribute("userInfo", userModel);
        return "account/profile";
    }
    @GetMapping("allCourse")
    public String getAllCoursePage(@RequestParam(value = "courseName", required = false) String courseName, Model model) {
        if (courseName != null && !courseName.isEmpty()) {
            List<Course> courses = courseService.findAllByName(courseName);
            model.addAttribute("courses", courses);
        } else {
            List<Course> allCourses = courseService.findAll();
            model.addAttribute("courses", allCourses);
        }
        return "usersPage/allCourses";
    }

    @GetMapping("coursePage")
    public String addCoursePage(@RequestParam(value = "courseId", required = false) Long courseId, Model model, @AuthenticationPrincipal User user){
            model.addAttribute("subscribe", subscribeService.checkSubscribe(user.getUsername(), courseId));
            model.addAttribute("courseInfo", courseService.findCourseById(courseId).get());
        return "usersPage/coursePage";
    }

    @GetMapping("lessonPage")
    public String lessonPage(@RequestParam(value = "courseId", required = false) Long courseId, Model model) throws InterruptedException {
        Course courseData = courseService.findCourseById(courseId).get();
        List<Lesson> lessonList = lessonService.getAllByCourseId(courseId);
        model.addAttribute("lesson_list", lessonList);
        model.addAttribute("course", courseData);
        return "usersPage/lessonsPage";
    }

    @GetMapping("exercisePage")
    public String exercisePage(@RequestParam(value = "lessonId", required = false) Long lessonId, Model model){
        Lesson lesson = lessonService.getLessonById(lessonId);
        Exercise exercise = exerciseService.getExerciseByLessonId(lessonId);
        if(exercise == null){
            return "redirect:/404";
        } else {
            model.addAttribute("lesson", lesson);
            model.addAttribute("exercise", exercise);
            return "usersPage/exercisePage";
        }
    }
}
