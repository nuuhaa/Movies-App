package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.movieapp.models.adapters.FavoritesListAdapter;
import com.example.movieapp.models.database_models.Favourites;
import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;
import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityFavouritesBinding;
import com.example.movieapp.models.database_models.User;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    ActivityFavouritesBinding binding;
    MeowBottomNavigation bottomNavigation;
    TextView FavouriteText;
    static User user;

    public static void setUser(User u) {
        user = u;
    }

    public static User getUser() {
        return user;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        binding = ActivityFavouritesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        MyDatabaseHelper db= new MyDatabaseHelper(FavoritesActivity.this);
//
//
//        db.addNewMovie("Trying ","Rating 4.5",22,"Audiences","runningTime","Netflex");


        int[] imageId={R.drawable.home_icon,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,R.drawable.home_icon,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,R.drawable.home_icon,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,R.drawable.home_icon,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground};

        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        MovieDBHelper movieDBHelper =new MovieDBHelper(getApplicationContext());
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
            movieArrayList.add(movie);
            cursor.moveToNext();
        }
        Cursor cursor2 = movieDBHelper.fetchAllFavourites();
        ArrayList<Favourites> FavouriteList = new ArrayList<>();
        while (!cursor2.isAfterLast()){
            Favourites movie = new Favourites(
                    cursor2.getInt(1),
                   cursor2.getInt(0)
            );
            FavouriteList.add(movie);
            cursor2.moveToNext();
        }

        ArrayList<Movie> FavouriteMoviesList = new ArrayList<>();

        for(int i = 0 ; i < FavouriteList.toArray().length;i++){
            if(FavouriteList.get(i).getUserID() == user.getId()){
                for(int j = 0 ; j < movieArrayList.toArray().length;j++){
                    if(FavouriteList.get(i).getMovieID() == movieArrayList.get(j).getId())
                        FavouriteMoviesList.add(movieArrayList.get(j));
                }
            }
        }

        FavouriteText = findViewById(R.id.FavourtieText);
        if (FavouriteMoviesList.toArray().length == 0) {
            FavouriteText.setText("Nothing on favourties");
        } else {
            FavouriteText.setText("Your Favourites");
        }
        FavoritesListAdapter favoritesListAdapter =new FavoritesListAdapter(getApplicationContext(),FavouriteMoviesList);

        binding.favoritesListView.setAdapter(favoritesListAdapter);

        bottomNavigation= (MeowBottomNavigation) findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_favorite));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_local_movies_24));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
                                               @Override
                                               public void onShowItem(MeowBottomNavigation.Model item) {
                                                   //initialize fragment
                                                   Intent i;
                                                   // check condition
                                                   switch (item.getId()) {
                                                       case 1:
                                                           //when id is 1 initialize fragment1
                                                           i = new Intent(getApplicationContext(),HomeActivity.class);
                                                           startActivity(i);
                                                           finish();
                                                           break;
                                                       case 3:
                                                           i = new Intent(getApplicationContext(),TopActivity.class);
                                                           startActivity(i);
                                                           finish();
                                                           break;
                                                   }
                                                           /*

                                                       case 2:
                                                           //when id is 2 initialize fragment2
                                                           fragment=new FavoriteFragment();
                                                           break;
                                                       case 3:
                                                           //when id is 3 initialize fragment3
                                                           fragment=new HelpFragment();
                                                           break;
                                                       case 4:
                                                           //when id is 4 initialize fragment4
                                                           fragment=new profileFragment();
                                                           break;

                                                   }
*/
                                               }
                                           }


        );
        //set notification
        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(2,false);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //display toast

            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(
                        getApplicationContext(),
                        "you Reselected "+item.getId(),Toast.LENGTH_SHORT

                ).show();
            }
        });

    }
}