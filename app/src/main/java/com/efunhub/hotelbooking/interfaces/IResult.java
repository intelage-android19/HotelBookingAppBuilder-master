package com.efunhub.hotelbooking.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by Admin on 17-11-2017.
 */

public interface IResult {

    void notifySuccess(int requestId, String response);
    void notifyError(int requestId, VolleyError error);
}
