package ru.lukyanov.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Empty name not allowed!")
    @Size(min = 2, max = 30, message = "Your name should be between 2 and 30 chars")
    private String name;
    @Min(value = 18, message = "Your age should be more than 18")
    @Max(value = 100, message = "You are too old for this...")
    private int age;
    @NotEmpty(message = "Empty email not allowed!")
    @Email(message = "Not valid e-mail")
    private String email;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}