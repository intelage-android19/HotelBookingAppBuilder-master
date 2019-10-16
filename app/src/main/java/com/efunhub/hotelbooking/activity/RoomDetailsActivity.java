package com.efunhub.hotelbooking.activity;

import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
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

import com.efunhub.hotelbooking.adapter.RoomGalaryAdapter;
import com.efunhub.hotelbooking.model.CategoryModel;
import com.efunhub.hotelbooking.adapter.MainCategoryRVAdapter;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.model.RooDetailsModel;
import com.efunhub.hotelbooking.adapter.RoomDetailsAdapter;
import com.efunhub.hotelbooking.adapter.SliderImageAdapter;
import com.efunhub.hotelbooking.model.RoomGalaryModel;
import com.efunhub.hotelbooking.model.SliderModel;
import com.efunhub.hotelbooking.databinding.RoomdetailsactivityBinding;
import com.efunhub.hotelbooking.utility.CheckConnectivity;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsActivity extends AppCompatActivity {

    private RoomdetailsactivityBinding mBinder;

    RecyclerView rvTopAmenities;
    RecyclerView rvRoomGallary;

    RoomGalaryAdapter roomGallaryAdapter ;
    List<RoomGalaryModel> roomGalaryList ;


    RoomDetailsAdapter roomDetailsAdapter ;
    List<RooDetailsModel> roomDetailsList ;

    ImageView imgRoomDetails;
    String img;

    CheckConnectivity checkConnectivity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = DataBindingUtil.setContentView(this, R.layout.roomdetailsactivity);

        img = getIntent().getExtras().getString("image");

        imgRoomDetails = mBinder.imgroomdetail;

        checkConnectivity = new CheckConnectivity();
        imgRoomDetails.setImageResource(Integer.parseInt(img));

        onClickRvRoomDetails();
        onClickRvRoomGallary();

    }

    private void onClickRvRoomGallary() {

        rvRoomGallary = mBinder.rvimgRoomGallary;
        roomGalaryList = new ArrayList<>();
        roomGallaryAdapter = new RoomGalaryAdapter(this, roomGalaryList);
        roomGalaryList = RoomGalaryList();

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRoomGallary.setLayoutManager(horizontalLayoutManager);
        rvRoomGallary.setAdapter(roomGallaryAdapter);
        rvRoomGallary.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(RoomDetailsActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent e) {

                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = recyclerView.getChildAdapterPosition(child);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

    }

    private List<RoomGalaryModel> RoomGalaryList() {
        roomGalaryList.add(new RoomGalaryModel(1, R.drawable.normalroom));
        roomGalaryList.add(new RoomGalaryModel(2, R.drawable.king));
        roomGalaryList.add(new RoomGalaryModel(3, R.drawable.singleroom));
        roomGalaryList.add(new RoomGalaryModel(4, R.drawable.luxury));


        return roomGalaryList;
    }



    private void onClickRvRoomDetails() {

        rvTopAmenities = mBinder.rvTopAmenities;
        roomDetailsList = new ArrayList<>();
        roomDetailsAdapter = new RoomDetailsAdapter(this, roomDetailsList);
        roomDetailsList = roomDetailsList();

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvTopAmenities.setLayoutManager(horizontalLayoutManager);
        rvTopAmenities.setAdapter(roomDetailsAdapter);
        rvTopAmenities.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(RoomDetailsActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent e) {

                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = recyclerView.getChildAdapterPosition(child);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

    }

    private List<RooDetailsModel> roomDetailsList() {
        roomDetailsList.add(new RooDetailsModel(1, R.drawable.ic_wifi_black_24dp, "Wi-Fi"));
        roomDetailsList.add(new RooDetailsModel(2, R.drawable.ic_ac_unit_black_24dp, "A/C"));
        roomDetailsList.add(new RooDetailsModel(3, R.drawable.ic_pool_black_24dp, "Pool"));
        roomDetailsList.add(new RooDetailsModel(4, R.drawable.gym, "Gym"));


        return roomDetailsList;
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
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
