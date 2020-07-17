package com.example.ibmproject.Data;

import java.util.Map;

public class ClientDetails {
    public String name;
    public String email;
    public String Gender;
    public String Age;

    public ClientDetails(String name, String email,String gender, String age) {
        this.name = name;
        this.email = email;
        Gender = gender;
        Age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
