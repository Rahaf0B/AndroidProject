package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public int userID;

    public String [] singleUserData;
    private Spinner travelDSpinneredit=null;
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//       getUserData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }



    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UpdateUIprofile();


    }
public void UpdateUIprofile() {
    getUserData();
    TextView editTextTextFNameEdit = (TextView) getActivity().findViewById(R.id.editTextTextFNameEdit);
    TextView editTextTextLNameEdit = (TextView) getActivity().findViewById(R.id.editTextTextLNameEdit);
    TextView editTextTextEmailAddress = (TextView) getActivity().findViewById(R.id.editTextTextEmailAddress);
    TextView editTextTextPassword = (TextView) getActivity().findViewById(R.id.editTextTextPassword);
    TextView editTextTextConfPassword = (TextView) getActivity().findViewById(R.id.editTextTextConfPassword);
    travelDSpinneredit =(Spinner) getActivity().findViewById(R.id.spinnerEdit);

    fillTheSpinnerMenu();
    editTextTextFNameEdit.setHint(singleUserData[0]);
    editTextTextLNameEdit.setHint(singleUserData[1]);
    editTextTextEmailAddress.setHint(singleUserData[2]);
    Pattern PassValidation = Pattern.compile("^(?=.*[a-z])(?=."
            + "*[A-Z])(?=.*\\d).+$");
    Button buttonSave=(Button) getActivity().findViewById(R.id.buttonSave);

    buttonSave.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            DataBaseHelper dataBaseHelperEdit =new DataBaseHelper(getActivity(),"travelers",null,1);
            boolean ChangeEmail=false;
            String Emailval="";
            if (!(editTextTextFNameEdit.getText().toString().isEmpty())){
                if (editTextTextFNameEdit.getText().toString().matches("^.*[^a-zA-Z0-9 ].*$") || editTextTextFNameEdit.getText().toString().matches(".*[0-9].*")) {
                    editTextTextFNameEdit.setTextColor(Color.RED);
                }

                  else if (editTextTextFNameEdit.getText().toString().length()>=3 && editTextTextFNameEdit.getText().toString().length()<=20) {
                    editTextTextFNameEdit.setTextColor(Color.BLACK);
                   String Fname=editTextTextFNameEdit.getText().toString();
                    dataBaseHelperEdit.UpdateFName(editTextTextFNameEdit.getText().toString(), userID);
                    editTextTextFNameEdit.setText("");
                    editTextTextFNameEdit.setHint(Fname);
                }else{
                    editTextTextFNameEdit.setTextColor(Color.RED);
                }
            }
            if (!(editTextTextLNameEdit.getText().toString().isEmpty())){

                if (editTextTextLNameEdit.getText().toString().matches("^.*[^a-zA-Z0-9 ].*$") || editTextTextLNameEdit.getText().toString().matches(".*[0-9].*")) {
                    editTextTextLNameEdit.setTextColor(Color.RED);
                }
                else if (editTextTextLNameEdit.getText().toString().length()>=3 && editTextTextLNameEdit.getText().toString().length()<=20) {
                    String Lname=editTextTextLNameEdit.getText().toString();
                    editTextTextLNameEdit.setTextColor(Color.BLACK);
                    dataBaseHelperEdit.UpdateLName(editTextTextLNameEdit.getText().toString(), userID);
                    editTextTextLNameEdit.setText("");
                    editTextTextLNameEdit.setHint(Lname);
                }
                else {
                    editTextTextLNameEdit.setTextColor(Color.RED);
                }

            }
            if (!(editTextTextEmailAddress.getText().toString().isEmpty())){
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress.getText().toString()).matches()) {

                    if (dataBaseHelperEdit.checkIfEmailExists(editTextTextEmailAddress.getText().toString())) {
                        ChangeEmail=true;
                        editTextTextEmailAddress.setTextColor(Color.BLACK);
                        dataBaseHelperEdit.UpdateEmail(editTextTextEmailAddress.getText().toString(), userID);
                        SignIn.updateSharedpref(editTextTextEmailAddress.getText().toString());
                        Emailval=editTextTextEmailAddress.getText().toString();
                        editTextTextEmailAddress.setText("");
                        editTextTextEmailAddress.setHint(Emailval);
                        SignIn.EmailValue=Emailval;

                    }

                    else{
                        ChangeEmail=false;
                        editTextTextEmailAddress.setTextColor(Color.RED);
                        if (editTextTextEmailAddress.getText().toString().equals(SignIn.EmailValue)) {
                            Toast.makeText(getActivity(), "This is Your Current Email",
                                    Toast.LENGTH_LONG).show();

                        }else{
                        Toast.makeText(getActivity(), "Your Email is Invalid",
                                Toast.LENGTH_LONG).show();
                    }}

                }else{
                    ChangeEmail=false;
                    editTextTextEmailAddress.setTextColor(Color.RED);
                    Toast.makeText(getActivity(), "Your Email is Invalid",
                            Toast.LENGTH_LONG).show();
                }
            }

            if (!(editTextTextPassword.getText().toString().isEmpty())) {
                Matcher passMatch=PassValidation.matcher(editTextTextPassword.getText().toString());
               if ((editTextTextPassword.getText().toString().length() >= 8 && editTextTextPassword.getText().toString().length() <= 15) && passMatch.matches()){
                if (editTextTextPassword.getText().toString().equals(editTextTextConfPassword.getText().toString())){
                    editTextTextPassword.setTextColor(Color.BLACK);
                    editTextTextConfPassword.setTextColor(Color.BLACK);


                    dataBaseHelperEdit.UpdatePass( editTextTextPassword.getText().toString(), userID);
                            editTextTextConfPassword.setText("");
                            editTextTextPassword.setText("");

                }
                else{
                    editTextTextPassword.setTextColor(Color.RED);
                    editTextTextConfPassword.setTextColor(Color.RED);
                }

               }
               else{
                   editTextTextPassword.setTextColor(Color.RED);
                   Toast.makeText(getActivity(), "Check Your Input",
                           Toast.LENGTH_LONG).show();
               }


            }


            String distnationVlaue=travelDSpinneredit.getSelectedItem().toString();

                if (!(distnationVlaue.equals(singleUserData[4]))) {

                    dataBaseHelperEdit.UpdateDestination(distnationVlaue, userID);


            }



            String userName=dataBaseHelperEdit.getUserNme(SignIn.EmailValue);
            TextView userNamenav=(TextView) getActivity().findViewById(R.id.userNamenav);
            userNamenav.setText(userName);


        }


    });


}


    public void getUserData() {
        DataBaseHelper dataBaseHelper =new DataBaseHelper(getActivity(),"travelers",null,1);

        String EmailValue= SignIn.EmailValue;

        userID= dataBaseHelper.getUserId(EmailValue);
        Cursor UserData=dataBaseHelper.getUser(userID);
        singleUserData=new String[5];
        int i=0;
        while (UserData.moveToNext()) {
            singleUserData[0]=UserData.getString(1);
            singleUserData[1]=UserData.getString(2);
            singleUserData[2]=UserData.getString(3);
            singleUserData[3]=UserData.getString(4);
            singleUserData[4]=UserData.getString(5);


        }

    }


    public void fillTheSpinnerMenu() {
        List<Distination> distinations=DistinationJsonParser.distinationsdata;
        String[] temp = new String[distinations.size()];

        if (distinations!=null){

            for (int i = 0; i < distinations.size(); i++) {
                temp[i]=distinations.get(i).getContinent().toString();

            }
            int size=temp.length;

            LinkedHashSet<String> options = new LinkedHashSet<String>();
            options.add(singleUserData[4]);

            for (int i=0;i<size;i++){
                options.add(temp[i]);}

            String [] destinationOptions= new String[options.size()];
            options.toArray(destinationOptions);

            ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, destinationOptions);
            travelDSpinneredit.setAdapter(objGenderArr);

        }
    }
}