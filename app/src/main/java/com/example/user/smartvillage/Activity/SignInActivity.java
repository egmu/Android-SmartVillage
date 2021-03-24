package com.example.user.smartvillage.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smartvillage.Activity.dashboard_user.MainActivity;
import com.example.user.smartvillage.Controller.SessionManager;
import com.example.user.smartvillage.Model.User;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {

    Button button_signin, button_linksignup;
    EditText et_username, et_password;
    String susername, spassword;
    Intent afterlogin;
    ProgressDialog pDialog;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        button_signin = (Button) findViewById(R.id.button_signin);
        button_linksignup = (Button) findViewById(R.id.button_link_signup);
        sessionManager = new SessionManager(getApplicationContext());
        button_linksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO PINDAH INTENT KE SINGUP
                Intent activityselanjutnya = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(activityselanjutnya);
//                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://192.168.43.191/smartvillage-1/frontend/web/index.php?r=site%2Fsignup")));
            }
        });

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO LOGIN
                susername = et_username.getText().toString().trim();
                spassword = et_password.getText().toString().trim();
                if (susername.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "User Kosong", Toast.LENGTH_SHORT).show();
                } else if (spassword.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Pass Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    doSignIn(susername,spassword);
                }
            }
        });

    }
    private void doSignIn(final String username, final String password) {
        pDialog = new ProgressDialog(SignInActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setMessage("Tunggu Sebentar...");
        pDialog.show();
        ApiService.service_post.postSignIn(username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if(response.body().isStatus()){
                    pDialog.dismiss();
                    Intent dashboard = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(dashboard);
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "User atau Pass salah", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                pDialog.dismiss();
            }
        });
    }
}