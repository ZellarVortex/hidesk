package com.diploma.hidesk.Service.CourseService;

import com.diploma.hidesk.Model.AuthModel.UserModel;
import com.diploma.hidesk.Model.CourseModel.Course;
import com.diploma.hidesk.Model.CourseModel.Exercise;
import com.diploma.hidesk.Model.CourseModel.Lesson;
import com.diploma.hidesk.Model.DTO.CourseDto;
import com.diploma.hidesk.Repo.AuthRepo.UserRepo;
import com.diploma.hidesk.Repo.CourseRepo.CourseRepo;
import com.diploma.hidesk.Repo.CourseRepo.ExerciseRepo;
import com.diploma.hidesk.Repo.CourseRepo.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.diploma.hidesk.Global.globalMethods.deletePhoto;
import static com.diploma.hidesk.Global.globalMethods.resizeAndSaveImage;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final LessonRepo lessonRepo;
    private final ExerciseRepo exerciseRepo;
    public Long addCourse(CourseDto dto, String email) throws IOException {
        UserModel user = userRepo.findByEmail(email);
        Course course = new Course();

        course.setName(dto.getName());
        course.setAbout(dto.getAbout());
        course.setForWho(dto.getForWho());
        course.setRequirements(dto.getRequirements());
        course.setDescription(dto.getDescription());
        course.setWhatIsTaught(dto.getWhatIsTaught());
        course.setAuthor(user);

        try {
            course.setImageName(resizeAndSaveImage(dto.getImage(), 300, 300));
        } catch (Exception e){
            if (dto.getId() != null) {
                course.setImageName(courseRepo.findById(dto.getId()).get().getImageName());
            }
        }
        if (dto.getId() != null) {
            course.setId(dto.getId());
        }
        courseRepo.save(course);
        return course.getId();
    }

    public void deleteCourse(Long courseId, User user) throws IOException {
        UserModel userModel = userRepo.findByEmail(user.getUsername());
        Course course = courseRepo.findById(courseId).get();

        if(Objects.equals(userModel.getId(), course.getAuthor().getId())){
            List<Lesson> lessonList = lessonRepo.findAllByCourse_Id(courseId);

            for(Lesson lesson : lessonList){
                Exercise temp = exerciseRepo.findExerciseByLesson_Id(lesson.getId());
                if(temp != null){
                    deletePhoto(temp.getImageName());
                    exerciseRepo.deleteById(temp.getId());
                }
                lessonRepo.deleteById(lesson.getId());
            }
        deletePhoto(course.getImageName());
        courseRepo.deleteById(courseId);
        }
    }

    public Optional<Course> findCourseById(Long id){
        return courseRepo.findById(id);
    }

    public List<Course> findAllByAuthorEmail(String email){
        return courseRepo.findAllByAuthorEmail(email);
    }
    public List<Course> findAll(){
        return courseRepo.findAll();
    }
    public List<Course> findAllByName(String keyword){
        return courseRepo.findByNameContainingIgnoreCase(keyword);
    }

}
