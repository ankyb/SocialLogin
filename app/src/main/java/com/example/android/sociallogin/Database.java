package com.example.android.sociallogin;

public class Database {
    public String name;
    public String colour;
    public String hobby;
    public String age;

    public Database(){

    }

    public Database(String name, String colour, String hobby, String age) {
        this.name = name;
        this.colour = colour;
        this.hobby = hobby;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
