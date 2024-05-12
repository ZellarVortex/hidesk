package com.diploma.hidesk.Model.CourseModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageName;
    @Column(length = 1000)
    private String mainContent;
    @JoinColumn(name = "lesson_id")
    @ManyToOne
    private Lesson lesson;
}
