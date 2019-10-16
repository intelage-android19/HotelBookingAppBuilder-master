package com.efunhub.hotelbooking.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.model.GuestRoomBaseClass;
import com.efunhub.hotelbooking.model.GuestRoomModel;
import com.efunhub.hotelbooking.utility.SessionManager;
import com.efunhub.hotelbooking.utility.VolleyService;
import com.efunhub.hotelbooking.activity.MainActivity;
import com.efunhub.hotelbooking.activity.RoomListActivity;
import com.efunhub.hotelbooking.adapter.AmenitiesAdapter;
import com.efunhub.hotelbooking.adapter.GuestRoomAdapter;
import com.efunhub.hotelbooking.databinding.HomefragmentBinding;
import com.efunhub.hotelbooking.interfaces.IResult;
import com.efunhub.hotelbooking.model.AmenitiesBaseModel;
import com.efunhub.hotelbooking.model.AmenitiesModel;
import com.efunhub.hotelbooking.model.HomeFragmentModel;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;
import static com.efunhub.hotelbooking.utility.ConstantVariables.AMENITIES_LIST;
import static com.efunhub.hotelbooking.utility.ConstantVariables.GUEST_ROOM;

public class HomeFragment extends Fragment {

    private HomefragmentBinding mBinder;

    public Activity mActivity;

    Context mContext;

    RecyclerView rvGuestRoom, rvAmenities;
    Button btnBookNow;

    private AmenitiesAdapter amenitiesAdapter;

    private GuestRoomAdapter guestRoomAdapter;

    LinearLayout relativeAdults, relativeChildrens;

    LinearLayout relativeCheckIn, relativeCheckOut, linearEnqiry, linearEmailUs, linearCallUs;

    Button btnSeeDetails;


    private DatePickerDialog.OnDateSetListener checkInDateSetListener;
    private DatePickerDialog.OnDateSetListener chekOutDateListener;

    int priceAdults = (int) 00;
    int priceChildrens = (int) 00;

    TextView txtCheckIn, txtCheckOut, txtAdults, txtxChildrens, txtAdultsIncrement, txtChildrensIncrement, txtAdultsDecrement, txtChildrensDecrement;

    String amenitiesURL = "Show-Amenities";
    String guestRoomsURL = "ShowHotelRooms";


    //Volley service
    private IResult mResultCallback;
    private VolleyService mVolleyService;

    SessionManager sessionManager;

    AmenitiesBaseModel amenitiesBaseModel;
    List<AmenitiesModel> amenitiesModelList = new ArrayList<>();

    GuestRoomBaseClass guestRoomBaseClass;
    List<GuestRoomModel> guestRoomModelList = new ArrayList<>();

    LinearLayout linearProgressBar;
    TextView txtNoAmenities,txtNoGuestRoom;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinder = DataBindingUtil.inflate(inflater, R.layout.homefragment, container, false);

        setupToolBarWithMenu(mBinder.toolbar.toolbar, getString(R.string.app_name));

        initData();

        getAmenities();


getGuestRooms();

