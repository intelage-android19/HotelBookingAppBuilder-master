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
import com.efunhub.hotelbooking.model.RoomListModel;

import java.util.List;

public class WalletHistoryAdapter extends RecyclerView.Adapter<WalletHistoryAdapter.ViewHolder>{

    private Context context;
    private List<RoomListModel> roomList;


    public WalletHistoryAdapter(Context context, List<RoomListModel> roomList) {

        this.context = context;
        this.roomList = roomList;
    }


    public WalletHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.wallet_history_adapter, null);
        return new WalletHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletHistoryAdapter.ViewHolder viewHolder, int i) {
        viewHolder.imgWalletHistory.setImageResource(roomList.get(i).imgRoomList);
        viewHolder.txtHistoryName.setText(roomList.get(i).txtRoomType);
        viewHolder.txtHistoryNumber.setText(roomList.get(i).txtRoomSize);
        viewHolder.txtHistoryDate.setText(roomList.get(i).txtRoomLunch);
        viewHolder.txtHistoryPrice.setText(roomList.get(i).txtRoomPeople);
    }


    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgWalletHistory;
        TextView txtHistoryName,txtHistoryNumber,txtHistoryDate,txtHistoryPrice;


        public ViewHolder(View itemView) {
            super(itemView);

            // txtLocationList = itemView.findViewById(R.id.txtLocationName);
            imgWalletHistory = itemView.findViewById(R.id.imgwallet);
            txtHistoryName = itemView.findViewById(R.id.txtHistoryName);
            txtHistoryNumber = itemView.findViewById(R.id.txtHistoryNo);
            txtHistoryDate = itemView.findViewById(R.id.txtHistoryDate);
            txtHistoryPrice = itemView.findViewById(R.id.txtHistoryPrice);



        }
    }
}
