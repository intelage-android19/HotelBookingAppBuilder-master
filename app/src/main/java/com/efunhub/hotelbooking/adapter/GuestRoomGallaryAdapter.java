package com.efunhub.hotelbooking.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.activity.GuestRoomDetailsActivity;
import com.efunhub.hotelbooking.activity.GuestRoomFullGallaryImage;
import com.efunhub.hotelbooking.activity.RoomDetailsActivity;
import com.efunhub.hotelbooking.interfaces.IResult;
import com.efunhub.hotelbooking.model.GuestRoomGallaryModel;
import com.efunhub.hotelbooking.model.GuestRoomModel;
import com.efunhub.hotelbooking.model.RoomGalaryModel;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GuestRoomGallaryAdapter extends RecyclerView.Adapter<GuestRoomGallaryAdapter.ViewHolder> {


    private Context context;
    List<GuestRoomGallaryModel> guestRoomGallaryModelsList;
    ImageView imageView;
    Integer limit = 3;
    TextView txtMoreImg;

    public GuestRoomGallaryAdapter(Context context, List<GuestRoomGallaryModel> guestRoomGallaryModelsList, ImageView imgRoomDetails) {

        this.context = context;
        this.guestRoomGallaryModelsList = guestRoomGallaryModelsList;
        this.imageView = imgRoomDetails;

    }


    @NonNull
    @Override
    public GuestRoomGallaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.roomgalary, null);
        return new GuestRoomGallaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GuestRoomGallaryAdapter.ViewHolder viewHolder, int i) {

        GuestRoomGallaryModel guestRoomGallaryModel = guestRoomGallaryModelsList.get(i);

        String imageURL = context.getResources().getString(R.string.guestroom_gallary_images) + guestRoomGallaryModel.getImage();

        Picasso.with(context)
                .load(imageURL)
                .into(imageView);

        txtMoreImg.setText("More" + " " + guestRoomGallaryModelsList.size());

        Picasso.with(context)
                .load(imageURL)
                .into(viewHolder.imgGallery);

        if (i==2){
            txtMoreImg.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public int getItemCount() {
        if (guestRoomGallaryModelsList.size() > limit) {

            return limit;
        }
        else {
            return guestRoomGallaryModelsList.size();

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgGallery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGallery = itemView.findViewById(R.id.imgGallery);
            txtMoreImg = itemView.findViewById(R.id.txtMoreImg);


            txtMoreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(context, GuestRoomFullGallaryImage.class);
                    intent.putExtra("list", (Serializable) guestRoomGallaryModelsList);
                    context.startActivity(intent);


                }
            });

            imgGallery.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    GuestRoomGallaryModel guestRoomGallaryModel = guestRoomGallaryModelsList.get(getAdapterPosition());

                    String imageURL = context.getResources().getString(R.string.guestroom_gallary_images) + guestRoomGallaryModel.getImage();

                    Picasso.with(context)
                            .load(imageURL)
                            .into(imageView);
                }
            });

        }
    }
}
