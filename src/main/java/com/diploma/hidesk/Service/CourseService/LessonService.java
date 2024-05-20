package com.diploma.hidesk.Service.CourseService;

import com.diploma.hidesk.Model.CourseModel.Course;
import com.diploma.hidesk.Model.CourseModel.Exercise;
import com.diploma.hidesk.Model.CourseModel.Lesson;
import com.diploma.hidesk.Model.DTO.LessonDto;
import com.diploma.hidesk.Repo.CourseRepo.CourseRepo;
import com.diploma.hidesk.Repo.CourseRepo.ExerciseRepo;
import com.diploma.hidesk.Repo.CourseRepo.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepo lessonRepo;
    private final CourseRepo courseRepo;
    private final ExerciseRepo exerciseRepo;

    public void addLesson(LessonDto dto) {
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Курс с ID " + dto.getCourseId() + " не найден"));

        List<String> lessonNames = dto.getLessonName();
        List<Long> lessonIds = dto.getLesson_id();

        lessonNames.removeFirst();

        if (lessonNames == null) {
            throw new IllegalArgumentException("Список названий уроков не должен быть null");
        }

        if (lessonIds == null) {
            lessonIds = new ArrayList<>();
        }

        // Обрабатываем каждое имя урока и соответствующий id
        for (int i = 0; i < lessonNames.size(); i++) {
            String lessonName = lessonNames.get(i);
            Long lessonId = (i < lessonIds.size()) ? lessonIds.get(i) : null;

            if (lessonName != null && !lessonName.isEmpty()) {
                // Добавляем или обновляем урок
                Lesson lesson = new Lesson();
                lesson.setCourse(course);
                if (lessonId != null) {
                    lesson.setId(lessonId);
                }
                lesson.setName(lessonName);
                lessonRepo.save(lesson);
            } else if (lessonId != null) {
                // Удаляем урок и связанное упражнение, если имя урока пустое или null
                try {
                    Exercise exercise = exerciseRepo.findExerciseByLesson_Id(lessonId);
                    if (exercise != null) {
                        exerciseRepo.deleteById(exercise.getId());
                    }
                    lessonRepo.deleteById(lessonId);
                } catch (Exception e) {
                    // Обработка возможных исключений при удалении
                    System.err.println("Ошибка при удалении упражнения или урока: " + e.getMessage());
                }
            }
        }
    }


    public List<Lesson> getAllByCourseId(Long id){
        return lessonRepo.findAllByCourse_Id(id);
    }
    public Lesson getLessonById(Long id){
        return lessonRepo.findById(id).get();
    }
    public List<Lesson> getAll(){
        return lessonRepo.findAll();
    }
}
