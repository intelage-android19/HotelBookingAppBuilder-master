package com.efunhub.hotelbooking.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.activity.MainActivity;
import com.efunhub.hotelbooking.activity.RoomDetailsActivity;
import com.efunhub.hotelbooking.activity.RoomListActivity;
import com.efunhub.hotelbooking.databinding.ReferandearnfragmentBinding;
import com.efunhub.hotelbooking.utility.SessionManager;

import java.util.HashMap;

import static com.efunhub.hotelbooking.utility.SessionManager.KEY_ID;

public class ReferAndEarnFragment extends  android.support.v4.app.Fragment {

    private ReferandearnfragmentBinding mBinder;
    TextView title;
    Context mContext;
    Activity mActivity;
    Button btnReferEarn;
    SessionManager sessionManager ;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinder = DataBindingUtil.inflate(inflater, R.layout.referandearnfragment, container, false);

        sessionManager = new SessionManager(getContext());

        HashMap<String, String> userAccount = sessionManager.getUserDetails();
        id = userAccount.get(KEY_ID);

        btnReferEarn = mBinder.btnReferEarn;
        btnReferEarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    String applink = "https://play.google.com/";
                    String messagelink = "Invite your friends and earn loyalty points #E-Learning \n";
//                messagelink = messagelink + applink + sid;
                    messagelink = messagelink + applink;

                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.putExtra(Intent.EXTRA_SUBJECT, "E-Learning");
                    i.putExtra(Intent.EXTRA_TEXT, messagelink);
                    i.setType("text/plain");
                    startActivity(Intent.createChooser(i, "Share via"));

                }
            }
        });

        return mBinder.getRoot();
    }





}
