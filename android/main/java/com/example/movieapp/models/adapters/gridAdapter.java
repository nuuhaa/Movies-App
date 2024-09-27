package com.example.movieapp.models.adapters;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp.R;
import com.example.movieapp.models.database_models.Movie;

import java.util.ArrayList;

public class gridAdapter extends BaseAdapter {
    Context context;
    ArrayList<Movie>allMovies;
    LayoutInflater inflater;
public gridAdapter (Context c, ArrayList<Movie> allMovies){
    context=c;
    this.allMovies = allMovies;
}

    @Override
    public int getCount() {
        return allMovies.toArray().length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = allMovies.get(position);
        if (inflater==null)
        { inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView==null){
            convertView=inflater.inflate(R.layout.grid_item,null);
        }
        ImageView imageView=convertView.findViewById(R.id.image_view);
        TextView textView =convertView.findViewById(R.id.text_view);
        imageView.setImageURI(Uri.parse("android.resource://com.example.movieapp/drawable/"+movie.getImage()));
        textView.setText(movie.getName());



        return convertView;
    }
}
