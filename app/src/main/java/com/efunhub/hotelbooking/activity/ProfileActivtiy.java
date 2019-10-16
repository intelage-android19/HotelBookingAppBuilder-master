package com.efunhub.hotelbooking.activity;

import android.app.Dialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.efunhub.hotelbooking.utility.CheckConnectivity;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.utility.SessionManager;
import com.efunhub.hotelbooking.utility.VolleyService;
import com.efunhub.hotelbooking.interfaces.IResult;
import com.efunhub.hotelbooking.model.ProfileBaseModel;
import com.efunhub.hotelbooking.model.ProfileModel;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.efunhub.hotelbooking.utility.ConstantVariables.CHANGE_PASSWORD;
import static com.efunhub.hotelbooking.utility.ConstantVariables.PROFILE_DATA;
import static com.efunhub.hotelbooking.utility.ConstantVariables.UPDATE_PROFILE;
import static com.efunhub.hotelbooking.utility.SessionManager.KEY_ID;

public class ProfileActivtiy extends AppCompatActivity {

    ImageView imgEdit;

    ProfileBaseModel profileBaseModel;
    private List<ProfileModel> profileModelList = new ArrayList<>();

    //Volley service
    private IResult mResultCallback;
    private VolleyService mVolleyService;

    private String Profile_Data = "Customer-Profile";
    private String updateProfile = "Customer-Update-Profile";
    private String updatePassword = "Customer-Update-Password";

    CheckConnectivity checkConnectivity;
    LinearLayout imgNoDataFound;
    SessionManager sessionManager;

    TextView txtPName, txtPPhone, txtEmail;
    EditText edtName, edtContact, edtEmail, edtOldp, edtNewp;
    Button btnUpdateProfile, btnDialogUpdatePassword;
    String id, name, contact, email, address, country_code, dialogName, dialogContact, dialogEmail, oldP, newP;
    CardView crdUpdatePassword;
    Dialog updateProfileDialog, updatePasswordDialog;

    CountryCodePicker ccp;
    AppCompatEditText edtPhoneNumber;
    String countrycode = "";

