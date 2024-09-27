package com.example.movieapp.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.movieapp.R;
import com.example.movieapp.models.adapters.CustomAdapter;
import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;
import com.example.movieapp.models.database_models.User;

import java.util.ArrayList;


public class TopActivity extends AppCompatActivity {

    static User user;
    MeowBottomNavigation bottomNavigation;
    ArrayList<Movie> allMovies;
    RecyclerView recyclerView;
    TextView no_data;
    ArrayList<String> movie_id, movie_name, movie_type, movie_rate , movie_description , movie_running_time ,movie_audiences , movie_actor , movie_available,movie_image ;

    CustomAdapter customAdapter;
    // a
    MovieDBHelper db  ;


    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        TopActivity.user = user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        db = new MovieDBHelper(this) ;


        //a

        recyclerView = findViewById(R.id.recyclerView);
        no_data = findViewById(R.id.no_data);

        db = new MovieDBHelper(TopActivity.this);
        movie_id = new ArrayList<>();
        movie_name = new ArrayList<>();
        movie_type = new ArrayList<>();
        movie_rate = new ArrayList<>();
        movie_description = new ArrayList<>();
        movie_running_time = new ArrayList<>();
        movie_audiences = new ArrayList<>();
        movie_actor = new ArrayList<>();
        movie_available = new ArrayList<>();
        movie_image = new ArrayList<>();

        storeDataInArrays();
        System.out.println(movie_name.get(0));
        customAdapter = new CustomAdapter(TopActivity.this,this, movie_id, movie_name, movie_type,
                movie_rate , movie_description , movie_running_time ,movie_audiences , movie_actor , movie_available , movie_image);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TopActivity.this));


        //a
        bottomNavigation= (MeowBottomNavigation) findViewById(R.id.bottom_navigation);
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
                                                       case 1:
                                                           i = new Intent(getApplicationContext(),HomeActivity.class);
                                                           HomeActivity.user = user;
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
        bottomNavigation.show(3,false);
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


        // db = new DatabaseaHandler(this) ;

       // Movie movie = new Movie(8 , "King" , "action" , "how amazing how amazing how amazing how amazing how amazing how amazing" , "1:13:45" , "kids" , "aa" ,"netflix" ,"unnamed") ;
//        //db.add_user(user);

        //db.add_movie(movie);
        //s = user.getFav_type();
        //public Movie(int rate, String name, String type) {
        // String type = db.User_type(1) ;
