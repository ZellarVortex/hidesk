package com.diploma.hidesk.Service.CourseService;

import com.diploma.hidesk.Model.CourseModel.Exercise;
import com.diploma.hidesk.Model.DTO.ExerciseDto;
import com.diploma.hidesk.Repo.CourseRepo.ExerciseRepo;
import com.diploma.hidesk.Repo.CourseRepo.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.diploma.hidesk.Global.globalMethods.resizeAndSaveImage;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;
    private final LessonRepo lessonRepo;

    public void addExercise(ExerciseDto exerciseDto) throws IOException {
        Exercise exercise = new Exercise();
        if(exerciseDto.getExerciseId() != null){
            exercise.setId(exerciseDto.getExerciseId());
        }
        try {
            exercise.setImageName(resizeAndSaveImage(exerciseDto.getImageName(), 400, 400));
        } catch (Exception e){
            if(exerciseDto.getExerciseId() != null){
                exercise.setImageName(exerciseRepo.findById(exerciseDto.getExerciseId()).get().getImageName());
            }
        }

        exercise.setName(exerciseDto.getName());
        exercise.setMainContent(exerciseDto.getMainContent());
        exercise.setLesson(lessonRepo.findById(exerciseDto.getLessonId()).get());

        exerciseRepo.save(exercise);
    }

    public Exercise getExerciseByLessonId(Long id){
        return exerciseRepo.findByLesson_Id(id);
    }
    public List<Exercise> getAll(){
        return exerciseRepo.findAll();
    }
}
