package com.efunhub.hotelbooking.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.efunhub.hotelbooking.fragment.HomeFragment;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.databinding.ActivityMainBinding;
import com.efunhub.hotelbooking.fragment.ReferAndEarnFragment;
import com.efunhub.hotelbooking.utility.CheckConnectivity;
import com.efunhub.hotelbooking.utility.SessionManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding mBinder;
    private Fragment fragment;

    CheckConnectivity checkConnectivity;
    SessionManager sessionManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        checkConnectivity = new CheckConnectivity();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(this);
        fragment = new HomeFragment();
        loadFragment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            fragment = new HomeFragment();
            loadFragment();


        } else if (id == R.id.nav_ReferEarn) {

            fragment = new ReferAndEarnFragment();
            loadFragment();

        } else if (id == R.id.nav_Profile) {

            Intent intent = new Intent(MainActivity.this, ProfileActivtiy.class);
            startActivity(intent);

        } else if (id == R.id.nav_wallet) {

            Intent intent = new Intent(MainActivity.this, WalletActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_Logout) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setTitle("Logout");
            alertDialogBuilder.setMessage("Are you sure you want to Logout ?");
            alertDialogBuilder.setIcon(R.drawable.ic_lock_outline_black_24dp);
            alertDialogBuilder.setPositiveButton("yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            sessionManager.logoutUser();
                            finish();
                        }
                    });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
        DrawerLayout drawer = findViewById(R.id.DrawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fm.getBackStackEntryCount() <= 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirm Exit");
            //  builder.setIcon(R.drawable.icon_exit);
            builder.setMessage("Do you want to exit?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory(Intent.CATEGORY_HOME);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            super.onBackPressed();
        }
    }


    private void loadFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.MainFramLayout, fragment)
                .addToBackStack(String.valueOf(fm))
                .commit();

    }

    public void toggleDrawer() {
        if (mBinder.DrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinder.DrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mBinder.DrawerLayout.openDrawer(GravityCompat.START);
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
