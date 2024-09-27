package com.example.movieapp.models.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movieapp.R;
import com.example.movieapp.models.database_models.Movie;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Movie> {


    public ListAdapter(Context context, ArrayList<Movie> movieArrayList) {

        super(context, R.layout.simple_list_item, movieArrayList);

    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Movie movie = getItem(position);

        System.out.println((movie.getType()));
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item, parent, false);

        }

        ImageView movieImage = convertView.findViewById(R.id.movieImage);
        TextView movieName = convertView.findViewById(R.id.movieName);
        TextView movieRate = convertView.findViewById(R.id.MovieRate);
        TextView movieDescription = convertView.findViewById(R.id.description);


        movieImage.setImageURI(Uri.parse("android.resource://com.example.movieapp/drawable/"+movie.getImage()));
        movieName.setText(movie.getName());
        movieRate.setText("Rating(IMDb) ".concat(String.valueOf(movie.getRating())));
        movieDescription.setText(movie.getDescription());

        return convertView;
    }
}