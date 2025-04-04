package org.example;

import java.util.Objects;

public class Human {
    private String surname, name, secondname;
    private int age;

    public Human(String surname, String name, String secondname, int age) {
        this.surname = surname;
        this.name = name;
        this.secondname = secondname;
        this.age = age;
    }

    public Human (Human copy){
        this.surname = copy.surname;
        this.name = copy.name;
        this.secondname = copy.secondname;
        this.age = copy.age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(surname, human.surname) && Objects.equals(name, human.name) && Objects.equals(secondname, human.secondname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, secondname, age);
    }
}
