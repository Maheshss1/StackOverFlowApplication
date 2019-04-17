package com.example.mahesh.stackoverflowapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mahesh.stackoverflowapplication.model.LoginDetails;
import com.example.mahesh.stackoverflowapplication.support.Common;
import com.example.mahesh.stackoverflowapplication.support.StackOverFlowAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //client_id = 14886
    //scope = write_access
    //redirect_uri = https://stackexchange.com/oauth/login_success

    //https://stackoverflow.com/oauth/access_token/json?client_id=14887&
    // client_secret=LssV5TBehHTpcGMTc4Ltpw((&
    // code=W7Vf(uGTPt3mz11DZrdr8A))&
    // redirect_uri=https://stackexchange.com/oauth/login_success

    private static final int CLIENT_ID = 14887;
    private static final String SCOPE = "no_expire";
    private static final String CLIENT_SECRET = "LssV5TBehHTpcGMTc4Ltpw((";
    private static final String CODE = "XfXW1169hRBMkIDIeV(ZVQ))";
    private static final String REDIRECT_URI = "https://stackexchange.com/oauth/login_success";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 234);
    }


    public void loginToUserInterestActivity(View v) {

        startActivity(new Intent(MainActivity.this, UserInterestedActivity.class));
        finish();
        StackOverFlowAPI stackOverFlowAPI = Common.getStackOverFlowAPI(Common.STACKOVERFLOWURL);
        stackOverFlowAPI.login(CLIENT_ID, CLIENT_SECRET, CODE, REDIRECT_URI).enqueue(
                new Callback<LoginDetails>() {
                    @Override
                    public void onResponse(Call<LoginDetails> call, Response<LoginDetails> response) {
                        Log.d(TAG, "onResponse: " + response.body());
                        Log.d(TAG, "onFailure: " + call.request().toString());

                    }

                    @Override
                    public void onFailure(Call<LoginDetails> call, Throwable t) {
                        t.printStackTrace();
                        Log.d(TAG, "onFailure: " + call.request().toString());
                    }
                }
        );
    }

}
