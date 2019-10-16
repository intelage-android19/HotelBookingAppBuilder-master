package com.efunhub.hotelbooking.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.adapter.AmenitiesAdapter;
import com.efunhub.hotelbooking.adapter.GuestRoomAdapter;
import com.efunhub.hotelbooking.adapter.GuestRoomAmenitiesAdapter;
import com.efunhub.hotelbooking.adapter.GuestRoomDetailsAdapter;
import com.efunhub.hotelbooking.adapter.GuestRoomGallaryAdapter;
import com.efunhub.hotelbooking.adapter.RoomDetailsAdapter;
import com.efunhub.hotelbooking.adapter.RoomGalaryAdapter;
import com.efunhub.hotelbooking.databinding.RoomdetailsactivityBinding;
import com.efunhub.hotelbooking.interfaces.IResult;
import com.efunhub.hotelbooking.model.AmenitiesBaseModel;
import com.efunhub.hotelbooking.model.AmenitiesModel;
import com.efunhub.hotelbooking.model.GuestRoomAmenitiesModel;
import com.efunhub.hotelbooking.model.GuestRoomBaseClass;
import com.efunhub.hotelbooking.model.GuestRoomGallaryModel;
import com.efunhub.hotelbooking.model.GuestRoomModel;
import com.efunhub.hotelbooking.model.RooDetailsModel;
import com.efunhub.hotelbooking.model.RoomGalaryModel;
import com.efunhub.hotelbooking.utility.CheckConnectivity;
import com.efunhub.hotelbooking.utility.SessionManager;
import com.efunhub.hotelbooking.utility.VolleyService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.efunhub.hotelbooking.utility.ConstantVariables.AMENITIES_LIST;
import static com.efunhub.hotelbooking.utility.ConstantVariables.GUEST_ROOM;

