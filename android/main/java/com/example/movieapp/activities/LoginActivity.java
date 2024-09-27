package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.models.database_models.Movie;
import com.example.movieapp.models.database_models.MovieDBHelper;
import com.example.movieapp.models.database_models.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private EditText logEmail,logPas ;
    private Button logBtn;
    private TextView logQn1,logQn2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        logEmail = findViewById(R.id.loginEmail);
        logPas = findViewById(R.id.loginPassword);
        logBtn = findViewById(R.id.loginButton);
        logQn1 = findViewById(R.id.loginPageQuestin1);
        logQn2 = findViewById(R.id.loginPageQuestin2);


        logBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<User> users = new ArrayList<User>();
                        MovieDBHelper movieDBHelper = new MovieDBHelper(getApplicationContext());
                        Cursor cursor = movieDBHelper.fetchAllUsers();
                        while (!cursor.isAfterLast()){
                            User user = new User(
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getInt(3),
                                    cursor.getString(5),
                                    cursor.getString(4).equals("1")
                            );
                            user.setId(cursor.getInt(0));
                            System.out.println(user.getId());
                            users.add(user);
                            cursor.moveToNext();
                        }

                        boolean check = false;
                        int index = 0;
                        for(int i = 0 ; i < users.toArray().length;i++){
                            if(users.get(i).getMail().equals(logEmail.getText().toString()) && users.get(i).getPassword().equals(logPas.getText().toString())) {
                                check = true;
                                index = i;
                                break;
                            }
                        }
                        if(check) {
                            if(users.get(index).isAdmin()) {
                                Intent i = new Intent(getApplication(), AdminPanel.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                HomeActivity.user = users.get(index);
                                Intent i = new Intent(getApplication(),HomeActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"User doesn't exist",Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
        Intent i = new Intent(this,RegisterActivity.class);
        logQn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(i);
                    }
                }
        );

    }
}