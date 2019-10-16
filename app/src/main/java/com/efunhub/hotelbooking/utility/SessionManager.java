package com.efunhub.hotelbooking.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.efunhub.hotelbooking.activity.LoginActivity;
import com.efunhub.hotelbooking.activity.MainActivity;

import java.util.HashMap;

/**
 * Created by Admin on 30-10-2017.
 */

public class SessionManager {

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context mContext;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "CableAgentPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    //FCM Token
    public static final String KEY_FCM_TOKEN = "token";

    // ID (make variable public to access from outside)
    public static final String KEY_ID = "agid";

    public static final String KEY_NAME = "name";

    public static final String KEY_EMAIL = "email";

    public static final String KEY_CONTACT = "contact";
    public static final String KEY_OPERATOR_ID = "operator";
    public static final String KEY_AGENT_ID= "agent";

    //Referrer ID
    public static final String REFERRER_ID = "referrer_id";



    // Constructor
    public SessionManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String id ) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing ID in pref
        editor.putString(KEY_ID, id);
      /*  editor.putString(KEY_CONTACT, contact);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_OPERATOR_ID, operatorId);
        editor.putString(KEY_AGENT_ID, agentid);*/

        // commit changes
        editor.commit();
    }

    public void storeRegIdInPref(String token) {
        editor.putString(KEY_FCM_TOKEN, token);
        editor.commit();
    }

    public HashMap<String, String> getRegIdPref() {
        HashMap<String, String> token = new HashMap<String, String>();

        // token
        token.put(KEY_FCM_TOKEN, pref.getString(KEY_FCM_TOKEN, null));

        return token;
    }

    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> user = new HashMap<String, String>();

        // user info
        user.put(KEY_ID, pref.getString(KEY_ID, null));

     /*   user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        user.put(KEY_CONTACT, pref.getString(KEY_CONTACT, null));

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        user.put(KEY_OPERATOR_ID, pref.getString(KEY_OPERATOR_ID, null));

        user.put(KEY_AGENT_ID, pref.getString(KEY_AGENT_ID, null));
*/

        return user;
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (this.isLoggedIn()) {

            // user is logged in redirect him to Main Activity
            Intent i = new Intent(mContext, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(i);

        } else {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(mContext, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(i);
        }

    }

    /*To store referrer id*/
    public void storeReferrerIdInPref(String refId) {
        editor.putString(REFERRER_ID, refId);
        editor.commit();
    }

    /*To get referrer id while registering new user*/
    public HashMap<String, String> getReferIdPref() {
        HashMap<String, String> referrId = new HashMap<String, String>();

        // referrId
        referrId.put(REFERRER_ID, pref.getString(REFERRER_ID, null));

        return referrId;
    }




    /**
     * Clear session details
     */

    public void removeLogin(){

        editor.putBoolean(IS_LOGIN, false);

        editor.remove(KEY_ID);

        editor.commit();
    }

    public void logoutUser() {
        removeLogin();
        // Clearing all data from Shared Preferences
        // After logout redirect user to Login Activity
        Intent i = new Intent(mContext, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mContext.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


}
