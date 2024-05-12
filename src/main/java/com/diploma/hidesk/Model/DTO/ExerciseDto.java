package com.diploma.hidesk.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ExerciseDto {
    private Long exerciseId;
    private Long lessonId;
    private String name;
    private MultipartFile imageName;
    private String mainContent;
}
