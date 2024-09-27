package com.example.movieapp.models.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movieapp.R;
import com.example.movieapp.activities.FavouritesActivity;
import com.example.movieapp.models.database_models.Favourites;
import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;

import java.util.ArrayList;

public class FavoritesListAdapter extends ArrayAdapter<Movie> {


    Context context;
    ArrayList<Movie>movies;

    public FavoritesListAdapter(Context context, ArrayList<Movie> favoriteArrayList){
        super(context, R.layout.favorites_list_item,favoriteArrayList);
        this.movies = favoriteArrayList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Movie movie=getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.favorites_list_item,parent,false);
        }



        ImageView imageView = convertView.findViewById(R.id.movieImage);
        TextView movieName = convertView.findViewById(R.id.movieName);
        TextView movieRating = convertView.findViewById(R.id.description);
        ImageButton favoriteIcon = convertView.findViewById(R.id.favoriteButton);



        imageView.setImageURI(Uri.parse("android.resource://com.example.movieapp/drawable/"+movie.getImage()));
        movieName.setText(movie.getName());
        movieRating.setText(movie.getDescription());
        favoriteIcon.setImageResource(R.drawable.red_heart);


        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDBHelper movieDBHelper = new MovieDBHelper(getContext());
                movieDBHelper.deleteFavouriteData(movie.getId(), FavouritesActivity.getUser().getId());
                remove(movie);


            }
        });
        return convertView;
    }
}
