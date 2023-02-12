package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static List<Distination> destinationPref;
    public  int randomIndex;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBaseHelper dataBaseHelper =new DataBaseHelper(getActivity(),"travelers",null,1);

        String EmailValue= SignIn.EmailValue;
        int userID= dataBaseHelper.getUserId(EmailValue); //EmailValue
        String destnationprefContinent=dataBaseHelper.getPrefiredDestination(userID);
        List<Distination> destination=DistinationJsonParser.distinationsdata;
        destinationPref=new ArrayList<>();
        for (int i=0;i<destination.size();i++){
            if (destination.get(i).getContinent().equals(destnationprefContinent)){
                destinationPref.add(destination.get(i));
            }

        }


        randomIndex = new Random().nextInt(destinationPref.size());

        return inflater.inflate(R.layout.fragment_home, container, false);
    }




    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DisplayIntoUI();

        Button buttonLocation=(Button) getActivity().findViewById(R.id.buttonLocation);
        Button buttonGenerate=(Button) getActivity().findViewById(R.id.buttonGenerate);


        buttonLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    Intent mapsIntent =new Intent();

                mapsIntent.setAction(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:"+Float.toString((destinationPref.get(randomIndex).getLatitude()))+","+Float.toString((destinationPref.get(randomIndex).getLongitude()))));

                    startActivity(mapsIntent);
            }


        });


        buttonGenerate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int temp=randomIndex;

                randomIndex = new Random().nextInt(destinationPref.size());
                if (randomIndex==temp){
                    randomIndex = new Random().nextInt(destinationPref.size());

                }
                DisplayIntoUI();
            }


        });






       }



       public void DisplayIntoUI(){
           TextView textViewCity=(TextView) getActivity().findViewById(R.id.textViewCityValue);
           TextView textViewCountry=(TextView) getActivity().findViewById(R.id.textViewCountryValue);
           TextView textViewContinent=(TextView) getActivity().findViewById(R.id.textViewContinentValue);
           TextView textViewLongitude=(TextView) getActivity().findViewById(R.id.textViewLongitudeValue);
           TextView textViewLatitude=(TextView) getActivity().findViewById(R.id.textViewLatitudeValue);
           TextView textViewCost=(TextView) getActivity().findViewById(R.id.textViewCostValue);
           TextView textViewDescription=(TextView) getActivity().findViewById(R.id.textViewDescriptionValue);

           ImageView DestinationImageView=(ImageView) getActivity().findViewById(R.id.DestinationImageViewValue);



           Picasso.get().load(destinationPref.get(randomIndex).getImg().toString()).into(DestinationImageView);


           textViewCity.setText("City Name\n"+destinationPref.get(randomIndex).getCity().toString());
           textViewCountry.setText("Country\n"+destinationPref.get(randomIndex).getCountry().toString());
           textViewContinent.setText("Continent\n"+destinationPref.get(randomIndex).getContinent().toString());
           textViewLongitude.setText( "Longitude\n"+Float.toString((destinationPref.get(randomIndex).getLongitude())));
           textViewLatitude.setText("Latitude\n"+ Float.toString((destinationPref.get(randomIndex).getLatitude())));
           textViewCost.setText("Cost\n"+String.valueOf(destinationPref.get(randomIndex).getCost()));
           textViewDescription.setText("Description\n"+destinationPref.get(randomIndex).getDescription().toString());





       }



}