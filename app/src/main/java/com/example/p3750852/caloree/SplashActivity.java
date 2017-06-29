package com.example.p3750852.caloree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by P3750852 on 2017.06.15..
 */

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences s = getApplicationContext().getSharedPreferences(InitActivity.userName, MODE_PRIVATE);
        String name = s.getString(InitActivity.userName, "");
        if (name.equals("")) {
            startActivity(new Intent(getApplicationContext(), InitActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }

    }
}
