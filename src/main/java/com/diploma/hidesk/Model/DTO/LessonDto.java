package com.diploma.hidesk.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    public Long courseId;
    public List<Long> lesson_id;
    public List<String> lessonName;
}
