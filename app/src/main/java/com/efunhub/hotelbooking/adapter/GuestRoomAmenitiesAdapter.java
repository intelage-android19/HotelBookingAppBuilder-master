package com.efunhub.hotelbooking.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.activity.GuestRoomDetailsActivity;
import com.efunhub.hotelbooking.model.AmenitiesModel;
import com.efunhub.hotelbooking.model.GuestRoomAmenitiesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GuestRoomAmenitiesAdapter extends RecyclerView.Adapter<GuestRoomAmenitiesAdapter.ViewHolder>{

    private Context context;
    List<GuestRoomAmenitiesModel> guestRoomAmenitiesModelList;

    public GuestRoomAmenitiesAdapter(GuestRoomDetailsActivity context, List<GuestRoomAmenitiesModel> guestRoomAmenitiesModel) {
        this.context = context;
        this.guestRoomAmenitiesModelList = guestRoomAmenitiesModel;

    }


    public GuestRoomAmenitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.guest_amenities_cardview, null);

        return new GuestRoomAmenitiesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestRoomAmenitiesAdapter.ViewHolder viewHolder, int i) {

        GuestRoomAmenitiesModel guestRoomAmenitiesModel = guestRoomAmenitiesModelList.get(i);

        viewHolder.txtAmenities.setText(guestRoomAmenitiesModel.getAmenityName());


        String imgUrl = context.getResources().getString(R.string.guestroom_amenities_url)+guestRoomAmenitiesModel.getAmenityIcon();

        if (!guestRoomAmenitiesModel.getAmenityIcon().equalsIgnoreCase("")) {

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
        return guestRoomAmenitiesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAmenities;
        TextView txtAmenities,txtGuestRoomDescription;


        public ViewHolder(View itemView) {
            super(itemView);

            txtAmenities = itemView.findViewById(R.id.txtamenities);
            imgAmenities = itemView.findViewById(R.id.imgTopamenities);


        }
    }
}
