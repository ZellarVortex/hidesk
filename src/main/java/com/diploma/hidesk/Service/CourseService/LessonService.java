package com.diploma.hidesk.Service.CourseService;

import com.diploma.hidesk.Model.CourseModel.Course;
import com.diploma.hidesk.Model.CourseModel.Lesson;
import com.diploma.hidesk.Model.DTO.LessonDto;
import com.diploma.hidesk.Repo.CourseRepo.CourseRepo;
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
    public void addLesson(LessonDto dto) {
        Course course = courseRepo.findById(dto.getCourseId()).orElseThrow(() -> new IllegalArgumentException("Курс с ID " + dto.getCourseId() + " не найден"));

        List<String> lessonNames = dto.getLessonName();
        List<Long> lessonIds = dto.getLesson_id();

        if (lessonIds == null) {
            lessonIds = new ArrayList<>();
        }

        for (int i = 0; i < lessonNames.size(); i++) {
            String lessonName = lessonNames.get(i);
            Long lessonId = null;
            if (i < lessonIds.size()) {
                lessonId = lessonIds.get(i);
            }

            if (lessonName != null && !lessonName.isEmpty()) {
                Lesson lesson = new Lesson();
                lesson.setCourse(course);
                if (lessonId != null) {
                    lesson.setId(lessonId);
                }
                lesson.setName(lessonName);
                lessonRepo.save(lesson);
            } else if (lessonId != null) {
                lessonRepo.deleteById(lessonId);
            }
        }
    }

    public List<Lesson> getAllByCourseId(Long id){
        return lessonRepo.findAllByCourse_Id(id);
    }
    public Lesson getLessonById(Long id){
        return lessonRepo.findById(id).get();
    }
}
