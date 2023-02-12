package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    static SharedPrefManager sharedPrefManager;
    EditText email;
    CheckBox rememberMe;
    String pass;
    static String email_Pref;
    public static String EmailValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        setContentView(R.layout.activity_sign_in);
        EditText password=(EditText) findViewById(R.id.PasswordEditText);
        email = (EditText) findViewById(R.id.EmailAddress);
        Button Sign_in = (Button) findViewById(R.id.button);
        rememberMe = (CheckBox) findViewById(R.id.checkBox);
        ImageButton back_btn_signin=(ImageButton) findViewById(R.id.back_btn_signin);
        Button signupaccountbtn=(Button) findViewById(R.id.signupaccountbtn);

        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(SignIn.this,"travelers", null,1);
        sharedPrefManager =SharedPrefManager.getInstance(this);

        email_Pref = sharedPrefManager.readString("Email","noValue");

        if (!email_Pref.equals("noValue")){
            email.setText(email_Pref);
            rememberMe.setChecked(true);
        }
        else {
            email.setHint("Enter your email address");
            rememberMe.setChecked(false);
        }
        int userID= dataBaseHelper.getUserId(EmailValue);
        Sign_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(rememberMe.isChecked()) {
                    sharedPrefManager.writeString("Email",email.getText().toString());
                }
                else {
                    sharedPrefManager.writeString("Email","noValue");
                }

                if (email.getText().toString().length()==0 ||password.getText().toString().length()==0){
                    Toast.makeText(SignIn.this,"Make Sure To Fill All Fields ", Toast.LENGTH_SHORT).show();
                    if (email.getText().toString().length()==0) {


                        email.setBackgroundTintList(ColorStateList.valueOf(Color.RED));  }
                    else{
                        email.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));

                    }
                    if (password.getText().toString().length()==0){
                        password.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    }else{
                        password.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    }

                }else {
                    email.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    password.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    pass = dataBaseHelper.checkIfPasswordExists(email.getText().toString());
                    dataBaseHelper.getAllUser();
                    if (dataBaseHelper.checkIfEmailExists(email.getText().toString())==false){
                        if (pass.equals(password.getText().toString())){
                            EmailValue=email.getText().toString();
                            email.setTextColor(Color.BLACK);
                            password.setTextColor(Color.BLACK);
                            Intent intent = new Intent(SignIn.this,NavigationDrawerActivity.class);
                            SignIn.this.startActivity(intent);
                            finish();
                        }
                        else {
                            password.setTextColor(Color.RED);
                            Toast.makeText(SignIn.this,"Wrong Password !!", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        email.setTextColor(Color.RED);
                        Toast.makeText(SignIn.this,"This Email Is Not Register ", Toast.LENGTH_SHORT).show();
                    }

                }}
        });


        back_btn_signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,MainPageOfTheApp.class);
                SignIn.this.startActivity(intent);
                finish();
            }
        });
        signupaccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,signupPage.class);
                SignIn.this.startActivity(intent);
                finish();
            }
        });

    }


    public static void updateSharedpref(String email) {


        if (!email_Pref.equals("noValue")) {
            sharedPrefManager.writeString("Email", email);
        }

    }




}