package com.example.movieapp.models.database_models;

public class Favourites {

    int userID;
    int movieID;

    public Favourites(int userID , int movieID){
        this.movieID = movieID;
        this.userID = userID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public int getUserID() {
        return userID;
    }
}
