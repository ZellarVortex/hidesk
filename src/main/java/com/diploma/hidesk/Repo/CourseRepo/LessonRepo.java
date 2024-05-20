package com.diploma.hidesk.Repo.CourseRepo;

import com.diploma.hidesk.Model.CourseModel.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByCourse_Id(Long id);
}
