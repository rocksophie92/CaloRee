package com.example.p3750852.caloree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.sun.istack.internal.Nullable;

/**
 * Created by P3750852 on 2017.06.15..
 */

public class SplashActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    SharedPreferences s = getApplicationContext().getSharedPreferences("CaloRee", MODE_PRIVATE);
    @Nullable
    String name = s.getString("Name", null); // biztonságosabb null-ni, mert nincs adatunk
    if (name == null) {
      startActivity(new Intent(this, InitActivity.class)); // ide a from activityt szoktuk
      // contextnek írni
    } else {
      startActivity(new Intent(this, MainActivity.class));
    }
  }
}
