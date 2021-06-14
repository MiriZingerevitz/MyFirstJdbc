package com.jb.beans;


import java.time.LocalDate;
import java.util.Date;

public class Person {
    private int id;
    private String name;
    private String city;
    private LocalDate birthday;
    private Hobby hobby;

    public Person() {
    }

    public Person(int id, String name, String city, LocalDate birthday, Hobby hobby) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.birthday = birthday;
        this.hobby = hobby;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", birthday=" + birthday +
                ", hobby=" + hobby +
                '}';
    }
}