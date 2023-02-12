package com.example.androidproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment implements DestinationAdapter.ItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    DestinationAdapter adapter;
    List<Distination>  dest;
    private   List<Distination> distinationsdata= new ArrayList<>();
    private Spinner travelDSpinnercontent;
    private   Button Serchbtn;
    private   List<Distination> destinationToShow;
    public static Distination selectedDescription;
    public AllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllFragment newInstance(String param1, String param2) {
        AllFragment fragment = new AllFragment();
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
    public void onResume() {
        super.onResume();








        travelDSpinnercontent =(Spinner) getActivity().findViewById(R.id.spinnerContentSelect);
        fillTheMenu(DistinationJsonParser.distinationsdata);


  destinationToShow=HomeFragment.destinationPref;
        TextView continetName=(TextView) getActivity().findViewById(R.id.textcontintvaluename);



        continetName.setText(destinationToShow.get(0).getContinent());
                dest=destinationToShow;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DestinationAdapter(getContext(), dest);
        adapter.setClickListener(AllFragment.this);
        recyclerView.setAdapter(adapter);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView =view.findViewById(R.id.idRVCourse);




        return view;

    }


    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Serchbtn=(Button) getActivity().findViewById(R.id.SearchContent);

        TextView continetName=(TextView) getActivity().findViewById(R.id.textcontintvaluename);

        Serchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String distnationVlaue=travelDSpinnercontent.getSelectedItem().toString();
                List<Distination>  destinationPrefcontent=new ArrayList<>();
                if (!(distnationVlaue.equals("Select Continente")))

                {
                    for (int i=0;i<distinationsdata.size();i++){
                        if (distinationsdata.get(i).getContinent().equals(distnationVlaue)){
                            destinationPrefcontent.add(distinationsdata.get(i));
                        }

                    }
                    Collections.copy(destinationToShow, destinationPrefcontent);

                    continetName.setText(destinationToShow.get(0).getContinent());
                    adapter.setdestinationData(destinationToShow);
                }
                else{
                    Toast.makeText(getActivity(), "You Should Select a Continente",
                            Toast.LENGTH_LONG).show();

                }

            }
        });

    }
    @Override
    public void onItemClick(View view, int position) {
        selectedDescription=destinationToShow.get(position);
        Intent intent = new Intent(getActivity(),DestinationActivity.class);
        startActivity(intent);

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
            options.add("Select Continente");
            for (int i=0;i<size;i++){
                    options.add(temp[i]);
                }

            String [] destinationOptions= new String[options.size()];
            options.toArray(destinationOptions);

            ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, destinationOptions);
            travelDSpinnercontent.setAdapter(objGenderArr);

        }
    }



}