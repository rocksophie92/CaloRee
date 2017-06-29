package com.example.p3750852.caloree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by P3750852 on 2017.06.15..
 */

public class InitActivity extends AppCompatActivity {

  private static final String TAG = "InitActivity"; // Ebben az esetben a static oké, de
  private EditText name;
  private Button save; // Ha itt definiálod, később máshol is tudod használni, pl onResume()
  private String userName; // Bármiben ami Context-et extendel, ne használj static-ot nem konstansra

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    save = (Button) findViewById(R.id.save_button);
    name = (EditText) findViewById(R.id.user_text);

    save.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (name.getText().length() == 0) { // ha a hossza 0, akkor amúgy is üres
          Toast.makeText(v.getContext(), getString(R.string.name_error), Toast.LENGTH_LONG)
              .show(); // a legszűkebb kontextust válaszd, hogy hamarabb megtalálj esetleges hibákat
        } else if (name.getText().toString().equals("Sophie")) {

          // SharedPreferences.Editor e = getApplicationContext().getSharedPreferences(name.getText().toString(), MODE_PRIVATE).edit();
          // e.putString("Name", name.getText().toString());
          // e.apply();
          Log.d(TAG, "Mennie kéne. Megadott kifejezés: " + name.getText());
          startActivity(new Intent(InitActivity.this, MainActivity.class));
        }
      }
    });
  }
}