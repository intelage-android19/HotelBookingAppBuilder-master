package com.efunhub.hotelbooking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.activity.GuestRoomFullGallaryImage;
import com.efunhub.hotelbooking.model.GuestRoomGallaryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GuestFullGalleryAdapter extends RecyclerView.Adapter<GuestFullGalleryAdapter.ViewHolder> {


    private Context mContext;
    List<GuestRoomGallaryModel> guestRoomGallaryModelsList;
    String id;

    ProgressBar pbAddBiike;


    public GuestFullGalleryAdapter(Context context,List<GuestRoomGallaryModel> guestRoomGallaryModelsList) {

        this.mContext = context;

        this.guestRoomGallaryModelsList = guestRoomGallaryModelsList;

    }


    public GuestFullGalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_room_full_image_gallary_adapter, parent, false);
        return new GuestFullGalleryAdapter.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(GuestFullGalleryAdapter.ViewHolder holder, int position) {

        GuestRoomGallaryModel guestRoomGallaryModel = guestRoomGallaryModelsList.get(position);

        String imageURL = mContext.getResources().getString(R.string.guestroom_gallary_images)+guestRoomGallaryModel.getImage();

        Picasso.with(mContext)
                .load(imageURL)
                .into(holder.imghorizontal);


    }


    @Override
    public int getItemCount() {
        return guestRoomGallaryModelsList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imghorizontal, imgDeleteImage;

        public ViewHolder(View itemView) {

            super(itemView);

            imghorizontal = itemView.findViewById(R.id.imgBikeGalary);
            pbAddBiike = itemView.findViewById(R.id.pbDeleteImage);


        }


    }
}