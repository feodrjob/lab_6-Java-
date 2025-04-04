package org.example;

public class Student extends Human{
    private String faculty;

    public Student(String surname, String name, String secondname, int age, String faculty) {
        super(surname, name, secondname, age);
        this.faculty = faculty;
    }


}
