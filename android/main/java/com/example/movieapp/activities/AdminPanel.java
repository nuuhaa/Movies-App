package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.movieapp.R;

public class AdminPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Button insertButton = (Button) findViewById(R.id.insertNewData);
        Button seeDataButton = (Button) findViewById(R.id.seeData);

        Intent i1 = new Intent(this,ShowingData.class);
        Intent i2 = new Intent(this,InsertNewFilm.class);
        seeDataButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(i1);
                    }
                }
        );

        insertButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(i2);
                    }
                }
        );
    }
}