package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.models.database_models.Favourites;
import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;
import com.example.movieapp.models.database_models.User;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    static User user;
    static Movie pressedMovie;


    public static void setUser(User user) {
        MovieActivity.user = user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);



        TextView titleText= findViewById(R.id.name);
        ImageView imageId= findViewById(R.id.imageView);
        TextView descriptionText= findViewById(R.id.description_text);
        TextView countryText= findViewById(R.id.country_text);
        TextView languageText= findViewById(R.id.language_text);
        TextView classificationText= findViewById(R.id.classifications_text);
        TextView elevationText= findViewById(R.id.elevation_text);
        Button button = findViewById(R.id.button);


        titleText.setText(pressedMovie.getName());
        imageId.setImageURI(Uri.parse("android.resource://com.example.movieapp/drawable/"+pressedMovie.getImage()));
        descriptionText.setText(pressedMovie.getDescription());
        countryText.setText(pressedMovie.getActors());
        languageText.setText(pressedMovie.getPlatForm());
        classificationText.setText(pressedMovie.getType());
        elevationText.setText(String.valueOf(pressedMovie.getRating()).concat("/10"));

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean check = true;

                        ArrayList<Favourites> FavouriteList;
                        ArrayList<Movie> FavouriteMoviesList;
                        ArrayList<Movie> movieArrayList;
                        movieArrayList = new ArrayList<Movie>();
                        MovieDBHelper movieDBHelper =new MovieDBHelper(getApplicationContext());
                        Cursor cursor = movieDBHelper.fetchAllMovies();
                        while (!cursor.isAfterLast()){
                            Movie movie = new Movie(
                                    cursor.getString(1),
                                    Double.parseDouble(cursor.getString(2)),
                                    Double.parseDouble(cursor.getString(5)),
                                    cursor.getString(8),
                                    Integer.parseInt(cursor.getString(6)),
                                    cursor.getString(7),
                                    cursor.getString(3),
                                    cursor.getString(4),
                                    cursor.getString(9),
                                    cursor.getString(10)
                            );
                            movie.setId(cursor.getInt(0));
                            movieArrayList.add(movie);
                            cursor.moveToNext();
                        }
                        Cursor cursor2 = movieDBHelper.fetchAllFavourites();
                        FavouriteList = new ArrayList<>();
                        while (!cursor2.isAfterLast()){
                            Favourites favourites = new Favourites(
                                    cursor2.getInt(1),
                                    cursor2.getInt(0)
                            );
                            System.out.println(favourites.getMovieID());
                            FavouriteList.add(favourites);
                            cursor2.moveToNext();
                        }
                        FavouriteMoviesList = new ArrayList<>();

                        for (int i = 0; i < FavouriteList.toArray().length; i++) {
                            if (FavouriteList.get(i).getUserID() == user.getId()) {
                                for (int j = 0; j < movieArrayList.toArray().length; j++) {
                                    if (FavouriteList.get(i).getMovieID() == movieArrayList.get(j).getId())
                                        FavouriteMoviesList.add(movieArrayList.get(i));
                                }
                            }
                        }


                        for(int i = 0 ; i < FavouriteMoviesList.toArray().length;i++){
                            System.out.println(FavouriteMoviesList.get(i).getId());
                            if(pressedMovie.getId()==FavouriteMoviesList.get(i).getId()){
                                check = false;
                                Toast.makeText(
                                        getApplicationContext(),
                                        "You Have This Movie Already In Favourites",Toast.LENGTH_SHORT

                                ).show();
                                break;
                            }
                        }
                        if(check){

                            movieDBHelper.addFavouriteData(user.getId(),pressedMovie.getId());
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Added To Favourites Successfully",Toast.LENGTH_SHORT

                            ).show();
                        }
                    }
                }
        );

    }


    public static void setPressedMovie(Movie movie){
        pressedMovie = movie;
    }

  /*  public ArrayList<Movie> getUserFavourites() {

        return  FavouriteMoviesList;
    }*/
}