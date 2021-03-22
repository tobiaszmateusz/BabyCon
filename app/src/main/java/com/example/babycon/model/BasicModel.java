package com.example.babycon.model;

public class BasicModel {

    String name;
    int age;
    int height;

    public BasicModel(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getname() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() { return height; }
}
