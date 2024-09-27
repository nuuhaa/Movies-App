package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.view.View;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.movieapp.models.database_models.Favourites;
import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;
import com.example.movieapp.R;
import com.example.movieapp.models.adapters.gridAdapter;
import com.example.movieapp.models.database_models.User;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    //initialize variables
    static User user;
    MeowBottomNavigation bottomNavigation;
    GridView gridView;
    //String [] moveisNames={"beauty","daster","aladdin","guardians","hangover","potiet"};
    //int []numberImagese={R.drawable.beauty,R.drawable.daster,R.drawable.aladdin,R.drawable.guardians,R.drawable.hangover,R.drawable.potiet};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //assign variables
        bottomNavigation= (MeowBottomNavigation) findViewById(R.id.bottom_navigation);
        gridView = (GridView) findViewById(R.id.grid_view);

        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
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
        //adapter
        gridAdapter adapter = new gridAdapter(HomeActivity.this,movieArrayList);
        gridView.setAdapter(adapter);
        //on item click listner

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieActivity.setUser(user);
                MovieActivity.setPressedMovie(movieArrayList.get(position));
                Intent i = new Intent(getApplicationContext(),MovieActivity.class);
                startActivity(i);
            }
        });

        //add menu item
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
                                                       case 2:
                                                           //when id is 1 initialize fragment1
                                                           i = new Intent(getApplicationContext(),FavouritesActivity.class);
                                                           startActivity(i);
                                                           FavouritesActivity.setUser(user);
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
        bottomNavigation.show(1,false);
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
  /*  private void LoadFragment(Fragment fragment){
        //replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();

    }*/
}