//        List<Movie> movieList = (List<Movie>) db.showMovies(user.getFav_type());
//       for ( Movie m : movieList)
//        {
//            String info = "name" + m.getName() + " rating " + m.getRate() + " type " + m.getType() ;
//           Log.d("movie data" , info);
//        }


 /*       x = 2;
        if(s.equalsIgnoreCase("action"))
        {
            a = true ;
        }
        if(s.equalsIgnoreCase("comedy"))
        {
            b = true ;
        }
*/
    }

    void storeDataInArrays() {

//        Cursor cursor = db.readAllDataB() ;
        // Cursor cursor = null ;
    /*    if (a) {
            ///Cursor cursor = db.readAllDataA();
            if (cursor.getCount() == 0) {
                //
                // empty_imageview.setVisibility(View.VISIBLE);
                no_data.setVisibility(View.VISIBLE);
            } else {
                while (cursor.moveToNext()) {
                    movie_id.add(cursor.getString(0));
                    movie_rate.add(cursor.getString(1));
                    movie_name.add(cursor.getString(2));
                    movie_type.add(cursor.getString(3));

                    movie_description.add(cursor.getString(4));
                    movie_running_time.add(cursor.getString(5));
                    movie_audiences.add(cursor.getString(6));
                    movie_actor.add(cursor.getString(7));
                    movie_available.add(cursor.getString(8));
                    movie_image = cursor.getString(9);
                }
            }
        }*/
     /*   if (b) {
            ///Cursor cursor2 = db.readAllDataB();
            if (cursor2.getCount() == 0) {
                //
                // empty_imageview.setVisibility(View.VISIBLE);
                no_data.setVisibility(View.VISIBLE);
            } else {
                while (cursor2.moveToNext()) {
                    movie_id.add(cursor2.getString(0));
                    movie_rate.add(cursor2.getString(1));
                    movie_name.add(cursor2.getString(2));
                    movie_type.add(cursor2.getString(3));

                    movie_description.add(cursor2.getString(4));
                    movie_running_time.add(cursor2.getString(5));
                    movie_audiences.add(cursor2.getString(6));
                    movie_actor.add(cursor2.getString(7));
                    movie_available.add(cursor2.getString(8));
                    movie_image = cursor2.getString(9);
                }
            }

        }
*/

        allMovies = new ArrayList<>();
        movie_id = new ArrayList<>();
        movie_name = new ArrayList<>();
        movie_type = new ArrayList<>();
        movie_rate = new ArrayList<>();
        movie_description = new ArrayList<>();
        movie_running_time = new ArrayList<>();
        movie_audiences = new ArrayList<>();
        movie_actor = new ArrayList<>();
        movie_available = new ArrayList<>();
        movie_image = new ArrayList<>();
        MovieDBHelper movieDBHelper = new MovieDBHelper(getApplicationContext());
        Cursor cursor = movieDBHelper.fetchAllMovies();
        while (!cursor.isAfterLast()) {
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
            allMovies.add(movie);
            cursor.moveToNext();
        }
            for(int i = 0 ; i < allMovies.toArray().length;i++){
                if(allMovies.get(i).getRating() > 7.5) {
                    movie_id.add(String.valueOf(allMovies.get(i).getId()));
                    movie_rate.add(String.valueOf(allMovies.get(i).getRating()));
                    movie_name.add(allMovies.get(i).getName());
                    movie_type.add(allMovies.get(i).getType());
                    movie_description.add(allMovies.get(i).getDescription());
                    movie_running_time.add(String.valueOf(allMovies.get(i).getRunningTime()));
                    movie_audiences.add(String.valueOf(allMovies.get(i).getAudiences()));
                    movie_actor.add(allMovies.get(i).getActors());
                    movie_available.add(allMovies.get(i).getPlatForm());
                    movie_image.add(allMovies.get(i).getImage());
                }
            }
    }
}


//        if(cursor.getCount() == 0){
//            //
//            // empty_imageview.setVisibility(View.VISIBLE);
//            no_data.setVisibility(View.VISIBLE);
//        }else{
//            while (cursor.moveToNext()){
//                movie_id.add(cursor.getString(0));
//                movie_rate.add(cursor.getString(1));
//                movie_name.add(cursor.getString(2));
//                movie_type.add(cursor.getString(3));
//
//                movie_description.add(cursor.getString(4)) ;
//                movie_running_time.add(cursor.getString(5));
//                movie_audiences.add(cursor.getString(6));
//                movie_actor.add(cursor.getString(7));
//                movie_available.add(cursor.getString(8));
//            }
//            //empty_imageview.setVisibility(View.GONE);
//            //
//            no_data.setVisibility(View.GONE);
//        }


//       switch (x)
//       {
//           case 1 :
//                       Cursor cursor = db.readAllData("action");
//                      if(cursor.getCount() == 0){
//                      //
//                          // empty_imageview.setVisibility(View.VISIBLE);
//                       no_data.setVisibility(View.VISIBLE);
//                     }else{
//                           while (cursor.moveToNext()){
//                                book_id.add(cursor.getString(0));
//                                book_title.add(cursor.getString(1));
//                                book_author.add(cursor.getString(2));
//                         book_pages.add(cursor.getString(3));
//                                  }
//            //empty_imageview.setVisibility(View.GONE);
//            //
//                          no_data.setVisibility(View.GONE);
//                         }
//               break;
//       }