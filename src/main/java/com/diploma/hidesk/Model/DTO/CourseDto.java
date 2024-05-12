package com.diploma.hidesk.Model.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private MultipartFile image;
    private String whatIsTaught;
    private String about;
    private String forWho;
    private String requirements;
}
