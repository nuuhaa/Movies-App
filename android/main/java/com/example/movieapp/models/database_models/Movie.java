package com.example.movieapp.models.database_models;

import java.util.ArrayList;

public class Movie {
    private int id;
    private String name;
    private double rating;
    private double runningTime;
    private String image;
    private int audiences;
    private String platForm;
    private String type;
    private String year;
    private String description;
    private ArrayList<String> actors;

    public Movie(){}

    public Movie(String name, double rating , double runningTime , String image , int audiences , String platForm, String type , String description , String actors , String year){
        this.name = name;
        this.rating = rating;
        this.runningTime = runningTime;
        this.image = image;
        this.audiences = audiences;
        this.platForm = platForm;
        this.type = type;
        this.description = description;
        this.actors = new ArrayList<String>();
        String[] parts = actors.split(",");
        for(int i = 0 ; i<parts.length;i++)
            this.actors.add(parts[i]);
        this.year = year;

    }
/*setters and getters*/

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }
    public String getActors() {
        String values = "";
        for(int i = 0 ; i< this.actors.toArray().length;i++){
            values = values.concat(this.actors.get(i).concat(","));
        }
        return values;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAudiences(int audiences) {
        this.audiences = audiences;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setRunningTime(double runningTime) {
        this.runningTime = runningTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getAudiences() {
        return audiences;
    }

    public String getImage() {
        return image;
    }

    public String getPlatForm() {
        return platForm;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public double getRunningTime() {
        return runningTime;
    }

}
