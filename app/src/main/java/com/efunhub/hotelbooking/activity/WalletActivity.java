package com.efunhub.hotelbooking.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.fragment.CreditedFragment;
import com.efunhub.hotelbooking.fragment.DebitedHistoryFragment;
import com.efunhub.hotelbooking.interfaces.IResult;
import com.efunhub.hotelbooking.utility.SessionManager;
import com.efunhub.hotelbooking.utility.VolleyService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.efunhub.hotelbooking.utility.ConstantVariables.WALLET;
import static com.efunhub.hotelbooking.utility.ConstantVariables.WALLET_HISTORY;
import static com.efunhub.hotelbooking.utility.ConstantVariables.WALLET_TRANSFER_AMOUNT;
import static com.efunhub.hotelbooking.utility.SessionManager.KEY_ID;

public class WalletActivity extends AppCompatActivity {

    Toolbar toolbar;

    //Volley service
    private IResult mResultCallback;
    private VolleyService mVolleyService;
    
    //all urls
    private String walletUrl = "GetWalletAmount";
    private String walletHistoryUrl = "Show-Wallet-Tranzaction-History";
    private String walletTransferAmount = "Transfer-Amount";

    private SessionManager sessionManager;
    private String cid;
    private String wallet_amount;
    TextView tvWalletBalance;
    RecyclerView rvTransaction;
    Button btnSend, btnTransfer;
    EditText edtTransMobile, edtTransAmount;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        setupToolbar();

        sessionManager = new SessionManager(this);
        rvTransaction = findViewById(R.id.rvTransaction);
        tvWalletBalance = findViewById(R.id.tvWalletBalance);
        btnTransfer = findViewById(R.id.btnTransfer);
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTransferDialog();
            }
        });

        getWalletBalance();


    }

    private void getWalletBalance() {

        initVolleyCallback();

        HashMap<String, String> userInfo = sessionManager.getUserDetails();

        cid = userInfo.get(KEY_ID);


        HashMap<String, String> param = new HashMap<>();

        param.put("customer_auto_id", cid);//"5d2577d462c29"

        mVolleyService = new VolleyService(mResultCallback, this);
        mVolleyService.postDataVolleyParameters(WALLET,
                this.getResources().getString(R.string.base_url) + walletUrl, param);

    }

    private void showTransferDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.transfer_dialog, null);
        dialogBuilder.setView(dialogView);

        edtTransMobile = dialogView.findViewById(R.id.edtTransMobile);
        edtTransAmount = dialogView.findViewById(R.id.edtTransAmount);
        btnSend = dialogView.findViewById(R.id.btnSend);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSend.setVisibility(View.GONE);
                // pbWalletTransfer.setVisibility(View.VISIBLE);
                //alertDialog.dismiss();

            }
        });

        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }


    private void initVolleyCallback() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(int requestId, String response) {
                JSONObject jsonObj = null;

                switch (requestId) {

                    case WALLET:
                        try {
                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");

                            if (status == 0) {
                                String msg = jsonObj.getString("msg");
                                Toast.makeText(WalletActivity.this, msg, Toast.LENGTH_SHORT).show();


                            } else if (status == 1) {


                                wallet_amount = jsonObj.getString("wallat_balance");

                                tvWalletBalance.setText("₹" + " " + wallet_amount);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        break;

                    case WALLET_HISTORY:
                        try {
                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");

                            if (status == 0) {
                                //  toastClass.makeToast(getApplicationContext(), "Error");

                            } else if (status == 1) {


                                Gson gson = new Gson();
/*
                                transactionHistoryBaseModel = gson.fromJson(
                                        response, TransactionHistoryBaseModel.class);

                                creditedTransactionHistoryModelsList=transactionHistoryBaseModel.getCreditedHistory();
                                debitedTransactionHistoryModelList=transactionHistoryBaseModel.getDebittedHistory();

                                setUpViewPager(mBinder.viewpagerHistory);
                                mBinder.tbTransactionHistory.setupWithViewPager(mBinder.viewpagerHistory);
                            }

                              if (walletlist.isEmpty()) {
                              mBinder.pbsoldcarlist.setVisibility(View.GONE);
                              mBinder.notransaction.setVisibility(View.VISIBLE);
                              }*/
                            }


                        } catch (Exception e) {

                            Log.v("Wallet Activity", e.toString());
                        }
                        break;


                    case WALLET_TRANSFER_AMOUNT:

                        try {
                            jsonObj = new JSONObject(response);
                            int status = jsonObj.getInt("status");

                            if (status == 0) {
                                //   toastClass.makeToast(getApplicationContext(), "Not available any data");

                            } else if (status == 1) {
                              /*  toastClass.makeToast(WalletActivity.this, "Amount transferred successfully");
                                dalogTransaction.cancel();*/
                            } else if (status == 2) {
                                // toastClass.makeToast(WalletActivity.this, "Your current balance is low");
                            } else if (status == 3) {
                                //  toastClass.makeToast(WalletActivity.this, "Sorry, an account not exist with this contact");
                            } else if (status == 4) {
                                //  toastClass.makeToast(WalletActivity.this, "You are using your own contact");
                            }


                        } catch (Exception e) {
                            Log.v("Wallet Activity", e.toString());
                        }

                        //  mBinder.btnLogin.setVisibility(View.VISIBLE);
                        //   mBinder.cvPgBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void notifyError(int requestId, VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong. Please try again !!!", Toast.LENGTH_LONG).show();
                Log.v("Volley requestid", String.valueOf(requestId));
                Log.v("Volley Error", String.valueOf(error));
            }
        };
    }


    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbarHistory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Wallet");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
