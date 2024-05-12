package com.diploma.hidesk.Controller;

import com.diploma.hidesk.Model.CourseModel.Course;
import com.diploma.hidesk.Model.CourseModel.Exercise;
import com.diploma.hidesk.Model.CourseModel.Lesson;
import com.diploma.hidesk.Model.CourseModel.Test;
import com.diploma.hidesk.Model.DTO.CourseDto;
import com.diploma.hidesk.Model.DTO.ExerciseDto;
import com.diploma.hidesk.Model.DTO.LessonDto;
import com.diploma.hidesk.Service.CourseService.CourseService;
import com.diploma.hidesk.Service.CourseService.ExerciseService;
import com.diploma.hidesk.Service.CourseService.LessonService;
import com.diploma.hidesk.Service.CourseService.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@SessionAttributes
@RequiredArgsConstructor
@RequestMapping("/")
public class TeacherController {
    private final CourseService courseService;
    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    private final TestService testService;

    @GetMapping("myCourse")
    private String getMyCoursePage(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("courses", courseService.findAllByAuthorEmail(user.getUsername()));
        return "teacherPages/myCourse";
    }

    @GetMapping("addCourse")
    public String addCoursePage(@RequestParam(value = "courseId", required = false) Long courseId, Model model){
        if(courseId != null) {
            model.addAttribute("courseInfo", courseService.findCourseById(courseId).get());
        }
        return "teacherPages/addCourse";
    }

    @PostMapping("addCourse")
    public String addCourse(CourseDto courseDto, @AuthenticationPrincipal User user) throws IOException {
        Long id = courseService.addCourse(courseDto, user.getUsername());
        return "redirect:/addLesson?courseId=" + id;
    }

    @GetMapping("addLesson")
    public String addLessonPage(@RequestParam(value = "courseId", required = false) Long courseId, Model model) throws InterruptedException {
        Course courseData = courseService.findCourseById(courseId).get();
        List<Lesson> lessonList = lessonService.getAllByCourseId(courseId);
        model.addAttribute("lesson_list", lessonList);
        model.addAttribute("course", courseData);
        return "teacherPages/addLesson";
    }
    
    @PostMapping("addLesson")
    public String addLesson(LessonDto dto){
            lessonService.addLesson(dto);
            return "redirect:/addLesson?courseId=" + dto.getCourseId();
        }

    @GetMapping("addExercise")
    public String addExercise(@RequestParam(value = "lessonId", required = false) Long lessonId, Model model){
        Lesson lesson = lessonService.getLessonById(lessonId);
        Exercise exercise = exerciseService.getExerciseByLessonId(lessonId);
        model.addAttribute("lesson", lesson);
        model.addAttribute("exercise", exercise);
        return "teacherPages/addExercise";
    }

    @PostMapping("addExercise")
    public String addExercise(ExerciseDto dto) throws IOException {
        exerciseService.addExercise(dto);
        return "redirect:/addLesson?courseId=" + lessonService
                .getLessonById(dto.getLessonId())
                .getCourse()
                .getId();
    }

    @PostMapping("addTest")
    public void addTest(Test test){

    }
}
