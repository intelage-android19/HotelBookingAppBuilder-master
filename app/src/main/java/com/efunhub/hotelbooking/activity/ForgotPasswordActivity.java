package com.efunhub.hotelbooking.activity;

import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.efunhub.hotelbooking.utility.CheckConnectivity;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.utility.VolleyService;
import com.efunhub.hotelbooking.databinding.ForgotpasswordBinding;
import com.efunhub.hotelbooking.interfaces.IResult;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.efunhub.hotelbooking.utility.ConstantVariables.FORGOT_PASS;

public class ForgotPasswordActivity extends AppCompatActivity {
    ForgotpasswordBinding mBinder;
    TextView title;

    EditText edtForgotEmail;
    FloatingActionButton fbForgotPass;

    //Volley service
    private IResult mResultCallback;
    private VolleyService mVolleyService;

    private String forgotPasswordUrl = "Customer-Forgot-Password";


    CheckConnectivity checkConnectivity;
    LinearLayout imgNoDataFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBinder = DataBindingUtil.setContentView(this, R.layout.forgotpassword);

        edtForgotEmail = mBinder.edtForgotPassEmail;
        fbForgotPass = mBinder.fbForgotPass;
        checkConnectivity = new CheckConnectivity();

        fbForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    forgotPassword();
                }
            }
        });
    }

    private void forgotPassword() {

        initVolleyCallback();

        // pbForgotPass.setVisibility(View.VISIBLE);
        //btnSend.setVisibility(View.GONE);

        mVolleyService = new VolleyService(mResultCallback, this);

        Map<String, String> params = new HashMap<>();
        params.put("email", edtForgotEmail.getText().toString());

        mVolleyService.postDataVolleyParameters(FORGOT_PASS,
                getApplicationContext().getResources().getString(R.string.base_url) + forgotPasswordUrl,
                params);

    }


    private void initVolleyCallback() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(int requestId, String response) {
                JSONObject jsonObj = null;

                switch (requestId) {

                    case FORGOT_PASS:
                        try {
                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");

                            if (status == 0) {
                                Toast.makeText(ForgotPasswordActivity.this, "Sorry, an account not exist with this email", Toast.LENGTH_SHORT).show();
                            } else if (status == 1) {

                                Toast.makeText(ForgotPasswordActivity.this, "Success, Please check your email", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (Exception e) {

                            Log.v("ForgotPasswordActivity", e.toString());
                        }
                        //  pbForgotPass.setVisibility(View.GONE);
                        //   btnSend.setVisibility(View.VISIBLE);
                        break;


                }
            }

            @Override
            public void notifyError(int requestId, VolleyError error) {

                imgNoDataFound.setVisibility(View.VISIBLE);
                Log.v("Volley requestid", String.valueOf(requestId));
                Log.v("Volley Error", String.valueOf(error));
                //   pbForgotPass.setVisibility(View.GONE);
                //  btnSend.setVisibility(View.VISIBLE);
            }
        };
    }


    public boolean checkValid() {

        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (edtForgotEmail.getText().toString().equalsIgnoreCase("")) {
            edtForgotEmail.setError("Please enter email id");

            //   toastClass.makeToast(this, "Please enter email id");
            return false;
        } else if (!edtForgotEmail.getText().toString().matches(emailPattern)) {
            edtForgotEmail.setError("Please enter valid email id");
            return false;
        }
        return true;
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
