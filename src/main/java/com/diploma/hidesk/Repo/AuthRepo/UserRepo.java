package com.diploma.hidesk.Repo.AuthRepo;

import com.diploma.hidesk.Model.AuthModel.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);

}
