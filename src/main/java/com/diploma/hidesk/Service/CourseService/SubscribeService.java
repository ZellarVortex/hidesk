package com.diploma.hidesk.Service.CourseService;

import com.diploma.hidesk.Model.AuthModel.UserModel;
import com.diploma.hidesk.Model.CourseModel.Course;
import com.diploma.hidesk.Model.RelatedModel.UserSubscribe;
import com.diploma.hidesk.Repo.AuthRepo.UserRepo;
import com.diploma.hidesk.Repo.CourseRepo.CourseRepo;
import com.diploma.hidesk.Repo.RelatedRepo.UserSubscribeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final UserSubscribeRepo userSubscribeRepo;
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;

    public void SubscribeUser(String email, Long courseId){
        UserModel userModel = userRepo.findByEmail(email);
        Course courseModel = courseRepo.findById(courseId).get();

        UserSubscribe userSubscribe = new UserSubscribe();
        userSubscribe.setUserModel(userModel);
        userSubscribe.setCourse(courseModel);

        userSubscribeRepo.save(userSubscribe);
    }
    public boolean checkSubscribe(String email, Long id){
        return userSubscribeRepo.findByUserModel_EmailAndCourse_Id(email, id) != null;
    }

    public void deleteSubscribe(String email, Long id){
        UserSubscribe userSubscribe = userSubscribeRepo.findByUserModel_EmailAndCourse_Id(email, id);
        userSubscribeRepo.deleteById(userSubscribe.getId());
    }

    public List<Course> getAllSubscriptionsCourse(String email){
        List<UserSubscribe> userSubscribes = userSubscribeRepo.findByUserModel_Id(userRepo
                        .findByEmail(email)
                        .getId());
        return userSubscribes.stream()
                .map(UserSubscribe::getCourse)
                .toList();
    }
}
