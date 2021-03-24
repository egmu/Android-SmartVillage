package com.example.user.smartvillage.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smartvillage.Model.DefaultModel;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpActivity extends AppCompatActivity {

    Button button_signup , button_link_signin;
    EditText et_email, et_username,et_confirmpass, et_password;
    String smail, susername, sconfirmpass, spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        button_signup = (Button) findViewById(R.id.button_link_signup);
        button_link_signin = (Button) findViewById(R.id.button_signin);

        et_email = (EditText) findViewById(R.id.Signup_EMAIL);
        et_username = (EditText) findViewById(R.id.Signup_username);
        et_confirmpass = (EditText) findViewById(R.id.Signup_Konfimasi_password);
        et_password = (EditText) findViewById(R.id.Signup_password);

        button_link_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignIn();
            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smail = et_email.getText().toString();
                susername = et_username.getText().toString();
                spassword = et_password.getText().toString();
                sconfirmpass = et_confirmpass.getText().toString();
                if (smail.isEmpty() || susername.isEmpty() || spassword.isEmpty() || sconfirmpass.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Pastikan anda sudah mengisi semua data dengan benar", Toast.LENGTH_SHORT).show();
                } else if (!spassword.equals(sconfirmpass)){
                    Toast.makeText(SignUpActivity.this, "Password Tidak sama", Toast.LENGTH_SHORT).show();
                } else {
                    doSignUp(smail, susername, spassword);
                }
            }
        });
    }

    private void doSignUp(String snik_data, String susername_data, String spassword_data){
        final ProgressDialog pDialog = new ProgressDialog(SignUpActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setMessage("Registering..");
        pDialog.show();
        ApiService.service_post.postSignUp(snik_data, susername_data, spassword_data).enqueue(new Callback<DefaultModel>() {
            @Override
            public void onResponse(Call<DefaultModel> call, retrofit2.Response<DefaultModel> response) {
                System.out.println(response);
                if(response.body().isStatus()){
                    Toast.makeText(SignUpActivity.this, "Chek email anda untuk mengonfirmasi email anda", Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
                    toSignIn();
                }else{
                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DefaultModel> call, Throwable t) {
                pDialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void toSignIn(){
        //TODO PINDAH INTENT KE SIGNIN
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}
