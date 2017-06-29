package com.example.p3750852.caloree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    EditText weight, age;
    EditText calorie;
    TextView result;
    Button countMale;
    Button countFemale;

    final double age1 = parseInt(age.toString());
    final double weight1 = parseInt(weight.toString());
    double actualCalorie = Double.parseDouble(calorie.toString());

    double idealCalorieM = (100 + age1) * 1.5 + weight1 * 13;
    double idealCalorieF = (100 + age1) * 1.2 + weight1 * 13;

    double availableM = idealCalorieM - actualCalorie;
    double availableF = idealCalorieF - actualCalorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intake);


        if (weight.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.name_error), Toast.LENGTH_LONG).show();
            return;
        }

        if (age.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), getString(R.string.name_error), Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences.Editor b = getApplicationContext().getSharedPreferences(age.getText().toString(), MODE_PRIVATE).edit();
        b.putString("Age", age.getText().toString());
        b.apply();

        SharedPreferences.Editor w = getApplicationContext().getSharedPreferences(weight.getText().toString(), MODE_PRIVATE).edit();
        w.putString("Weight", weight.getText().toString());
        w.apply();


        calorie = (EditText) findViewById(R.id.calorie_text);

        result = (TextView) findViewById(R.id.result);

        countMale = (Button) findViewById(R.id.countM);
        countFemale = (Button) findViewById(R.id.countF);

        countMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //     try {
                        CalorieTruthM();
                        //    } catch (Exception e) {
                        //        e.printStackTrace();
                        //        Log.e(TAG, "fail");
                        //    }
                    }
                });

            }
        });


        countFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //     try {
                        CalorieTruthF();
                        //    } catch (Exception e) {
                        //        e.printStackTrace();
                        //        Log.e(TAG, "fail");
                        //    }
                    }
                });

            }
        });

    }



    public void CalorieTruthM() {

        if (actualCalorie == idealCalorieM) {
            result.setText("You can still eat " + availableM + " calories today! Haha, you can't actually eat anything today anymore.");
        } else if (actualCalorie < idealCalorieM) {
            result.setText("Skinny! You can still eat like " + availableM + " calories");
        } else if (actualCalorie > idealCalorieM) {
            result.setText("Oops, ain't it a bit too much? Drop that fork, will you? " + availableM + " points for you!");
        }
    }

    public void CalorieTruthF() {

        if (actualCalorie == idealCalorieF) {
            result.setText("You can still eat " + availableF + " calories today! Haha, you can't actually eat anything today anymore.");
        } else if (actualCalorie < idealCalorieM) {
            result.setText("Skinny! You can still eat like " + availableF + " calories");
        } else if (actualCalorie > idealCalorieM) {
            result.setText("Oops, ain't it a bit too much? Drop that fork, will you? " + availableF + " points for you!");
        }
    }

}