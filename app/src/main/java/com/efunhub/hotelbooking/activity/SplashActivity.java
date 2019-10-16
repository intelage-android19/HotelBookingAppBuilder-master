package com.efunhub.hotelbooking.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.utility.SessionManager;


public class SplashActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvAppname;

    // Animation
    private Animation animLogo, animAppName;

    // Session Manager
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        init();

        //After loading animatio set animation to particuler view(Button, Image, etc.)
     /*   ivLogo.setAnimation(animLogo);
        tvAppname.setAnimation(animAppName);
*/
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    session.checkLogin();
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    private void init() {
        session = new SessionManager(getApplicationContext());

        //Load animations
     /*   animLogo = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        animAppName = AnimationUtils.loadAnimation(this, R.anim.appname_anim);
*/
       // ivLogo = findViewById(R.id.ivLogo);
        tvAppname = findViewById(R.id.tvAppname);
    }
}
