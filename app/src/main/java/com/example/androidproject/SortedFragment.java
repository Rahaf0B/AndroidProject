package com.example.androidproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SortedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SortedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SortedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SortedFragment newInstance(String param1, String param2) {
        SortedFragment fragment = new SortedFragment();
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

        return inflater.inflate(R.layout.fragment_sorted, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Distination> distinations = DistinationJsonParser.distinationsdata;
        TextView text_city = (TextView) getActivity().findViewById(R.id.City);
        TextView text_cost = (TextView) getActivity().findViewById(R.id.Cost);
        Button ascending_button =(Button) getActivity().findViewById(R.id.buttonsae);
        Button descending_button =(Button) getActivity().findViewById(R.id.buttondes);
        ImageView animationImageView = (ImageView) getActivity().findViewById(R.id.dollarsignimg);
        ImageView costImageView = (ImageView) getActivity().findViewById(R.id.imageViewcost);
        TextView textViewdis =(TextView) getActivity().findViewById(R.id.textViewdis);
        LinearLayout linearlay=(LinearLayout) getActivity().findViewById(R.id.linearlay);
        RelativeLayout animationlayout=(RelativeLayout) getActivity().findViewById(R.id.animationlayout);


        if (distinations!=null) {
            Collections.sort(distinations, Distination.ascending_order);
            for (int i = 0; i < distinations.size(); i++) {
                text_city.append(distinations.get(i).getCity());
                text_city.append("\n\n");
                text_cost.append(String.valueOf(distinations.get(i).getCost()));
                text_cost.append("\n\n");
            }
        }
        animationlayout.setVisibility(View.GONE);
        animationImageView.setVisibility(View.GONE);
        ascending_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animationlayout.setVisibility(View.VISIBLE);
                animationImageView.setVisibility(View.VISIBLE);

                textViewdis.setVisibility(View.GONE);
                costImageView.setVisibility(View.GONE);
                linearlay.setVisibility(View.GONE);
                ascending_button.setVisibility(View.GONE);
                descending_button.setVisibility(View.GONE);


                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.ascendinganimation);

                animationImageView.startAnimation(animation);


                new CountDownTimer(2500, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        animationlayout.setVisibility(View.GONE);
                        animationImageView.setVisibility(View.GONE);
                        textViewdis.setVisibility(View.VISIBLE);
                        costImageView.setVisibility(View.VISIBLE);
                        linearlay.setVisibility(View.VISIBLE);
                        ascending_button.setVisibility(View.VISIBLE);
                        descending_button.setVisibility(View.VISIBLE);
                    }
                }.start();






                text_city.setText(null);
                text_cost.setText(null);
                if (distinations!=null) {
                    Collections.sort(distinations, Distination.ascending_order);
                    for (int i = 0; i < distinations.size(); i++) {
                        text_city.append(distinations.get(i).getCity());
                        text_city.append("\n\n");
                        text_cost.append(String.valueOf(distinations.get(i).getCost()));
                        text_cost.append("\n\n");
                    }
                }
            } });

        descending_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                animationlayout.setVisibility(View.VISIBLE);
                animationImageView.setVisibility(View.VISIBLE);

                textViewdis.setVisibility(View.GONE);
                costImageView.setVisibility(View.GONE);
                linearlay.setVisibility(View.GONE);
                ascending_button.setVisibility(View.GONE);
                descending_button.setVisibility(View.GONE);


                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.descendinganimation);

                animationImageView.startAnimation(animation);


                new CountDownTimer(3000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        animationlayout.setVisibility(View.GONE);
                        animationImageView.setVisibility(View.GONE);
                        textViewdis.setVisibility(View.VISIBLE);
                        costImageView.setVisibility(View.VISIBLE);
                        linearlay.setVisibility(View.VISIBLE);
                        ascending_button.setVisibility(View.VISIBLE);
                        descending_button.setVisibility(View.VISIBLE);
                    }
                }.start();




                text_city.setText(null);
                text_cost.setText(null);
                if (distinations!=null) {
                    Collections.sort(distinations, Distination.descending_order);
                    for (int i = 0; i < distinations.size(); i++) {
                        text_city.append(distinations.get(i).getCity());
                        text_city.append("\n\n");
                        text_cost.append(String.valueOf(distinations.get(i).getCost()));
                        text_cost.append("\n\n");
                    }
                }
            } });

    }





}