package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        Button fragmentImagebtn=(Button) findViewById(R.id.buttonImgOpen);
        Button fragmentDescriptionbtn=(Button) findViewById(R.id.buttonDescriptionOpen);
        Button mapOpen=(Button) findViewById(R.id.buttonLocationOpen);
        ImageButton btn_back=(ImageButton) findViewById(R.id.backbtnFragment) ;



        final imageFragment fragmentImage = new imageFragment();
        final DescriptionFragment fragmentDescription = new DescriptionFragment();

        TextView cityName=(TextView) findViewById(R.id.textCotyNameDestination);
        cityName.setText(AllFragment.selectedDescription.getCity());


        ImageView ddd=(ImageView) findViewById(R.id.ddd);



        fragmentImagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                if (fragmentImagebtn.getText().toString().equals("Show Image")){
    fragmentImagebtn.setText("Hide Image");

    fragmentTransaction.replace(R.id.frameimage, fragmentImage);
    Bundle imgBundle = new Bundle();
    imgBundle.putString("img",AllFragment.selectedDescription.getImg());
    fragmentImage.setArguments(imgBundle);
    fragmentTransaction.commit();
}
              else {
    fragmentImagebtn.setText("Show Image");
    fragmentTransaction.remove(fragmentImage);
                    fragmentTransaction.commit();
              }

            }
        });



        fragmentDescriptionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                if (fragmentDescriptionbtn.getText().toString().equals("Show Description")) {
                    fragmentDescriptionbtn.setText("Hide Description");
                    fragmentTransaction.replace(R.id.frameDescription, fragmentDescription);
                    Bundle imgBundle = new Bundle();
                    imgBundle.putString("description", AllFragment.selectedDescription.getDescription());
                    fragmentDescription.setArguments(imgBundle);
                    fragmentTransaction.commit();
                } else {
fragmentDescriptionbtn.setText("Show Description");
fragmentTransaction.remove(fragmentDescription);
                    fragmentTransaction.commit();

                }
            }
        });


        mapOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapsIntent =new Intent();

                mapsIntent.setAction(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:"+AllFragment.selectedDescription.getLatitude()+","+AllFragment.selectedDescription.getLongitude()));

                startActivity(mapsIntent);
            }
        });



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DestinationActivity.this,NavigationDrawerActivity.class);
                DestinationActivity.this.startActivity(intent);
                finish();



            }
        });
    }
}