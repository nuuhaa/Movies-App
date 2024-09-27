package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.movieapp.models.adapters.ListAdapter;
import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;
import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityShowingDataBinding;

import java.util.ArrayList;

public class ShowingData extends AppCompatActivity {

    ActivityShowingDataBinding binding;

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_data);

        Intent i = new Intent(this, UpdateMovieData.class);

        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        MovieDBHelper movieDBHelper =new MovieDBHelper(getApplicationContext());
        binding = ActivityShowingDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
            System.out.println(movie.getId());
            movieArrayList.add(movie);
            cursor.moveToNext();
        }
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),movieArrayList);
        binding.MoviesList.setAdapter(listAdapter);
        binding.MoviesList.setClickable(true);
        binding.MoviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UpdateMovieData.setPressedMovie(movieArrayList.get(position));
                startActivity(i);
                finish();
            }
        });

        SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
                pullToRefresh.setRefreshing(false);
            }
        });
    }
}