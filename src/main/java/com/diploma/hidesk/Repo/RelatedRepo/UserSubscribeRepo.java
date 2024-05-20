package com.diploma.hidesk.Repo.RelatedRepo;

import com.diploma.hidesk.Model.RelatedModel.UserSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscribeRepo extends JpaRepository<UserSubscribe, Long> {
    UserSubscribe findByUserModel_EmailAndCourse_Id(String email, Long id);
    List<UserSubscribe> findByUserModel_Id(Long id);
    void deleteByCourse_Id(Long id);
}
