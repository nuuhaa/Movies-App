package com.example.movieapp.models.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.models.database_models.Movie;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> movie_id, movie_name, movie_type, movie_rate , movie_description , movie_running_time , movie_audiences , movie_actor ,movie_available  ;
    private  ArrayList<String> movie_image;
//    private String description ;
//    private String running_time ;
//    private String audiences;
//    private String actor ;
//    private String available ;
    Movie movie ;

    public CustomAdapter(Activity activity, Context context, ArrayList<String> movie_id,ArrayList<String> movie_name, ArrayList<String> movie_type, ArrayList<String> movie_rate , ArrayList<String> movie_description , ArrayList<String> movie_running_time , ArrayList<String> movie_audiences ,ArrayList<String> movie_actor , ArrayList<String> movie_available , ArrayList<String> movie_image){
        this.activity = activity;
        this.context = context;
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_type = movie_type;
        this.movie_rate = movie_rate;
        this.movie_description = movie_description ;
        this.movie_running_time = movie_running_time ;
        this.movie_audiences = movie_audiences ;
        this.movie_actor = movie_actor ;
        this.movie_available = movie_available ;
        this.movie_image = movie_image ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        System.out.println(holder.movie_name_txt.getText().toString());
        holder.movie_id_txt.setText(String.valueOf(movie_id.get(position)));
        holder.movie_name_txt.setText(String.valueOf(movie_name.get(position)));
        holder.movie_type_txt.setText(String.valueOf(movie_type.get(position)));
        holder.movie_rate_txt.setText(String.valueOf(movie_rate.get(position)));
        holder.movie_description_txt.setText(String.valueOf(movie_description.get(position)));

        holder.movie_running_time_txt.setText(String.valueOf(movie_running_time.get(position)));
        holder.movie_audiences_txt.setText(String.valueOf(movie_audiences.get(position)));
        holder.movie_actor_txt.setText(String.valueOf(movie_actor.get(position)));
        holder.movie_available_txt.setText(String.valueOf(movie_available.get(position)));
        holder.movie_image_txt.setImageURI(Uri.parse("android.resource://com.example.movieapp/drawable/"+ movie_image.get(position)));


        //Recyclerview onClickListener
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, UpdateActivity.class);
//                intent.putExtra("id", String.valueOf(book_id.get(position)));
//                intent.putExtra("title", String.valueOf(book_title.get(position)));
//                intent.putExtra("author", String.valueOf(book_author.get(position)));
//                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movie_id_txt,movie_name_txt, movie_type_txt, movie_rate_txt , movie_description_txt , movie_running_time_txt , movie_audiences_txt , movie_actor_txt , movie_available_txt;
        ImageView movie_image_txt ;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
           movie_id_txt = itemView.findViewById(R.id.movie_id_txt);
            movie_type_txt= itemView.findViewById(R.id.movie_type_txt);
            movie_name_txt = itemView.findViewById(R.id.movies_name_txt);
            movie_rate_txt = itemView.findViewById(R.id.movie_rate_txt);
            movie_description_txt =  itemView.findViewById(R.id.movie_description_txt);
            movie_running_time_txt =  itemView.findViewById(R.id.movie_running_time_txt);
            movie_audiences_txt =  itemView.findViewById(R.id.movie_audiences_txt);
            movie_actor_txt =  itemView.findViewById(R.id.movie_actor_txt);
            movie_available_txt =  itemView.findViewById(R.id.movie_available_txt);
            movie_image_txt = itemView.findViewById(R.id.movie_image_txt);


            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}

