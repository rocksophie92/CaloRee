package com.example.p3750852.caloree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

/**
 * Created by P3750852 on 2017.06.15..
 */

public class InitActivity extends AppCompatActivity {

    private static final String TAG = "InitActivity";
    public static String userName;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button save;

        save = (Button) findViewById(R.id.save_button);
       name = (EditText) findViewById(R.id.user_text);

                save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.name_error), Toast.LENGTH_LONG).show();

                }
                else if (name.getText().toString().equals("Sophie")){

                   // SharedPreferences.Editor e = getApplicationContext().getSharedPreferences(name.getText().toString(), MODE_PRIVATE).edit();
                   // e.putString("Name", name.getText().toString());
                   // e.apply();
                    Log.d(TAG, "Mennie kéne");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }      }
        });
    }
}