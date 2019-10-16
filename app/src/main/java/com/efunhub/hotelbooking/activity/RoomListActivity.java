package com.efunhub.hotelbooking.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.efunhub.hotelbooking.adapter.RoomListAdapter;
import com.efunhub.hotelbooking.databinding.RoomlistactivityBinding;
import com.efunhub.hotelbooking.model.HomeFragmentModel;
import com.efunhub.hotelbooking.model.RoomListModel;
import com.efunhub.hotelbooking.utility.CheckConnectivity;

import java.util.ArrayList;
import java.util.List;

public class RoomListActivity extends AppCompatActivity  {

    RoomlistactivityBinding mBinder;

    RecyclerView rvRoomsList;
    private RoomListAdapter roomListAdapter;
    private List<RoomListModel> roomList;

    TextView title;

    CheckConnectivity checkConnectivity ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = DataBindingUtil.setContentView(this, R.layout.roomlistactivity);
      //  setupToolBarWithMenu(mBinder.toolbar.toolbar,"Room List");

        checkConnectivity = new CheckConnectivity();
        onClickRvOffers();
    }

    private void onClickRvOffers() {

        rvRoomsList = mBinder.rvRoomsList;
        rvRoomsList.setNestedScrollingEnabled(false);
        roomList = new ArrayList<>();
        roomList = roomList();

        roomListAdapter = new RoomListAdapter(RoomListActivity.this, roomList);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(RoomListActivity.this, LinearLayoutManager.VERTICAL, false);
        rvRoomsList.setLayoutManager(horizontalLayoutManager);
        rvRoomsList.setAdapter(roomListAdapter);
        rvRoomsList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(RoomListActivity.this, new GestureDetector.SimpleOnGestureListener() {

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
                    Intent mIntent = new Intent(RoomListActivity.this, RoomDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("image", String.valueOf(roomList.get(position).getImgRoomList()));
                    mIntent.putExtras(bundle);
                    startActivity(mIntent);
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

    private List<RoomListModel> roomList() {
        roomList.add(new RoomListModel(1, R.drawable.luxury, "Normal Room","24 Sq.mt.","lunch","2"));
        roomList.add(new RoomListModel(2, R.drawable.luxury, "Luxury Room","50 Sq.mt."," Breakfast and lunch","3"));
        roomList.add(new RoomListModel(3, R.drawable.luxury, "Luxury Grande Room City View","100 Sq.mt.","lunch and Dinner","1"));
        roomList.add(new RoomListModel(4, R.drawable.luxury, "Single Room","20 Sq.mt.","lunch","1"));
        roomList.add(new RoomListModel(5, R.drawable.luxury, "Double Room","34 Sq.mt.","lunch","4"));
        roomList.add(new RoomListModel(6, R.drawable.luxury, "King Room","40 Sq.mt.","Breakfast ,Lunch and Dinner","5"));

        return roomList;
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
