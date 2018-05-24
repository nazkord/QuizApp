package com.example.nazar.myfirstapplication.entities;

/**
 * Created by nazar on 16.03.2017.
 */

public class Person {

    private int id;
    private String name;
    private int age;
    private EyeColor eyeColor;
    private Temperament temperament;

    public Person(String name, int age, EyeColor eyeColor, Temperament temperament) {
        this.name = name;
        this.age = age;
        this.eyeColor = eyeColor;
        this.temperament = temperament;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Temperament getTemperament() {
        return temperament;
    }

    public void setTemperament(Temperament temperament) {
        this.temperament = temperament;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
