package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;
import com.example.movieapp.R;

import java.util.ArrayList;

public class InsertNewFilm extends AppCompatActivity {
    ArrayList<Movie> allMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_film);




        Button insertButton = (Button) findViewById(R.id.insertButton);

        insertButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        allMovies = new ArrayList<Movie>();
                        MovieDBHelper movieDBHelper = new MovieDBHelper(getApplicationContext());
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
                            allMovies.add(movie);
                            cursor.moveToNext();
                        }

                        boolean check = true;
                        String errorMsg="";
                        String movieName = "";
                        String movieImage = "";
                        double movieRating = 0.0;
                        double runningTime = 0.0;
                        int audiences = 0;
                        String platForm = "";
                        String type = "";
                        String description="";
                        String actors="";
                        String year="";

                        if(((TextView) findViewById(R.id.moviename)).getText().toString().equals("")||
                                ((TextView) findViewById(R.id.platForm)).getText().toString().equals("")||
                                ((TextView) findViewById(R.id.type)).getText().toString().equals("")||
                                ((TextView) findViewById(R.id.audiences)).getText().toString().equals("")||
                                ((TextView) findViewById(R.id.runTime)).getText().toString().equals("")||
                                ((TextView) findViewById(R.id.rate)).getText().toString().equals("")||
                                ((TextView) findViewById(R.id.description)).getText().toString().equals("")||
                                ((TextView) findViewById(R.id.yearofproduction)).getText().toString().equals("")||
                                ((TextView) findViewById(R.id.actors)).getText().toString().equals("")
                        ){
                            errorMsg = "You Enter Empty Values , Please Check Your Data again";
                            check = false;
                        }
                        else {

                             movieName = ((TextView) findViewById(R.id.moviename)).getText().toString();
                             movieImage = movieName.replaceAll("\\s", "").toLowerCase();
                             movieRating = Double.parseDouble(((TextView) findViewById(R.id.rate)).getText().toString());
                             runningTime = Double.parseDouble(((TextView) findViewById(R.id.runTime)).getText().toString());
                             audiences = Integer.parseInt(((TextView) findViewById(R.id.audiences)).getText().toString());
                             platForm = ((TextView) findViewById(R.id.platForm)).getText().toString();
                             type = ((TextView) findViewById(R.id.type)).getText().toString();
                             description = ((TextView) findViewById(R.id.description)).getText().toString();
                             actors = ((TextView) findViewById(R.id.actors)).getText().toString();
                             year = ((TextView) findViewById(R.id.yearofproduction)).getText().toString();


                            for (int i = 0; i < allMovies.toArray().length; i++) {
                                if (allMovies.get(i).getName().replaceAll("\\s", "").toLowerCase().equals(movieName.replaceAll("\\s", "").toLowerCase())) {
                                    errorMsg = "This Movie already exist";
                                    check = false;
                                    break;
                                }
                            }
                        }

                        if(check){
                            movieDBHelper.insertNewFilm(
                                    movieName,
                                    String.valueOf(movieRating),
                                    String.valueOf(runningTime),
                                    movieImage,
                                    String.valueOf(audiences),
                                    platForm,
                                    type,
                                    description,
                                    actors,
                                    year
                            );

                            Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),errorMsg,Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }
}