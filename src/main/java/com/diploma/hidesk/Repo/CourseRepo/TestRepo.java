package com.diploma.hidesk.Repo.CourseRepo;

import com.diploma.hidesk.Model.CourseModel.Course;
import com.diploma.hidesk.Model.CourseModel.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test, Long> {
}
