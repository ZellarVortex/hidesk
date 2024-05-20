package com.diploma.hidesk.Repo.CourseRepo;

import com.diploma.hidesk.Model.CourseModel.Exercise;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
    Exercise findByLesson_Id(Long id);
    Exercise findExerciseByLesson_Id(Long id);
    @Transactional
    void deleteByLessonId(Long lessonId);
}
