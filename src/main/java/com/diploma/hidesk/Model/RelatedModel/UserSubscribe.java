package com.diploma.hidesk.Model.RelatedModel;

import com.diploma.hidesk.Model.AuthModel.UserModel;
import com.diploma.hidesk.Model.CourseModel.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserSubscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserModel userModel;
    @ManyToOne
    private Course course;
}
