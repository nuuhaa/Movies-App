package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;

public class UpdateMovieData extends AppCompatActivity {

    MovieDBHelper movieDBHelper;
    static Movie PressedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie_data);
        setData();

        Button deleteButton = (Button) findViewById(R.id.DeleteButton);
        Button updateButton = (Button) findViewById(R.id.UpdateButton);


        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieDBHelper = new MovieDBHelper(getApplicationContext());
                        boolean check = true;

                        String rating = ((TextView) findViewById(R.id.rate)).getText().toString();
                        String audiences = ((TextView) findViewById(R.id.audiences)).getText().toString();
                        String platForm = ((TextView) findViewById(R.id.platForm)).getText().toString();
                        String description = ((TextView) findViewById(R.id.description)).getText().toString();

                        if(rating.equals("")||
                           audiences.equals("")||
                           platForm.equals("")||
                           description.equals("")
                        ){
                            check = false;
                        }

                        if(check){
                            movieDBHelper.UpdateMovieData(PressedMovie.getId(),PressedMovie.getName(),rating,String.valueOf(PressedMovie.getRunningTime()),PressedMovie.getImage(),audiences,platForm,PressedMovie.getType(),description,PressedMovie.getActors(),PressedMovie.getYear());
                            Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"You Enter Empty Values , Please Check Your Data again",Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieDBHelper = new MovieDBHelper(getApplicationContext());
                        movieDBHelper.DeleteMovieData(PressedMovie.getId());
                        Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_LONG).show();
                        finish();

                    }
                }
        );

    }

    public static void setPressedMovie(Movie pressedMovie) {
        PressedMovie = pressedMovie;
    }

    public void setData(){
        ((TextView) findViewById(R.id.moviename)).setText(PressedMovie.getName());
        ((ImageView) findViewById(R.id.movieImage)).setImageURI(Uri.parse("android.resource://com.example.movieapp/drawable/"+PressedMovie.getImage()));
        ((TextView) findViewById(R.id.rate)).setText(String.valueOf(PressedMovie.getRating()));
        ((TextView) findViewById(R.id.audiences)).setText(String.valueOf(PressedMovie.getAudiences()));
        ((TextView) findViewById(R.id.platForm)).setText(PressedMovie.getPlatForm());
        ((TextView) findViewById(R.id.description)).setText(PressedMovie.getDescription());
        ((TextView) findViewById(R.id.yearofproduction)).setText(PressedMovie.getYear());
    }
}