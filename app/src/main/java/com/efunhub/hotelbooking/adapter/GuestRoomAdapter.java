package com.efunhub.hotelbooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.activity.GuestRoomDetailsActivity;
import com.efunhub.hotelbooking.activity.RoomDetailsActivity;
import com.efunhub.hotelbooking.model.GuestRoomModel;
import com.efunhub.hotelbooking.model.HomeFragmentModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GuestRoomAdapter  extends RecyclerView.Adapter<GuestRoomAdapter.ViewHolder>{

    private Context context;
    private List<GuestRoomModel> guestRoomModelList;


    public GuestRoomAdapter(Context context, List<GuestRoomModel> guestRoomModel) {

        this.context = context;
        this.guestRoomModelList = guestRoomModel;
    }


    public GuestRoomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gustroomcardview, null);
        return new GuestRoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestRoomAdapter.ViewHolder viewHolder, int i) {

        GuestRoomModel guestRoomModel = guestRoomModelList.get(i);

        String imgUrl = context.getResources().getString(R.string.guestRoom_image_url)+guestRoomModel.getRoomBanner();

        String    desc = guestRoomModel.getDescriptions();
        Spanned htmlAsDesc = Html.fromHtml(desc);

        viewHolder.txtGuestRoom.setText(guestRoomModel.getRoomType());
        viewHolder.txtGuestRoomDesc.setText(htmlAsDesc+"...");
        viewHolder.txtGuestUpTo.setText("Up to " +guestRoomModel.getUptoGuest());
        viewHolder.txtPrice.setText("₹" + " "+guestRoomModel.getTotalPayable()+ " "+"/-");

        if (guestRoomModel.getTaxesPrice()!=null) {
            viewHolder.txtTaxes.setText("including " + " " + "₹" + guestRoomModel.getTaxesPrice() + " " + "taxes");
        }else {
            viewHolder.txtTaxes.setText("including " + " " + "₹" + "0" + " " + "taxes");

        }


        viewHolder.txtDiscount.setText(guestRoomModel.getDiscountPrice());

        Picasso.with(context.getApplicationContext())
                .load(imgUrl)
                .into(viewHolder.imgGuestRoom);


    }


    @Override
    public int getItemCount() {

        return guestRoomModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGuestRoom;
        TextView txtGuestRoom,txtGuestRoomDesc,txtGuestUpTo,txtPrice,txtTaxes,txtDiscount;


        public ViewHolder(View itemView) {
            super(itemView);
            imgGuestRoom = itemView.findViewById(R.id.imgGuestRoom);
            txtGuestRoom = itemView.findViewById(R.id.txtGuestRoom);
            txtGuestRoomDesc = itemView.findViewById(R.id.txtGuestDescription);
            txtGuestUpTo = itemView.findViewById(R.id.txtGuestUpTo);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtTaxes = itemView.findViewById(R.id.txtTaxes);
            txtDiscount = itemView.findViewById(R.id.txtDiscount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    GuestRoomModel guestRoomModel = guestRoomModelList.get(getAdapterPosition());

                    Intent intent = new Intent(context, GuestRoomDetailsActivity.class);

                    intent.putExtra("id",guestRoomModel.getRoomId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
