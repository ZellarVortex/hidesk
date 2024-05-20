package com.diploma.hidesk.Model.CourseModel;

import com.diploma.hidesk.Model.AuthModel.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageName;
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String whatIsTaught;
    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String forWho;
    @Column(length = 1000)
    private String requirements;
    @JoinColumn(name = "user_id")
    @ManyToOne()
    private UserModel author;
}
