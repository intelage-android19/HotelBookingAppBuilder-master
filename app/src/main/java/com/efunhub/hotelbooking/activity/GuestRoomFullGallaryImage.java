package com.efunhub.hotelbooking.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.adapter.GuestFullGalleryAdapter;
import com.efunhub.hotelbooking.adapter.RoomListAdapter;
import com.efunhub.hotelbooking.databinding.RoomlistactivityBinding;
import com.efunhub.hotelbooking.model.GuestRoomGallaryModel;
import com.efunhub.hotelbooking.model.RoomListModel;
import com.efunhub.hotelbooking.utility.CheckConnectivity;

import java.util.ArrayList;
import java.util.List;

public class GuestRoomFullGallaryImage extends AppCompatActivity  {


    RecyclerView rvGuestRoomGallery;

    private GuestFullGalleryAdapter guestFullGalleryAdapter;

    List<GuestRoomGallaryModel> guestRoomGallaryModelsList;

    TextView title;

    CheckConnectivity checkConnectivity;

    Intent intent = getIntent();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_room_full_image_gallary);

        checkConnectivity = new CheckConnectivity();

        guestRoomGallaryModelsList = (List<GuestRoomGallaryModel>)getIntent().getExtras().getSerializable("list");

        setupToolbar();
        onClickRvOffers();
    }


    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Geaust Room Details");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();            }
        });

    }



    private void onClickRvOffers() {

        rvGuestRoomGallery = findViewById(R.id.rvGuestRoomGallery);

        guestFullGalleryAdapter = new GuestFullGalleryAdapter(GuestRoomFullGallaryImage.this, guestRoomGallaryModelsList);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(GuestRoomFullGallaryImage.this, LinearLayoutManager.VERTICAL, false);
        rvGuestRoomGallery.setLayoutManager(horizontalLayoutManager);
        rvGuestRoomGallery.setAdapter(guestFullGalleryAdapter);



    }



    public void onResume() {
        super.onResume();

        //Check connectivity

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(checkConnectivity, intentFilter);

    }

    @Override
    public void onPause() {
        super.onPause();

        if (checkConnectivity != null) {
            this.unregisterReceiver(checkConnectivity);
        }
    }

}