public class GuestRoomDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvTopAmenities;
    RecyclerView rvRoomGallary;

    GuestRoomGallaryAdapter guestRoomGallaryAdapter;
    List<GuestRoomGallaryModel> guestRoomGallaryModelsList = new ArrayList<>();

    GuestRoomAmenitiesAdapter guestRoomAmenitiesAdapter;
    List<GuestRoomAmenitiesModel> guestRoomAmenitiesModelList = new ArrayList<>();

    GuestRoomDetailsAdapter roomDetailsAdapter;
    List<RooDetailsModel> roomDetailsList;

    GuestRoomBaseClass guestRoomBaseClass;
    List<GuestRoomModel> guestRoomModelList = new ArrayList<>();


    ImageView imgRoomDetails;
    String desc,id,includedData,price,discount,guestUpTo;

    //Volley service
    private IResult mResultCallback;
    private VolleyService mVolleyService;

    SessionManager sessionManager;

    String guestRoomsURL = "ShowHotelRooms";

    CheckConnectivity checkConnectivity;

    TextView txtGuestRoomDescription,tvIncludedData,txtPrice,txtDiscount,txtGuestUpTo;
    Button btnBuyNowGuestRoom;

    LinearLayout relativeCheckIn, relativeCheckOut, linearEnqiry, linearEmailUs, linearCallUs;

    LinearLayout relativeAdults, relativeChildrens;


    private DatePickerDialog.OnDateSetListener checkInDateSetListener;
    private DatePickerDialog.OnDateSetListener chekOutDateListener;

    int priceAdults = (int) 00;
    int priceChildrens = (int) 00;

    TextView txtCheckIn, txtCheckOut, txtAdults, txtxChildrens, txtAdultsIncrement, txtChildrensIncrement, txtAdultsDecrement, txtChildrensDecrement;
    Button btnSeeDetails;
    ImageView icBack;
    Toolbar toolbar ;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gest_room_details);

        setupToolbar();

        imgRoomDetails = findViewById(R.id.imgroomdetail);
        rvRoomGallary = findViewById(R.id.rvimgRoomGallary);
        rvTopAmenities = findViewById(R.id.rvTopAmenities);
        txtGuestRoomDescription = findViewById(R.id.txtGuestRoomDescription);
        tvIncludedData = findViewById(R.id.tvIncludedData);
        txtPrice = findViewById(R.id.txtPrice);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtGuestUpTo = findViewById(R.id.txtGuestUpTo);
        btnBuyNowGuestRoom = findViewById(R.id.btnBuyNowGuestRoom);


        checkConnectivity = new CheckConnectivity();


         id = getIntent().getStringExtra("id");

        btnBuyNowGuestRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildDialog(R.style.DialogAnimation);

            }
        });

        getGuestRooms();


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


    private void getGuestRooms() {


        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback, this);

        mVolleyService.getDataVolley(GUEST_ROOM, this.getResources().getString(R.string.base_url) + guestRoomsURL);
    }


    private void initVolleyCallback() {

        //    linearProgressBar.setVisibility(View.VISIBLE);

        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(int requestId, String response) {
                JSONObject jsonObj = null;

                switch (requestId) {

                    case GUEST_ROOM:
                        try{ jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");

                            if (status == 1) {

                                Gson gson = new Gson();

                                guestRoomBaseClass = gson.fromJson(
                                        response, GuestRoomBaseClass.class);

                                List<GuestRoomModel> guestRoomModels = guestRoomBaseClass.getRoomsData();


                                for (GuestRoomModel guestRoomModel : guestRoomModels) {

                                    if (id.equals(guestRoomModel.getRoomId())) {

                                        guestRoomModelList.add(guestRoomModel);

                                        desc = guestRoomModel.getDescriptions();
                                        txtGuestRoomDescription.setText(Html.fromHtml(desc).toString().replaceAll("\n", "").trim());

                                     //   txtGuestRoomDescription.setText(htmlAsDesc);

                                        guestUpTo = String.valueOf(guestRoomModel.getUptoGuest());
                                        txtGuestUpTo.setText("Up to "+guestUpTo + " "+"guests");

                                        price = String.valueOf(guestRoomModel.getTotalPayable());
                                        txtPrice.setText("₹"+  " " +price+"/-");


                                        discount = String.valueOf(guestRoomModel.getDiscountPrice());
                                        txtDiscount.setText("₹"+  " " +discount+"/-");



                                        includedData = guestRoomModel.getIncludedInprice();
                                        Spanned htmlasIncludedData = Html.fromHtml(includedData);
                                      //  tvIncludedData.setText(htmlasIncludedData);

                                        tvIncludedData.setText(Html.fromHtml(includedData));

                                        guestRoomGallaryModelsList = guestRoomModels.get(0).getAllGallery();

                                        guestRoomGallaryAdapter = new GuestRoomGallaryAdapter(GuestRoomDetailsActivity.this, guestRoomGallaryModelsList, imgRoomDetails);
                                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(GuestRoomDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                        rvRoomGallary.setLayoutManager(horizontalLayoutManager);
                                        rvRoomGallary.setAdapter(guestRoomGallaryAdapter);


                                        //Amenities

                                        guestRoomAmenitiesModelList = guestRoomModels.get(0).getAllAmenities();

                                        guestRoomAmenitiesAdapter = new GuestRoomAmenitiesAdapter(GuestRoomDetailsActivity.this, guestRoomAmenitiesModelList);
                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GuestRoomDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                        rvTopAmenities.setLayoutManager(linearLayoutManager);
                                        rvTopAmenities.setAdapter(guestRoomAmenitiesAdapter);


                                    }
                                }

                                }

                            if (guestRoomModelList.isEmpty()) {
                                //  imgNoDataFound.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {

                            Log.v("Customer Activity", e.toString());
                        }

                        break;

                }
                // linearProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void notifyError(int requestId, VolleyError error) {
                System.out.println(error);
                //   imgNoDataFound.setVisibility(View.VISIBLE);
                //Toast.makeText(CustomerListAtivity.this, "Something went wrong. Please try again !!!", Toast.LENGTH_LONG).show();
                Log.v("Volley requestid", String.valueOf(requestId));
                Log.v("Volley Error", String.valueOf(error));
                //       linearProgressBar.setVisibility(View.GONE);

            }
        };
    }

    private void buildDialog(int animationSource) {

        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.booknowdetailsactivity);

        dialog.setCanceledOnTouchOutside(true);

        relativeCheckIn = dialog.findViewById(R.id.linearCheckIn);
        relativeCheckOut = dialog.findViewById(R.id.linearCheckOut);
        txtCheckIn = dialog.findViewById(R.id.txtCheckIn);
        txtCheckOut = dialog.findViewById(R.id.txtCheckOut);
        relativeAdults = dialog.findViewById(R.id.rlAdult);
        relativeChildrens = dialog.findViewById(R.id.rlChildrens);
        txtAdults = dialog.findViewById(R.id.txtAdult);
        txtxChildrens = dialog.findViewById(R.id.txtChildrens);
        txtAdultsIncrement = dialog.findViewById(R.id.txtAdultsInc);
        txtChildrensIncrement = dialog.findViewById(R.id.txtChildrensInc);
        txtAdultsDecrement = dialog.findViewById(R.id.txtAdultsDec);
        txtChildrensDecrement = dialog.findViewById(R.id.txtChildrensDec);
        btnSeeDetails = dialog.findViewById(R.id.btnSeeDetails);

        dialog.getWindow().getAttributes().windowAnimations = animationSource;

        dialog.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
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
