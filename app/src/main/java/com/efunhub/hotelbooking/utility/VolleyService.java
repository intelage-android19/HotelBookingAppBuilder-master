package com.efunhub.hotelbooking.utility;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.efunhub.hotelbooking.interfaces.IResult;
import com.efunhub.hotelbooking.utility.TLSSocketFactory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by Admin on 17-11-2017.
 */

public class VolleyService {

    IResult mResultCallback = null;
    Context mContext;

    public VolleyService(IResult resultCallback, Context context) {
        mResultCallback = resultCallback;
        mContext = context;
    }



    public void postDataVolleyParameters(final int requestId, String url, final Map<String, String> params) {
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            //start check android version below lolipop
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT)
            {
                HttpStack stack = null;
                try {
                    stack = new HurlStack(null, new TLSSocketFactory());
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                    Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                    stack = new HurlStack();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                    stack = new HurlStack();
                }
                queue = Volley.newRequestQueue(mContext, stack);
            } else {
                queue = Volley.newRequestQueue(mContext);
            }
            //end check android version below lolipop

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    if (mResultCallback != null)
                        mResultCallback.notifySuccess(requestId, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mResultCallback != null)
                        mResultCallback.notifyError(requestId, error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };


            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(stringRequest);

        } catch (Exception e) {
            Log.v("VolleyServiceException", e.toString());
        }
    }

    public void postDataVolley(final int requestId, String url) {
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            //start check android version below lolipop
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                    && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT)
            {
                HttpStack stack = null;
                try {
                    stack = new HurlStack(null, new TLSSocketFactory());
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                    Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                    stack = new HurlStack();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                    stack = new HurlStack();
                }
                queue = Volley.newRequestQueue(mContext, stack);
            } else {
                queue = Volley.newRequestQueue(mContext);
            }
            //end check android version below lolipop

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    if (mResultCallback != null)
                        mResultCallback.notifySuccess(requestId, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mResultCallback != null)
                        mResultCallback.notifyError(requestId, error);
                }
            });


            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(stringRequest);

        } catch (Exception e) {
            Log.v("VolleyServiceException", e.toString());
        }
    }


    public void getDataVolley(final int requestId, String url){

        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            //start check android version below lolipop
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                    && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT)
            {
                HttpStack stack = null;
                try {
                    stack = new HurlStack(null, new TLSSocketFactory());
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                    Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                    stack = new HurlStack();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    Log.d("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                    stack = new HurlStack();
                }
                queue = Volley.newRequestQueue(mContext, stack);
            } else {
                queue = Volley.newRequestQueue(mContext);
            }
            //end check android version below lolipop

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    if (mResultCallback != null)
                        mResultCallback.notifySuccess(requestId, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mResultCallback != null)
                        mResultCallback.notifyError(requestId, error);
                }
            });


            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(stringRequest);

        } catch (Exception e) {
            Log.v("VolleyServiceException", e.toString());
        }
       /* try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType, error);
                }
            });

            queue.add(jsonObj);

        }catch(Exception e){

        }*/
    }

}
