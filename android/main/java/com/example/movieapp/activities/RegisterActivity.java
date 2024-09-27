package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.models.database_models.MovieDBHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText RegEmail, RegPas, RefAge;
    private Button RegBtn;
    private TextView RegQn3, RegQn2;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        RegEmail = findViewById(R.id.RegistrationEmail);
        RegPas = findViewById(R.id.RegistrationPassword);
        RefAge = findViewById(R.id.RegistrationAge);
        RegBtn = findViewById(R.id.RegistrationButton);
        RegQn3 = findViewById(R.id.RegistrationPageQuestin3);
        RegQn2 = findViewById(R.id.RegistrationPageQuestin2);

        RegQn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
        );


        RegBtn.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        RadioGroup radioGroup = findViewById(R.id.radioGroup);
                        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        if(RegEmail.getText().toString().equals("")||RegPas.getText().toString().equals("")||RefAge.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "EmptyData , Please Check Your Data again", Toast.LENGTH_LONG).show();

                        }
                        else {

                            String registerEmail = RegEmail.getText().toString();
                            boolean checkEmailValid = registerEmail.contains("@");

                            String password = RegPas.getText().toString();
                            boolean checkPassValid = (password.length() >= 8);

                            radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                            if (checkEmailValid && checkPassValid) {
                                MovieDBHelper movieDBHelper = new MovieDBHelper(getApplicationContext());
                                movieDBHelper.addUserData(
                                        RegEmail.getText().toString(),
                                        RegPas.getText().toString(),
                                        Integer.parseInt(RefAge.getText().toString()),
                                        radioButton.getText().toString(),
                                        false
                                );
                                Toast.makeText(getApplicationContext(), "SignedUp Successfully", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "Please Check Your Data again", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
        //radioGroup = findViewById(R.id.RegistrationButton);
        //Button buttonApply = findViewById(R.id.RegistrationButton);
    }

 /*   public void checkButton(View view){

    }*/
}