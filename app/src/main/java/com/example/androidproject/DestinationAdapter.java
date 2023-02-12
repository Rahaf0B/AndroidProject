package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestinationAdapter  extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener itemClickListener;
    private  List<Distination> destinations;
    // data is passed into the constructor
    DestinationAdapter(Context context, List<Distination>  data){
        this.mInflater = LayoutInflater.from(context);
        this.destinations = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_destination, parent, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Distination destination = destinations.get(position);
       if (destination!=null) {
           holder.cityText.setText(destination.getCity());
           holder.countryText.setText(destination.getCountry());
       }

    }


    @Override
    public int getItemCount() {
        return destinations.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView cityText;
        private final  TextView countryText;
        private final ImageButton image;
        private int userID;
        ViewHolder(View itemView) {
            super(itemView);
            DataBaseHelper dataBaseHelper =new DataBaseHelper(itemView.getContext(),"travelers",null,1);
            String EmailValue= SignIn.EmailValue;
            userID= dataBaseHelper.getUserId(EmailValue);
            cityText = itemView.findViewById(R.id.textCityName);
            countryText = itemView.findViewById(R.id.textCountryName);
            image = itemView.findViewById(R.id.buttonFavorite);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dataBaseHelper.CheckIfFavoriteIsExist(cityText.getText().toString(),userID)){


                        Toast.makeText(itemView.getContext(), "This City is Exist in your Favorite",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        dataBaseHelper.insertFavorite(cityText.getText().toString(),countryText.getText().toString(),userID);
                        dataBaseHelper.getAllFavorite(userID);
                    }
                }
            });


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setdestinationData(List<Distination> destinationdata) {

        this.destinations = destinationdata;
        notifyDataSetChanged();
    }

    String getItem(int id) {
        return destinations.get(id).getCity();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}