package com.diploma.hidesk.Service.AdminService;

import com.diploma.hidesk.Model.AuthModel.Role;
import com.diploma.hidesk.Model.AuthModel.UserModel;
import com.diploma.hidesk.Model.CourseModel.Course;
import com.diploma.hidesk.Model.CourseModel.Lesson;
import com.diploma.hidesk.Repo.AuthRepo.UserRepo;
import com.diploma.hidesk.Repo.CourseRepo.CourseRepo;
import com.diploma.hidesk.Repo.CourseRepo.ExerciseRepo;
import com.diploma.hidesk.Repo.CourseRepo.LessonRepo;
import com.diploma.hidesk.Repo.RelatedRepo.UserSubscribeRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepo userRepo;
    private final LessonRepo lessonRepo;
    private final CourseRepo courseRepo;
    private final ExerciseRepo exerciseRepo;
    private final UserSubscribeRepo userSubscribeRepo;
    @Transactional
    public void deleteUser(Long id){
        List<Course> courseList = courseRepo.findAllByAuthorEmail(userRepo.findById(id).get().getEmail());
        for (Course course : courseList){
            deleteCourse(course.getId());
        }
        userRepo.deleteById(id);
    }

    public void appointTeacher(Long id) {
        Optional<UserModel> userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            UserModel userModel = userOpt.get();
            Set<Role> roles = userModel.getRole();

            if (roles.contains(Role.STUDENT)) {
                roles.remove(Role.STUDENT);
                roles.add(Role.TEACHER);
            } else if (roles.contains(Role.TEACHER)) {
                roles.remove(Role.TEACHER);
                roles.add(Role.STUDENT);
            }
            userModel.setRole(roles);
            userRepo.save(userModel);
        }
    }

    public void appointAdmin(Long id) {
        Optional<UserModel> userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            UserModel userModel = userOpt.get();
            Set<Role> roles = userModel.getRole();

            if (roles.contains(Role.ADMIN)) {
                roles.remove(Role.ADMIN);
            } else {
                roles.add(Role.ADMIN);
            }

            userModel.setRole(roles);
            userRepo.save(userModel);
        }
    }


    @Transactional
    public void deleteCourse(Long id) {
        if (!courseRepo.existsById(id)) {
            throw new EntityNotFoundException("Course with id " + id + " not found");
        }

        List<Lesson> lessons = lessonRepo.findAllByCourse_Id(id);
        for (Lesson lesson : lessons) {
            exerciseRepo.deleteByLessonId(lesson.getId());
        }
        userSubscribeRepo.deleteByCourse_Id(id);
        lessonRepo.deleteAll(lessons);
        courseRepo.deleteById(id);
    }

    @Transactional
    public void deleteLesson(Long id) {
        if (!lessonRepo.existsById(id)) {
            throw new EntityNotFoundException("Lesson with id " + id + " not found");
        }

        exerciseRepo.deleteByLessonId(id);
        lessonRepo.deleteById(id);
    }

    @Transactional
    public void deleteExercise(Long id) {
        if (!exerciseRepo.existsById(id)) {
            throw new EntityNotFoundException("Exercise with id " + id + " not found");
        }

        exerciseRepo.deleteById(id);
    }
}

