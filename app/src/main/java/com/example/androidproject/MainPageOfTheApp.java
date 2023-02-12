package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainPageOfTheApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_of_the_app);

        Button signup_btn = (Button)findViewById(R.id.btn_signup);
        Button signin_btn = (Button)findViewById(R.id.btn_signin);

        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainPageOfTheApp.this);
        connectionAsyncTask.execute("https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca");




        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageOfTheApp.this,signupPage.class);
                MainPageOfTheApp.this.startActivity(intent);
                finish();

            } });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageOfTheApp.this,SignIn.class);
                MainPageOfTheApp.this.startActivity(intent);
                finish();

            } });


    }

    public void getTheData(List<Distination> distinations) {


    }

}