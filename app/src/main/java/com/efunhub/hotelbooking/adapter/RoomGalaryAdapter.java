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
import com.efunhub.hotelbooking.activity.RoomDetailsActivity;
import com.efunhub.hotelbooking.model.RooDetailsModel;
import com.efunhub.hotelbooking.model.RoomGalaryModel;

import java.util.List;

public class RoomGalaryAdapter extends RecyclerView.Adapter<RoomGalaryAdapter.ViewHolder>{


    private Activity mActivity;
    private List<RoomGalaryModel> roomGalaryList;
    ImageView imageView ;

    public RoomGalaryAdapter(RoomDetailsActivity roomDetailsActivity, List<RoomGalaryModel> roomGalaryList) {

        this.mActivity = roomDetailsActivity;
        this.roomGalaryList = roomGalaryList;
        this.imageView = mActivity.findViewById(R.id.imgroomdetail);
    }

    @NonNull
    @Override
    public RoomGalaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.roomgalary, null);
        return new RoomGalaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomGalaryAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.imgGallery.setImageResource(roomGalaryList.get(i).imgGallery);

        viewHolder.imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(roomGalaryList.get(i).imgGallery);
            }
        });

    }



    @Override
    public int getItemCount() {
        return roomGalaryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgGallery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGallery = itemView.findViewById(R.id.imgGallery);

        }
    }
}
