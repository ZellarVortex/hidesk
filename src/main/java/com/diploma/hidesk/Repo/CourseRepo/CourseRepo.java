package com.diploma.hidesk.Repo.CourseRepo;

import com.diploma.hidesk.Model.CourseModel.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findAllByAuthorEmail(String email);
}
