package com.efunhub.hotelbooking.activity;

import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.fragment.LoginFragment;
import com.efunhub.hotelbooking.fragment.RegistrationFragment;
import com.efunhub.hotelbooking.utility.CheckConnectivity;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    TabLayout tlLoginSignup;
    ViewPager viewPager;
    CheckConnectivity checkConnectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initData();

        setUpViewPager(viewPager);
        tlLoginSignup.setupWithViewPager(viewPager);


    }

    private void initData() {
        tlLoginSignup = findViewById(R.id.tlLoginSignup);
        viewPager = findViewById(R.id.viewPager);
        checkConnectivity = new CheckConnectivity();
    }


    private void setUpViewPager(ViewPager viewPager) {

        LoginActivity.ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        LoginFragment loginFragment = LoginFragment.newInstance(1);
        adapter.addFragment(loginFragment, "LogIn");

        RegistrationFragment registrationFragment = RegistrationFragment.newInstance(2);

        adapter.addFragment(registrationFragment, "SignUp");

        viewPager.setAdapter(adapter);

    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
/*

            if (position == mFragmentTitleList.size() - 1) {

                Fragment fragment = new UsedCarGalleryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                fragment.setArguments(bundle);
                return fragment;
            } else {
*/
            return mFragmentList.get(position);
            //  }
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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
