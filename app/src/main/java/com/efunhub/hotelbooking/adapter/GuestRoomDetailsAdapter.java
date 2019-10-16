package com.efunhub.hotelbooking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.activity.GuestRoomDetailsActivity;
import com.efunhub.hotelbooking.activity.RoomDetailsActivity;
import com.efunhub.hotelbooking.model.RooDetailsModel;

import java.util.List;

public class GuestRoomDetailsAdapter extends RecyclerView.Adapter<GuestRoomDetailsAdapter.ViewHolder>{


    private Context context;
    private List<RooDetailsModel> roomDetailsList;

    public GuestRoomDetailsAdapter(GuestRoomDetailsActivity guestRoomDetailsActivity, List<RooDetailsModel> roomDetailsList) {

        this.context = guestRoomDetailsActivity;
        this.roomDetailsList = roomDetailsList;
    }

    @NonNull
    @Override
    public GuestRoomDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.roomdetailscardview, null);
        return new GuestRoomDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestRoomDetailsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.imgLocationList.setImageResource(roomDetailsList.get(i).imgamenities);
        viewHolder.txtamenities.setText(roomDetailsList.get(i).txtamenities);



    }



    @Override
    public int getItemCount() {
        return roomDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgLocationList;
        TextView txtamenities;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLocationList = itemView.findViewById(R.id.imgamenities);
            txtamenities = itemView.findViewById(R.id.txtamenities);

        }
    }
}
