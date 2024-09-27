package com.example.movieapp.models.database_models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {
    private static final String dbName = "MovieDB";
    SQLiteDatabase movieDB;


    public MovieDBHelper(Context context){
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE movies(filmID integer primary key autoincrement,"+"name text not null , rating text not null , runningTime text not null ,image text not null,platForm text not null,audiences text not null , type text not null,description text not null , actors text not null , year text not null)"
        );
        db.execSQL("CREATE TABLE users(Uid integer primary key autoincrement,mail text not null , password text not null,age integer not null, admin boolean not null ,gender text not null)");
        db.execSQL("Create TABLE favourites(filmID integer REFERENCES movies(filmID) , UserID integer REFERENCES users(Uid))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertNewFilm(String name, String rating , String runningTime , String image , String audiences , String platForm , String type , String description ,String actors,String year){
        ContentValues row = new ContentValues();
        row.put("name",name);
        row.put("rating",rating);
        row.put("type",type);
        row.put("description",description);
        row.put("actors",actors);
        row.put("runningTime",runningTime);
        row.put("audiences",audiences);
        row.put("platForm",platForm);
        row.put("year",year);
        row.put("image",image);
        movieDB = getWritableDatabase();
        movieDB.insert("movies",null,row);
        movieDB.close();
    }

    public void UpdateMovieData(int id,String name, String rating , String runningTime , String image , String audiences , String platForm , String type , String description ,String actors,String year){
        movieDB = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("name",name);
        row.put("rating",rating);
        row.put("type",type);
        row.put("description",description);
        row.put("actors",actors);
        row.put("runningTime",runningTime);
        row.put("audiences",audiences);
        row.put("platForm",platForm);
        row.put("year",year);
        row.put("image",image);

        movieDB.update("movies",row,"filmID="+id,null);
        movieDB.close();
    }

    public void DeleteMovieData(int id){
            movieDB = getWritableDatabase();
            movieDB.delete("movies","filmID="+id,null);
            movieDB.close();
    }

    public void addUserData(String mail,String password,int age,String gender,boolean isAdmin){
        ContentValues row = new ContentValues();
        row.put("mail",mail);
        row.put("name"," ");
        row.put("password",password);
        row.put("age",age);
        row.put("admin",isAdmin);
        row.put("gender",gender);
        movieDB = getWritableDatabase();
        movieDB.insert("users",null,row);
        movieDB.close();
    }

    public void addFavouriteData(int UserID,int MovieID){
        ContentValues row = new ContentValues();
        row.put("filmID",MovieID);
        row.put("UserID",UserID);
        movieDB = getWritableDatabase();
        movieDB.insert("favourites",null,row);
        movieDB.close();
    }

    public void deleteFavouriteData(int FilmID, int UserID){
        movieDB = getWritableDatabase();
        movieDB.delete("favourites","filmID="+FilmID+" AND "+"UserID = "+UserID,null);
        movieDB.close();
    }

    public Cursor readAllDataCategory(String type){
        movieDB = getReadableDatabase();
        String[] selections = {type , "7"};
        String[]rowDetails = {"filmID","name","rating","type","description","runningTime","audiences","platForm","image","actors","year"};
        Cursor cursor = movieDB.query("movies",rowDetails,"type = ? AND rating > ?",selections,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        movieDB.close();
        return cursor;
    }

    public Cursor fetchAllUsers(){
        movieDB = getReadableDatabase();
        String[]rowDetails = {"Uid","mail","password","age","admin","gender"};
        Cursor cursor = movieDB.query("users",rowDetails,null,null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        movieDB.close();
        return cursor;

    }

    public Cursor fetchAllMovies(){
        movieDB = getReadableDatabase();
        String[]rowDetails = {"filmID","name","rating","type","description","runningTime","audiences","platForm","image","actors","year"};
        Cursor cursor = movieDB.query("movies",rowDetails,null,null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        movieDB.close();
        return cursor;
    }

    public Cursor fetchAllFavourites(){
        movieDB = getReadableDatabase();
        String[]rowDetails = {"filmID","UserID"};
        Cursor cursor = movieDB.query("favourites",rowDetails,null,null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        movieDB.close();
        return cursor;
    }
}
