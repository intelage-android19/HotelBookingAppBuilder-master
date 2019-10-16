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
import com.efunhub.hotelbooking.model.HomeFragmentModel;
import com.efunhub.hotelbooking.model.RoomListModel;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder>{

    private Context context;
    private List<RoomListModel> roomList;


    public RoomListAdapter(Context context, List<RoomListModel> roomList) {

        this.context = context;
        this.roomList = roomList;
    }


    public RoomListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.roomlistcardview, null);
        return new RoomListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.imgLocationList.setImageResource(roomList.get(i).imgRoomList);
        viewHolder.txtRoomType.setText(roomList.get(i).txtRoomType);
        viewHolder.txtRoomSize.setText(roomList.get(i).txtRoomSize);
        viewHolder.txtRoomLunch.setText(roomList.get(i).txtRoomLunch);
        viewHolder.txtRoomPeople.setText(roomList.get(i).txtRoomPeople);
    }


    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLocationList;
        TextView txtRoomSize,txtRoomType,txtRoomLunch,txtRoomPeople;


        public ViewHolder(View itemView) {
            super(itemView);

            // txtLocationList = itemView.findViewById(R.id.txtLocationName);
            imgLocationList = itemView.findViewById(R.id.imgGuestRoom);
            txtRoomType = itemView.findViewById(R.id.txtRoomType);
            txtRoomSize = itemView.findViewById(R.id.txtRoomSize);
            txtRoomLunch = itemView.findViewById(R.id.txtRoomLunch);
            txtRoomPeople = itemView.findViewById(R.id.txtRoomPeople);

/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HomeFragmentModel homeFragmentModel = locationList.get(getAdapterPosition());

                    Intent intent = new Intent(context, HotelListActivity.class);
                    context.startActivity(intent);
                }
            });
*/

        }
    }
}
