package com.example.user.smartvillage.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.user.smartvillage.Activity.SignInActivity;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "UserPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "usernam";
    public static final String KEY_ROLE = "role";
    public static final String KEY_TOKEN = "token";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(int id, String username, String role, String token){
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_ID, id);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_TOKEN, token);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public Boolean checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
//            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(_context, LoginActivity.class);
//            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            // Staring Login Activity
//            _context.startActivity(i);
            return false;
        }
    return true;
    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

//        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
//        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
//        user.put(KEY_USERNAME,pref.getString(KEY_USERNAME,null));
//        user.put(KEY_iDENTITY_NUMBER,pref.getString(KEY_iDENTITY_NUMBER,null));
//        user.put(KEY_ADDRESS,pref.getString(KEY_ADDRESS,null));
//        user.put(KEY_POSTCODE,pref.getString(KEY_POSTCODE,null));
//        user.put(KEY_PHONE_NUUMBER,pref.getString(KEY_PHONE_NUUMBER,null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
//        Intent i = new Intent(_context, SignInActivity.class);
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // Staring Login Activity
//        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn()
    {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public int getKeyIdet()
    {
      return pref.getInt(KEY_ID,0);
    }

    public String getKeyUsername() {
        return pref.getString(KEY_USERNAME,"");
    }

    public String getKeyRole() {
        return pref.getString(KEY_ROLE,"");
    }

    public String getKeyToken() {
        return pref.getString(KEY_TOKEN,"");
    }

}