    LinearLayout linearProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileactivity);

        imgEdit = findViewById(R.id.imgEdit);
        txtPName = findViewById(R.id.txtPName);
        txtPPhone = findViewById(R.id.txtPPhone);
        txtEmail = findViewById(R.id.txtEmail);
        crdUpdatePassword = findViewById(R.id.crdUpdatePassword);
        linearProgressBar = findViewById(R.id.linearProgressBar);

        checkConnectivity = new CheckConnectivity();

        sessionManager = new SessionManager(this);

        HashMap<String, String> userInfo = sessionManager.getUserDetails();

        id = userInfo.get(KEY_ID);


        getProfileData();

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateProfileDialog();

            }
        });

        crdUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePasswordDialog();
            }
        });


    }

    private void updateProfileDialog() {

        updateProfileDialog = new Dialog(ProfileActivtiy.this);

        updateProfileDialog.setContentView(R.layout.editprofile);

        edtName = updateProfileDialog.findViewById(R.id.edtProfilename);
        edtContact = updateProfileDialog.findViewById(R.id.edtProfilePhone);
        edtEmail = updateProfileDialog.findViewById(R.id.edtProfileEmail);
        btnUpdateProfile = updateProfileDialog.findViewById(R.id.btnUpdateProfile);

        ccp = (CountryCodePicker) updateProfileDialog.findViewById(R.id.ccpEditProfile);

        countrycode = ccp.getSelectedCountryCode();

        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                countrycode = ccp.getSelectedCountryCode();
            }
        });


        dialogName = edtName.getText().toString();
        dialogContact = edtContact.getText().toString();
        dialogEmail = edtEmail.getText().toString();


        edtName.setText(name);
        edtContact.setText(contact);
        edtEmail.setText(email);

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidations()) {
                    updateProfile();
                }
            }
        });
        updateProfileDialog.setCanceledOnTouchOutside(true);

        updateProfileDialog.show();


    }

    private void updatePasswordDialog() {

        updatePasswordDialog = new Dialog(ProfileActivtiy.this);

        updatePasswordDialog.setContentView(R.layout.updtae_password_dialog);

        edtOldp = updatePasswordDialog.findViewById(R.id.edtOldPassword);
        edtNewp = updatePasswordDialog.findViewById(R.id.edtNewPassword);
        btnDialogUpdatePassword = updatePasswordDialog.findViewById(R.id.btnUpdatePasswordDialog);

        btnDialogUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPasswordValidations()) {
                    updatePassword();
                }
            }
        });
        updatePasswordDialog.setCanceledOnTouchOutside(true);

        updatePasswordDialog.show();

    }

    private void getProfileData() {

        sessionManager = new SessionManager(this);

        HashMap<String, String> userInfo = sessionManager.getUserDetails();

        id = userInfo.get(KEY_ID);

        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback, this);

        Map<String, String> params = new HashMap<>();
        params.put("customer_auto_id", id);

        mVolleyService.postDataVolleyParameters(PROFILE_DATA, this.getResources().getString(R.string.base_url) + Profile_Data, params);

    }

    private void updateProfile() {

        sessionManager = new SessionManager(this);

        HashMap<String, String> userInfo = sessionManager.getUserDetails();

        Map<String, String> params = new HashMap<>();

        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback, this);

        id = userInfo.get(KEY_ID);

        params.put("name", edtName.getText().toString());
        params.put("email", edtEmail.getText().toString());
        params.put("contact", edtContact.getText().toString());
        params.put("country_code", countrycode);


        mVolleyService.postDataVolleyParameters(UPDATE_PROFILE, this.getResources().getString(R.string.base_url) + updateProfile, params);

    }

    private void updatePassword() {

        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback, this);

        Map<String, String> params = new HashMap<>();

        params.put("oldp", edtOldp.getText().toString());
        params.put("newp", edtNewp.getText().toString());
        params.put("customer_auto_id", id);

        mVolleyService.postDataVolleyParameters(CHANGE_PASSWORD, this.getResources().getString(R.string.base_url) + updatePassword, params);
    }

    private void initVolleyCallback() {
        linearProgressBar.setVisibility(View.VISIBLE);
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(int requestId, String response) {
                JSONObject jsonObj = null;

                switch (requestId) {


                    case PROFILE_DATA:
                        try {
                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");

                            if (status == 1) {
                                //   Toast.makeText(ProfileActivtiy.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                                //    alertDialogUpdateProfile.dismiss();

                                JSONObject jsonObject1 = jsonObj.getJSONObject("profile");


                                name = jsonObject1.getString("name");
                                contact = jsonObject1.getString("contact");
                                email = jsonObject1.getString("email");

                                address = jsonObject1.getString("address");
                                country_code = jsonObject1.getString("country_code");

                                txtPName.setText(name);
                                txtPPhone.setText(contact);
                                txtEmail.setText(email);

                            }
                        } catch (Exception e) {

                            Log.v("Update_profile_dialog", e.toString());
                        }

                        break;
                    case UPDATE_PROFILE:
                        try {
                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");
                            int message = jsonObj.getInt("msg");

                            if (status == 1) {
                                Toast.makeText(ProfileActivtiy.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                                updateProfileDialog.dismiss();
                            } else if (status == 0) {
                                Toast.makeText(ProfileActivtiy.this, message, Toast.LENGTH_SHORT).show();
                                //    alertDialogUpdateProfile.dismiss()
                            }
                        } catch (Exception e) {

                            Log.v("Update_profile_dialog", e.toString());
                        }

                        break;

                    case CHANGE_PASSWORD:
                        try {
                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");
                            String msg = jsonObj.getString("msg");

                            if (status == 0) {
                                Toast.makeText(ProfileActivtiy.this, msg, Toast.LENGTH_SHORT).show();
                            } else if (status == 1) {
                                Toast.makeText(ProfileActivtiy.this, "Password has been changed successfully.", Toast.LENGTH_SHORT).show();
                                updatePasswordDialog.dismiss();

                            }

                        } catch (Exception e) {

                            Log.v("Update_profile_dialog", e.toString());
                        }
                        break;


                }
                linearProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void notifyError(int requestId, VolleyError error) {
                System.out.println(error);
                //  imgNoDataFound.setVisibility(View.VISIBLE);
                Log.v("Volley requestid", String.valueOf(requestId));
                Log.v("Volley Error", String.valueOf(error));
                linearProgressBar.setVisibility(View.GONE);

            }
        };
    }

    private boolean checkValidations() {

        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (TextUtils.isEmpty(edtName.getText().toString())) {

            Toast.makeText(ProfileActivtiy.this, "Please enter Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edtContact.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter email ", Toast.LENGTH_LONG).show();
            return false;
        } else if (!edtEmail.getText().toString().matches(emailPattern)) {
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean checkPasswordValidations() {

        if (TextUtils.isEmpty(edtOldp.getText().toString())) {

            Toast.makeText(ProfileActivtiy.this, "Please enter old password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edtOldp.getText().toString().length() < 6) {
            Toast.makeText(ProfileActivtiy.this, "Password must be atleast 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(edtNewp.getText().toString())) {
            Toast.makeText(ProfileActivtiy.this, "Please enter new password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edtNewp.getText().toString().length() < 6) {
            Toast.makeText(ProfileActivtiy.this, "Password must be atleast 6 characters", Toast.LENGTH_SHORT).show();
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
