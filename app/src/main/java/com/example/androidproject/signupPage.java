package com.example.androidproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupPage extends AppCompatActivity {
    private static final String TOAST_TEXT = "";
    private Spinner travelDSpinner=null;
    DataBaseHelper dataBaseHelper =new DataBaseHelper(signupPage.this,"travelers",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        User newUser=new User();

        boolean [] CheckInput= new boolean []{true,true,true,true,true};

        EditText FnameEditText=(EditText)findViewById(R.id.FnameEditText);
        EditText LnameEditText=(EditText)findViewById(R.id.LnameEditText);
        EditText EmailEditText=(EditText)findViewById(R.id.EmailEditText);
        EditText PasswordEditText=(EditText)findViewById(R.id.PasswordEditText);
        EditText ConfirmPasswordEditText=(EditText)findViewById(R.id.ConfirmPasswordEditText);

        Button CreatAcount =(Button) findViewById(R.id.creatSignup_btn);
        Button back_btn=(Button) findViewById(R.id.back_btn);

        TextView ContainPass=(TextView)findViewById(R.id.PassContent);
        TextView ContainLowerCase=(TextView)findViewById(R.id.ContainLower);
        TextView ContainUpperCase=(TextView)findViewById(R.id.ContainUpper);
        TextView ContainNumber=(TextView)findViewById(R.id.ContainAnumber);
        TextView ErrorName=(TextView) findViewById(R.id.errorName);
        TextView ErrorEmail =(TextView) findViewById(R.id.errorEmail);
        TextView textViewEmail=(TextView) findViewById(R.id.textViewEmail);
        TextView textViewPass=(TextView) findViewById(R.id.textViewPass);


        ConstraintLayout.LayoutParams newlayoutparms2 =(ConstraintLayout.LayoutParams) EmailEditText.getLayoutParams() ;
        ConstraintLayout.LayoutParams newlayoutparms =(ConstraintLayout.LayoutParams) textViewEmail.getLayoutParams() ;



        Toast ErrorMsg =Toast.makeText(signupPage.this, TOAST_TEXT,Toast.LENGTH_LONG);



        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(signupPage.this);
        connectionAsyncTask.execute("https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca");


        travelDSpinner =(Spinner) findViewById(R.id.travelDistinationSpinner);


        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signupPage.this,MainPageOfTheApp.class);
                signupPage.this.startActivity(intent);
                finish();
            }
        });

        CreatAcount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String FnameValue=FnameEditText.getText().toString();
                String LnameValue=LnameEditText.getText().toString();
                String EmailValue= EmailEditText.getText().toString();
                String PassValue=PasswordEditText.getText().toString();
                String ConfPassValue=ConfirmPasswordEditText.getText().toString();
                String distnationVlaue=travelDSpinner.getSelectedItem().toString();

                Pattern passPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
                Pattern LowerCase= Pattern.compile("^(?=.*[a-z]).+$");
                Pattern UpperCase= Pattern.compile("^(?=.*[A-Z]).+$");
                Pattern Number=Pattern.compile("^(?=.*\\d).+$");
                Pattern PassValidation = Pattern.compile("^(?=.*[a-z])(?=."
                        + "*[A-Z])(?=.*\\d).+$");

                boolean checkFName=true;
                boolean checkpass=true;

                if (FnameValue.isEmpty() || LnameValue.isEmpty() || EmailValue.isEmpty() || PassValue.isEmpty() || ConfPassValue.isEmpty() ){
                    ErrorMsg.setText("You Should Fill All The Fields");
                    ErrorMsg.show();
                }
                else{

                    if (FnameValue.length()<3 || FnameValue.length()>20){
                        FnameEditText.setTextColor(Color.RED);
                        CheckInput [0]=false;
                        FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                        ErrorName.setVisibility(View.VISIBLE);

                        newlayoutparms.topMargin=45;
                        newlayoutparms.startToEnd=50;
                        textViewEmail.setLayoutParams(newlayoutparms);

                        newlayoutparms2.topMargin=30;
                        newlayoutparms2.endToStart=72;
                        EmailEditText.setLayoutParams(newlayoutparms2);

                        checkFName=false;

                    }
                    else {

                        checkFName=true;
                        ErrorName.setVisibility(View.INVISIBLE);

                        newlayoutparms.bottomToTop=15;
                        newlayoutparms.startToEnd=50;
                        textViewEmail.setLayoutParams(newlayoutparms);

                        newlayoutparms2.bottomToTop=7;
                        newlayoutparms2.endToStart=72;
                        EmailEditText.setLayoutParams(newlayoutparms2);

                        if (FnameValue.matches("^.*[^a-zA-Z0-9 ].*$") || FnameValue.matches(".*[0-9].*")) {
                            FnameEditText.setTextColor(Color.RED);
                            CheckInput[0] = false;
                            FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            ErrorMsg.setText("The Name Should Not Contain Non Alpha Char");
                            ErrorMsg.show();
                        } else {
                            CheckInput[0] = true;
                            FnameEditText.setTextColor(Color.BLACK);
                            FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        }
                    }


                    if (LnameValue.length()<3 || LnameValue.length()>20){


                        LnameEditText.setTextColor(Color.RED);
                        CheckInput [1]=false;
                        LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        ErrorName.setVisibility(View.VISIBLE);

                    }
                    else{
                        if (checkFName==true) {
                            ErrorName.setVisibility(View.INVISIBLE);
                        }
                        if (LnameValue.matches("^.*[^a-zA-Z0-9 ].*$")   ||  LnameValue.matches(".*[0-9].*")){
                            ErrorMsg.setText("The Name Should Not Contain Non Alpha Char");
                            ErrorMsg.show();
                            LnameEditText.setTextColor(Color.RED);
                            CheckInput [1]=false;
                            LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        }
                        else {
                            LnameEditText.setTextColor(Color.BLACK);
                            CheckInput[1] = true;
                            LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        }           }




                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(EmailValue).matches()){
                        EmailEditText.setTextColor(Color.RED);
                        CheckInput [2]=false;
                        ErrorMsg.setText("You Email Is Invalid");
                        ErrorMsg.show();

                    }
                    else {

                        if (dataBaseHelper.checkIfEmailExists(EmailValue)){
                            ErrorEmail.setVisibility(View.INVISIBLE);

                            EmailEditText.setTextColor(Color.BLACK);
                            CheckInput[2] = true;
                            EmailEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));

                        }
                        else{
                            ErrorEmail.setVisibility(View.VISIBLE);
                            EmailEditText.setTextColor(Color.RED);
                            CheckInput [2]=false;
                            ErrorMsg.setText("You Email Is Invalid");
                            ErrorMsg.show();
                        }

                    }

                    Matcher passMatch=PassValidation.matcher(PassValue);

                    if (passMatch.matches()) {


                        if (PassValue.length() < 8 || PassValue.length() > 15) {
                            checkpass=false;
                            PasswordEditText.setTextColor(Color.RED);
                            CheckInput[3] = false;
                            PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            ErrorMsg.setText("Your Password Is Not Correct \nit length should be between 8 and 15 char");
                            ErrorMsg.show();
                        } else {

                            PasswordEditText.setTextColor(Color.BLACK);
                            CheckInput[3] = true;
                            PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                            ContainLowerCase.setTextColor(Color.BLACK);
                            ContainUpperCase.setTextColor(Color.BLACK);
                            ContainNumber.setTextColor(Color.BLACK);
                            ContainPass.setVisibility(View.INVISIBLE);
                            ContainLowerCase.setVisibility(View.INVISIBLE);
                            ContainUpperCase.setVisibility(View.INVISIBLE);
                            ContainNumber.setVisibility(View.INVISIBLE);

                        }
                    }
                    else{
                        checkpass=false;

                        PasswordEditText.setTextColor(Color.RED);
                        CheckInput[3] = false;
                        PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        ContainPass.setVisibility(View.VISIBLE);
                        ContainLowerCase.setVisibility(View.VISIBLE);
                        ContainUpperCase.setVisibility(View.VISIBLE);
                        ContainNumber.setVisibility(View.VISIBLE);
                        ErrorMsg.setText("Your Password Is Not Correct");
                        ErrorMsg.show();
                        passMatch=LowerCase.matcher(PassValue);
                        if (!passMatch.matches() ){
                            ContainLowerCase.setTextColor(Color.RED);
                        }
                        else{
                            ContainLowerCase.setTextColor(Color.BLACK);
                        }
                        passMatch=UpperCase.matcher(PassValue);

                        if (!passMatch.matches() ){
                            ContainUpperCase.setTextColor(Color.RED);
                        }
                        else{
                            ContainUpperCase.setTextColor(Color.BLACK);
                        }
                        passMatch=Number.matcher(PassValue);

                        if (!passMatch.matches()){
                            ContainNumber.setTextColor(Color.RED);
                        }
                        else{
                            ContainNumber.setTextColor(Color.BLACK);
                        }

                    }
                    if (!PassValue.equals(ConfPassValue)){
                        ConfirmPasswordEditText.setTextColor(Color.RED);
                        CheckInput[4] = false;
                        ConfirmPasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        PasswordEditText.setTextColor(Color.RED);
                        CheckInput[3] = false;
                        PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        ErrorMsg.setText("Your Password and Confirm Password are not the same");
                        ErrorMsg.show();

                    }
                    else{
                        if (checkpass==true) {
                            ConfirmPasswordEditText.setTextColor(Color.BLACK);
                            CheckInput[4] = true;
                            CheckInput[3] = true;
                            ConfirmPasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        }
                    }

                    int countWrongInput=0;
                    for (int i=0;i<CheckInput.length;i++){
                        if (CheckInput[i]==false){
                            countWrongInput+=1;
                        }
                    }

                    if (countWrongInput>1){
                        ErrorMsg.setText("Your Input Is Wrong \nCheck The Data That You Inter");
                        ErrorMsg.show();
                    }
                    else if (countWrongInput==0){


                        newUser.setFname(FnameValue);
                        newUser.setLname(LnameValue);
                        newUser.setEmail(EmailValue);
                        newUser.setPassword(PassValue);
                        newUser.setDistination(distnationVlaue);

                        dataBaseHelper.insertUser(newUser);

                        Intent intent = new Intent(signupPage.this,HomePage.class);
                        signupPage.this.startActivity(intent);
                        finish();






                    }

                }
            }



        });






    }

    public void fillTheMenu(List<Distination> distinations) { //List<Distination> distinations
        String[] distinationsOptions = new String[distinations.size()];


        if (distinations!=null){

            for (int i = 0; i < distinations.size(); i++) {
                distinationsOptions[i]=distinations.get(i).getContinent().toString();

            }
            int size=distinationsOptions.length;
            int j=0;
            for (int k =0;k<size-1;k++){
                if (distinationsOptions[k] != distinationsOptions[k + 1]) {
                    distinationsOptions[j++] = distinationsOptions[k];
                }
            }
            distinationsOptions[j++]=distinationsOptions[size-1];


            ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distinationsOptions);
            travelDSpinner.setAdapter(objGenderArr);

        }
    }

}