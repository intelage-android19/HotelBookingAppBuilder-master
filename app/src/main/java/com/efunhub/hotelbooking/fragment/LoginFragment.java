package com.efunhub.hotelbooking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.utility.SessionManager;
import com.efunhub.hotelbooking.utility.VolleyService;
import com.efunhub.hotelbooking.activity.ForgotPasswordActivity;
import com.efunhub.hotelbooking.activity.MainActivity;
import com.efunhub.hotelbooking.interfaces.IResult;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.efunhub.hotelbooking.utility.ConstantVariables.LOGIN;

public class LoginFragment extends Fragment {

    TextView txtForgotPass;
    EditText edtLoginMobile, edtLoginPass;
    FloatingActionButton btnLogin;

    //Volley service
    private IResult mResultCallback;
    private VolleyService mVolleyService;

    SessionManager sessionManager;

    //all urls
    private String LOGIN_URL = "Customer-Login";


    CountryCodePicker ccp;
    AppCompatEditText edtPhoneNumber;
    String countrycode = "";

    LinearLayout linearProgressBar;

    public static LoginFragment newInstance( Integer one) {

        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.login_fragment, container, false);



        txtForgotPass = view.findViewById(R.id.txtForgotPass);
        edtLoginMobile = view.findViewById(R.id.edtLoginMobile);
        edtLoginPass = view.findViewById(R.id.edtLoginPass);
        btnLogin =  view.findViewById(R.id.fbLogin);
        linearProgressBar =  view.findViewById(R.id.linearProgressBar);

        ccp = (CountryCodePicker) view.findViewById(R.id.ccp);

        countrycode = ccp.getSelectedCountryCode();
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                countrycode = ccp.getSelectedCountryCode();
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                  loginCustomer();
                }
            }
        });

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ForgotPasswordActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        return view;
    }


    private void loginCustomer() {

        sessionManager = new SessionManager(getContext());

        HashMap<String, String> userInfo = sessionManager.getUserDetails();

        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback, getContext());

        Map<String, String> params = new HashMap<>();

        params.put("contact", edtLoginMobile.getText().toString());
        params.put("password", edtLoginPass.getText().toString());
        params.put("country_code", countrycode);

        Log.v("Register Activity", String.valueOf(params));

        mVolleyService.postDataVolleyParameters(LOGIN, this.getResources().getString(R.string.base_url) + LOGIN_URL, params);

    }

    private void initVolleyCallback() {

        linearProgressBar.setVisibility(View.VISIBLE);

        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(int requestId, String response) {
                JSONObject jsonObj = null;

                switch (requestId) {

                    case LOGIN:
                        try {

                            jsonObj = new JSONObject(response);

                            int status = jsonObj.getInt("status");
                            String message = jsonObj.getString("msg");

                            if (status == 0) {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            } else if (status == 1) {
                                 String Id = jsonObj.getString("customer_auto_id");

                                Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();
                                sessionManager.createLoginSession(Id);
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                        } catch (Exception e) {

                            Log.v("Login Activity", e.toString());
                        }

                        break;

                }
                linearProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void notifyError(int requestId, VolleyError error) {
                System.out.println(error);
                Toast.makeText(getContext(), "Something went wrong. Please try again !!!", Toast.LENGTH_LONG).show();
                Log.v("Volley requestid", String.valueOf(requestId));
                Log.v("Volley Error", String.valueOf(error));
                linearProgressBar.setVisibility(View.GONE);

            }
        };
    }


    public boolean checkValid() {

        String contactPattern = "^[6-9][0-9]{9,}$";
        String passwordPattern = "^[a-zA-Z0-9]{6,}$";

        if (edtLoginMobile.getText().toString().equalsIgnoreCase("")) {
            edtLoginMobile.setError("Please enter mobile number");
            return false;
        } else if (!edtLoginMobile.getText().toString().matches(contactPattern)) {
            edtLoginMobile.setError("Please enter valid mobile number");

            //  toastClass.makeToast(this, "Please enter valid mobile number");
            return false;
        } else if (edtLoginPass.getText().toString().equalsIgnoreCase("")) {
            edtLoginPass.setError("Please enter password");
            return false;
        } else if (!edtLoginPass.getText().toString().matches(passwordPattern)) {
            edtLoginPass.setError(" Password must be at least 6 characters");
            return false;
        }
        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        /*FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.viewPager, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();*/

    }
}
