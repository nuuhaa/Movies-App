package com.example.movieapp.models.database_models;

public class User {
    int id;
    String mail;
    String password;
    int age;
    String gender;
    boolean isAdmin;


    public User(String mail,String password,int age,String gender,boolean isAdmin){
        this.mail = mail;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.isAdmin = isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
