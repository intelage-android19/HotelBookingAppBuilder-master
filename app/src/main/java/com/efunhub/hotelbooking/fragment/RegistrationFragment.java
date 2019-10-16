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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.efunhub.hotelbooking.utility.CheckConnectivity;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.utility.SessionManager;
import com.efunhub.hotelbooking.utility.VolleyService;
import com.efunhub.hotelbooking.activity.LoginActivity;
import com.efunhub.hotelbooking.interfaces.IResult;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.efunhub.hotelbooking.utility.ConstantVariables.REGISTER_CUSTOMER;

public class RegistrationFragment extends Fragment {


    EditText edtRegisterName,edtRegisterPhone,edtRegisterEmail,edtRegisterPass,edtRegisterCountryCode;

    FloatingActionButton fbRegister;

    String RegistrationURL = "Customer-Registration";
    private IResult mResultCallback;
    private VolleyService mVollyService;
    private CheckConnectivity checkConnectivity;
    private SessionManager sessionManager ;

    CountryCodePicker ccp;
    AppCompatEditText edtPhoneNumber;
    String countrycode = "";

    LinearLayout linearLayoutProgressBar;

    public static RegistrationFragment newInstance( Integer two) {
        RegistrationFragment fragment = new RegistrationFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.registeractivity, container, false);

        edtRegisterName = view.findViewById(R.id.edtRegistrationName);
        edtRegisterPhone = view.findViewById(R.id.edtRegistrationPhone);
        edtRegisterEmail = view.findViewById(R.id.edtRegistrationEmail);
        edtRegisterPass = view.findViewById(R.id.edtRegistrationPass);
        fbRegister = view.findViewById(R.id.fbRegister);
        linearLayoutProgressBar = view.findViewById(R.id.linearProgressBar);

        ccp = (CountryCodePicker) view.findViewById(R.id.ccp);

        countrycode = ccp.getSelectedCountryCode();

        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                countrycode = ccp.getSelectedCountryCode();
            }
        });


        fbRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {

                    registerCustomer();
                }
            }
        });

        return view;
    }

    private void registerCustomer() {

        sessionManager = new SessionManager(getContext());

        HashMap<String, String> userInfo = sessionManager.getUserDetails();
        String referrerId = userInfo.get(SessionManager.REFERRER_ID);

        initVolleyCallback();

        mVollyService = new VolleyService(mResultCallback, getContext());

        Map<String, String> params = new HashMap<>();


        params.put("name", edtRegisterName.getText().toString());
        params.put("email", edtRegisterEmail.getText().toString());
        params.put("contact", edtRegisterPhone.getText().toString());
        params.put("country_code",countrycode);
        params.put("password", edtRegisterPass.getText().toString());

        if (referrerId != null) {
            params.put("referrer_id", referrerId);
        } else {
            params.put("referrer_id", "");
        }



        Log.v("Register Activity", String.valueOf(params));

        mVollyService.postDataVolleyParameters(REGISTER_CUSTOMER, this.getResources().getString(R.string.base_url) + RegistrationURL, params);

    }

    private void initVolleyCallback() {

        linearLayoutProgressBar.setVisibility(View.VISIBLE);

        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(int requestId, String response) {
                JSONObject jsonObj = null;

                switch (requestId) {

                    case REGISTER_CUSTOMER:
                        try {

                            jsonObj = new JSONObject(response);

                            int status = jsonObj.getInt("status");
                            String message = jsonObj.getString("msg");

                            if (status == 0) {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            } else if (status == 1) {
                                Toast.makeText(getActivity(), "Register Successfully", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getContext(), LoginActivity.class));
                            }
                        } catch (Exception e) {

                            Log.v("Customer Activity", e.toString());
                        }

                        break;

                }
                linearLayoutProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void notifyError(int requestId, VolleyError error) {
                System.out.println(error);
                Toast.makeText(getContext(), "Something went wrong. Please try again !!!", Toast.LENGTH_LONG).show();
                Log.v("Volley requestid", String.valueOf(requestId));
                Log.v("Volley Error", String.valueOf(error));
                linearLayoutProgressBar.setVisibility(View.GONE);

            }
        };
    }

    public boolean checkValid() {

        String namePattern = "^[a-zA-Z -]+$";

        String emailPattern = "^[a-zA-Z]\\w+(.\\w+)*@\\w+(.[0-9a-zA-Z]+)*.[a-zA-Z]{2,4}$";

        String phoneRegex = "^[6-9][0-9]{9,}$";

        String passwordRegex = "^[a-zA-Z0-9]{6,}$";

        if (edtRegisterName.getText().toString().equalsIgnoreCase("")) {
            edtRegisterName.setError("Please enter full name");
            return false;
        } else if (!edtRegisterName.getText().toString().matches(namePattern)) {
            edtRegisterName.setError("Name contains only Uppercase and Lowercase Letters");
            return false;
        } else if (edtRegisterEmail.getText().toString().equalsIgnoreCase("")) {
            edtRegisterEmail.setError("Please enter email id");
            return false;
        } else if (!edtRegisterEmail.getText().toString().matches(emailPattern)) {
            edtRegisterEmail.setError("Please enter valid email id");
            return false;
        } else if (edtRegisterPhone.getText().toString().equalsIgnoreCase("")) {
            //  toastClass.makeToast(this, "Please enter mobile number");
            edtRegisterPhone.setError("Please enter mobile number");
            return false;
        } else if (!edtRegisterPhone.getText().toString().matches(phoneRegex)) {
            edtRegisterPhone.setError("Please enter valid phone number");
            return false;
        } else if (edtRegisterPass.getText().toString().equalsIgnoreCase("")) {
            edtRegisterPass.setError("Please enter password");
            return false;
        } else if (!edtRegisterPass.getText().toString().matches(passwordRegex)) {
            edtRegisterPass.setError(" Password must be at least 6 characters");
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
        ft.commit();
*/
    }
}
