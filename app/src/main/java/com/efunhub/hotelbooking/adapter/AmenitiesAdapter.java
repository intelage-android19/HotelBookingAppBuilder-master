package com.efunhub.hotelbooking.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.model.AmenitiesModel;
import com.efunhub.hotelbooking.model.HomeFragmentModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.security.Principal;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.ViewHolder>{

    private Context context;
    private Activity   activity ;
    private List<AmenitiesModel> amenitiesModels;

    public AmenitiesAdapter(Context context, List<AmenitiesModel> amenitiesModelList) {

        this.context = context;
        this.amenitiesModels = amenitiesModelList;
    }


    public AmenitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.amenitiescardview, null);

        return new AmenitiesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmenitiesAdapter.ViewHolder viewHolder, int i) {

        AmenitiesModel amenitiesModel = amenitiesModels.get(i);

        viewHolder.txtAmenities.setText(amenitiesModel.getAmenity());


        String imgUrl = context.getResources().getString(R.string.amenities_url)+amenitiesModel.getImage();

        if (!amenitiesModel.getImage().equalsIgnoreCase("")) {

            Picasso.with(context.getApplicationContext())
                    .load(imgUrl)
                    .into(viewHolder.imgAmenities);
        }
        else {

            Picasso.with(context.getApplicationContext())
                    .load(imgUrl)
                    .placeholder(R.drawable.ic_wifi_black_24dp)
                    .into(viewHolder.imgAmenities);
        }
    }


    @Override
    public int getItemCount() {
        return amenitiesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAmenities;
        TextView txtAmenities;


        public ViewHolder(View itemView) {
            super(itemView);

            txtAmenities = itemView.findViewById(R.id.txtamenities);
            imgAmenities = itemView.findViewById(R.id.imgTopamenities);


        }
    }
}