        return mBinder.getRoot();

    }

    private void getAmenities() {


        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback, getContext());

        mVolleyService.getDataVolley(AMENITIES_LIST, this.getResources().getString(R.string.base_url) + amenitiesURL);
    }

    private void getGuestRooms() {


        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback, getContext());

        mVolleyService.getDataVolley(GUEST_ROOM, this.getResources().getString(R.string.base_url) + guestRoomsURL);
    }


    private void initVolleyCallback() {

       //    linearProgressBar.setVisibility(View.VISIBLE);

        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(int requestId, String response) {
                JSONObject jsonObj = null;

                switch (requestId) {

                    case AMENITIES_LIST:
                        try {
                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");

                            if (status == 1) {

                                Gson gson = new Gson();

                                amenitiesBaseModel = gson.fromJson(
                                        response, AmenitiesBaseModel.class);


                                List<AmenitiesModel> amenitiesModels = amenitiesBaseModel.getAllAmenities();

                                for (AmenitiesModel amenitiesModel : amenitiesModels) {
                                    amenitiesModelList.add(amenitiesModel);

                                }

                                amenitiesAdapter = new AmenitiesAdapter(getContext(), amenitiesModelList);
                                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                                rvAmenities.setLayoutManager(horizontalLayoutManager);
                                rvAmenities.setAdapter(amenitiesAdapter);

                            }

                            if (amenitiesModelList.isEmpty()) {
                               txtNoAmenities.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {

                            Log.v("Customer Activity", e.toString());
                        }

                        break;
                    case GUEST_ROOM:
                        try{

                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");

                            if (status == 1) {

                                Gson gson = new Gson();

                                guestRoomBaseClass = gson.fromJson(
                                        response, GuestRoomBaseClass.class);

                                List<GuestRoomModel> guestRoomModels = guestRoomBaseClass.getRoomsData();

                                for (GuestRoomModel guestRoomModel : guestRoomModels) {
                                    guestRoomModelList.add(guestRoomModel);



                                }

                              /*  String about = jsonObject.getString("included_inprice");
                                Spanned htmlAsSpanned = Html.fromHtml(about);
*/

                                guestRoomAdapter = new GuestRoomAdapter(getContext(), guestRoomModelList);
                                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                                rvGuestRoom.setLayoutManager(horizontalLayoutManager);
                                rvGuestRoom.setAdapter(guestRoomAdapter);

                            }

                            if (guestRoomModelList.isEmpty()) {
                                  txtNoGuestRoom.setVisibility(View.VISIBLE);
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


    private void initData() {
        btnBookNow = mBinder.btnBookNow;
        linearEnqiry = mBinder.linearEnqiry;
        linearEmailUs = mBinder.linearEmailUs;
        linearCallUs = mBinder.linearCallUs;
        rvAmenities = mBinder.rvAmenities;
        rvGuestRoom = mBinder.rvGuestRoom;
        txtNoAmenities = mBinder.txtNoAmenities;
        txtNoGuestRoom = mBinder.txtNoData;


        linearEnqiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(R.style.DialogAnimation);

            }
        });


        linearCallUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+251999999999"));
                startActivity(intent);
            }
        });

        linearEmailUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });


        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildDialog(R.style.DialogAnimation);

            }
        });

    }

    private void buildDialog(int animationSource) {

        final Dialog dialog = new Dialog(getContext());

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

        onClick();

    }


    private void dialog(int animationSource) {

        final Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.enquiryalertdialog);

        dialog.setCanceledOnTouchOutside(true);

        dialog.getWindow().getAttributes().windowAnimations = animationSource;

        dialog.show();

    }

    private void onClick() {

        relativeCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog,
                        checkInDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        checkInDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyy/mm/dd: " + year + "/" + month + "/" + day);

                String date = year + "/" + month + "/" + day;
                txtCheckIn.setText(date);
            }
        };


        relativeCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        chekOutDateListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        chekOutDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyy/mm/dd: " + year + "/" + month + "/" + day);

                String date = year + "/" + month + "/" + day;
                txtCheckOut.setText(date);
            }
        };


        btnSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RoomListActivity.class);
                startActivity(intent);
            }
        });

        txtAdultsIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceAdults = priceAdults + 01;
                txtAdults.setText(String.valueOf(priceAdults));
            }
        });

        txtAdultsDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceAdults = priceAdults - 01;
                txtAdults.setText(String.valueOf(priceAdults));
            }
        });


        txtChildrensIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceChildrens = priceChildrens + 01;
                txtxChildrens.setText(String.valueOf(priceChildrens));
            }
        });


        txtChildrensDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceChildrens = priceChildrens - 01;
                txtxChildrens.setText(String.valueOf(priceChildrens));
            }
        });
    }


    public void setupToolBarWithMenu(Toolbar toolbar, @Nullable String Title) {
        ((MainActivity) mActivity).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity) mActivity).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        ImageView ivMenu = (ImageView) toolbar.findViewById(R.id.icMenu);
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) mActivity).toggleDrawer();
            }
        });


    }


}
