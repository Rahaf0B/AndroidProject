package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupPage extends AppCompatActivity {
    private static final String TOAST_TEXT = "";
    private Spinner travelDSpinner=null;
    DataBaseHelper dataBaseHelper =new DataBaseHelper(signupPage.this,"travelers",null,1);
    public  List<Distination> distinationsdata= new ArrayList<>();;
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
        ImageButton  back_btn=(ImageButton) findViewById(R.id.back_btn);
        Button signinaccountbtn=(Button) findViewById(R.id.signinaccountbtn);

        TextView ContainPass=(TextView)findViewById(R.id.PassContent);
        TextView ContainLowerCase=(TextView)findViewById(R.id.ContainLower);
        TextView ContainUpperCase=(TextView)findViewById(R.id.ContainUpper);
        TextView ContainNumber=(TextView)findViewById(R.id.ContainAnumber);
        TextView ErrorName=(TextView) findViewById(R.id.errorName);
        TextView ErrorEmail =(TextView) findViewById(R.id.errorEmail);
        TextView ErrorPass =(TextView) findViewById(R.id.errorPass);

        TextView textViewEmail=(TextView) findViewById(R.id.textViewEmail);
        TextView textViewPass=(TextView) findViewById(R.id.textViewPass);




        ConstraintLayout.LayoutParams newlayoutparms2 =(ConstraintLayout.LayoutParams) EmailEditText.getLayoutParams() ;
        ConstraintLayout.LayoutParams newlayoutparms =(ConstraintLayout.LayoutParams) textViewEmail.getLayoutParams() ;



        Toast ErrorMsg =Toast.makeText(signupPage.this, TOAST_TEXT,Toast.LENGTH_LONG);



        travelDSpinner =(Spinner) findViewById(R.id.travelDistinationSpinner);
        fillTheMenu(DistinationJsonParser.distinationsdata);




        back_btn.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(signupPage.this,MainPageOfTheApp.class);
                                            signupPage.this.startActivity(intent);
                                            finish();
                                        }
                                    });

        signinaccountbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signupPage.this,SignIn.class);
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

                boolean checkFNameLength=true;
                boolean checkFnameChar=true;
                boolean checkpass=true;

                if (FnameValue.isEmpty() || LnameValue.isEmpty() || EmailValue.isEmpty() || PassValue.isEmpty() || ConfPassValue.isEmpty() ){
                    ErrorMsg.setText("You Should Fill All The Fields");
                    ErrorMsg.show();

                    if (FnameValue.isEmpty()) {
                        FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    } else {
                        FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    }

                    if (LnameValue.isEmpty()) {
                        LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    } else {
                        LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    }


                    if (EmailValue.isEmpty()) {
                        EmailEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    } else {
                        EmailEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    }

                    if (PassValue.isEmpty()) {
                        PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    } else {
                        PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    }

                    if (ConfPassValue.isEmpty()) {
                        ConfirmPasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    } else {
                        ConfirmPasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    }



                }
                else{
                    FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    EmailEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    ConfirmPasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));


                    if (FnameValue.length()<3 || FnameValue.length()>20){
                        FnameEditText.setTextColor(Color.RED);
                        FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                        CheckInput [0]=false;
                        FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        ErrorName.setText("The Name should be 3-20 char");
                        ErrorName.setVisibility(View.VISIBLE);

                        newlayoutparms.topMargin=45;
                        newlayoutparms.startToEnd=50;
                        textViewEmail.setLayoutParams(newlayoutparms);

                        newlayoutparms2.topMargin=30;
                        newlayoutparms2.endToStart=72;
                        EmailEditText.setLayoutParams(newlayoutparms2);

                        checkFNameLength=false;

                    }
                    else {

                        checkFNameLength=true;
                        ErrorName.setVisibility(View.INVISIBLE);

                        newlayoutparms.bottomToTop=15;
                        newlayoutparms.startToEnd=50;
                        textViewEmail.setLayoutParams(newlayoutparms);

                        newlayoutparms2.bottomToTop=7;
                        newlayoutparms2.endToStart=72;
                        EmailEditText.setLayoutParams(newlayoutparms2);

                        if (FnameValue.matches("^.*[^a-zA-Z0-9 ].*$") || FnameValue.matches(".*[0-9].*")) {
                            checkFnameChar=false;
                            ErrorName.setText("Name should not contain non-char");
                            ErrorName.setVisibility(View.VISIBLE);
                            FnameEditText.setTextColor(Color.RED);
                            FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            CheckInput[0] = false;
                            FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            ErrorMsg.setText("The Name Should Not Contain Non Alpha Char");
                            ErrorMsg.show();
                        } else {
                            checkFnameChar=true;
                            ErrorName.setVisibility(View.INVISIBLE);
                            FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));

                            CheckInput[0] = true;
                            FnameEditText.setTextColor(Color.BLACK);
                            FnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        }
                    }


                    if (LnameValue.length()<3 || LnameValue.length()>20){

                        LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                        LnameEditText.setTextColor(Color.RED);
                        CheckInput [1]=false;
                        LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        if (checkFnameChar==false) {
                            ErrorName.setText("Fname has non-char & Lname should be 3-20");
                        }
                        else{
                            ErrorName.setText("The Name should be 3-20 char");
                        }

                        ErrorName.setVisibility(View.VISIBLE);

                    }
                    else{

                        if (LnameValue.matches("^.*[^a-zA-Z0-9 ].*$")   ||  LnameValue.matches(".*[0-9].*")){
                            if (checkFNameLength==false){
                                ErrorName.setText("Lname has non-char & Fname should be 3-20");
                            }
                            else{
                                ErrorName.setText("Name should not contain non-char");
                            }
                            ErrorName.setVisibility(View.VISIBLE);
                            ErrorMsg.setText("The Name Should Not Contain Non Alpha Char");
                            ErrorMsg.show();
                            LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            LnameEditText.setTextColor(Color.RED);
                            CheckInput [1]=false;
                            LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        }
                        else {
                            LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                           if (checkFnameChar==true && checkFNameLength==true){
                               ErrorName.setVisibility(View.INVISIBLE);
                           }
                            LnameEditText.setTextColor(Color.BLACK);
                            CheckInput[1] = true;
                            LnameEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                        }           }




                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(EmailValue).matches()){
                        EmailEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        ErrorEmail.setText("Email Invalid");
                        EmailEditText.setTextColor(Color.RED);
                        CheckInput [2]=false;
                        ErrorMsg.setText("You Email Is Invalid");
                        ErrorEmail.setVisibility(View.VISIBLE);
                        ErrorMsg.show();

                    }
                    else {

                        if (dataBaseHelper.checkIfEmailExists(EmailValue)){
                            ErrorEmail.setVisibility(View.INVISIBLE);
                            EmailEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));

                            EmailEditText.setTextColor(Color.BLACK);
                            CheckInput[2] = true;
                            EmailEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));

                        }
                        else{
                            EmailEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            ErrorEmail.setText("This Email Is Used");
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


                            ErrorPass.setVisibility(View.VISIBLE);
                            PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                            checkpass=false;
                            PasswordEditText.setTextColor(Color.RED);
                            CheckInput[3] = false;
                            PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                            ErrorMsg.setText("Your Password Is Not Correct \nit length should be between 8 and 15 char");
                            ErrorMsg.show();
                        } else {
                            ErrorPass.setVisibility(View.INVISIBLE);
                            PasswordEditText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));

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




                        Cursor alldata=dataBaseHelper.getAllUser();

                        int id= dataBaseHelper.getUserId(EmailValue);


                        Intent intent = new Intent(signupPage.this,SignIn.class);
                        signupPage.this.startActivity(intent);
                        finish();
                    }
                }
            }

        });
    }

    public void fillTheMenu(List<Distination> distinations) {
        String[] temp = new String[distinations.size()];
        distinationsdata=distinations;

        if (distinations!=null){

            for (int i = 0; i < distinations.size(); i++) {
                temp[i]=distinations.get(i).getContinent().toString();

            }
            int size=temp.length;

            LinkedHashSet<String> options = new LinkedHashSet<String>();

            for (int i=0;i<size;i++){
            options.add(temp[i]);}

            String [] destinationOptions= new String[options.size()];
            options.toArray(destinationOptions);

            ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, destinationOptions);
            travelDSpinner.setAdapter(objGenderArr);

        }
    }

}