package com.diploma.hidesk.Model.AuthModel;

public enum Role {
    STUDENT("Студент"),
    TEACHER("Учитель"),
    ADMIN("Администратор");

    private final String name;
    Role(String name){this.name = name;}
}
