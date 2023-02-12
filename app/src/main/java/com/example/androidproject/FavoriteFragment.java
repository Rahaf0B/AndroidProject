package com.example.androidproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView city;
    private TextView country;
    private String distnationVlaue = null;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }









    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FillSpinner();

        FavoriteDestination();
        DataBaseHelper dataBaseHelper =new DataBaseHelper(getActivity(),"travelers",null,1);
        int  userID= dataBaseHelper.getUserId( SignIn.EmailValue);
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        Button RemoveDestination = (Button) getActivity().findViewById(R.id.remove);
        Cursor allFavoriteCursor = dataBaseHelper.getAllFavorite(userID);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                RemoveDestination.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (spinner.getCount() == 0) {
                            Toast.makeText(getActivity(), "spinner hasn't values",
                                    Toast.LENGTH_LONG).show();
                        } else if (spinner.getCount() > 0) {
                            distnationVlaue = spinner.getSelectedItem().toString();
                            if (!(distnationVlaue.equals("Select City"))) {
                                dataBaseHelper.DeleteFavorite(distnationVlaue,userID);
                                FavoriteDestination();
                                FillSpinner();
                            }
                            else {
                                Toast.makeText(getActivity(), "You Should Select a City To Delete",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
            @Override
            public void onNothingSelected (AdapterView < ? > parentView){
                // your code here
            }
        });
    }

    public void FillSpinner(){
        DataBaseHelper dataBaseHelper =new DataBaseHelper(getActivity(),"travelers",null,1);
        int  userID= dataBaseHelper.getUserId( SignIn.EmailValue);
        Cursor allFavoriteCursor = dataBaseHelper.getAllFavorite(userID);
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        String [] City = new String[allFavoriteCursor.getCount() + 1];
        City[0] = "Select City";
        int i = 1;
        while (allFavoriteCursor.moveToNext()){
            City[i] = allFavoriteCursor.getString(1).toString();

            i = i + 1;
        }

        ArrayAdapter<String> objCityArr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, City);
        spinner.setAdapter(objCityArr);

    }
    public void FavoriteDestination(){
        DataBaseHelper dataBaseHelper =new DataBaseHelper(getActivity(),"travelers",null,1);
        int  userID= dataBaseHelper.getUserId( SignIn.EmailValue);
        city = (TextView) getActivity().findViewById(R.id.city);
        country = (TextView) getActivity().findViewById(R.id.country);
        Cursor allFavorite = dataBaseHelper.getAllFavorite(userID);
        city.setText(null);
        country.setText(null);
        while (allFavorite.moveToNext()){
            city.append(
                    allFavorite.getString(1)
                            +"\n\n"
            );
            country.append(
                    allFavorite.getString(2)
                            +"\n\n"
            );
        }

    }





}