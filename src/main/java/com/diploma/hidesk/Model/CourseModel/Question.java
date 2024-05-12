package com.diploma.hidesk.Model.CourseModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ElementCollection
    private List<String> options = new ArrayList<>();

    private int correctOptionIndex;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
}