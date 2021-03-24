package com.example.user.smartvillage.Activity;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.smartvillage.Activity.dashboard_user.MainActivity;
import com.example.user.smartvillage.Controller.SessionManager;
import com.example.user.smartvillage.R;

public class SplashScreenActivity extends AppCompatActivity {

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        session = new SessionManager(getApplicationContext());
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){
            if (session.checkLogin()){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
            }
        }, 1000);
    }
}
