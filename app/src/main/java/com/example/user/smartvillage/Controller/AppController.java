package com.example.user.smartvillage.Controller;

import android.content.Context;


public class AppController {

    private static AppController mInstance;
    private static Context mCtx;

    private AppController(Context context) {
        mCtx = context;
    }

    public static synchronized AppController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppController (context);
        }
        return mInstance;
    }
}
