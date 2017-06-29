package com.example.p3750852.caloree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  private EditText txtWeight, txtAge; // ezek elé txt vagy btn prefixeket szoktunk írni hogy egyből
  // látsszon micsodák
  private EditText txtCalorie;
  private TextView txtResult;
  private Button btnCountMale;
  private Button btnCountFemale;

  /**
   * Static mert az osztályból semmit nem kell használnia, és amúgy is inkább utility mint
   * állapot függvény
   * @param age alany kora
   * @param weight alany kövérségének numerikus kifejezése
   * @param isMale alany péniszének egzisztencia jelzője
   * @return egy tökéletes világban ennyit enne emberünk
   */
  public static double getIdealCalorie(double age, double weight, boolean isMale) {
    float genderMultiplier = isMale ? 1.5f : 1.2f;
    return (100 + age) * genderMultiplier + weight * 13;
  }

  private double getAvailableCalories(boolean isMale) {
    return getIdealCalorie(getAge(), getWeight(), isMale);
  }

  private double getAge() {
    return Double.valueOf(txtAge.getText().toString());
  }

  private double getWeight() {
    return Double.valueOf(txtWeight.getText().toString());
  }

  private double getActualCalorie() {
    return Double.valueOf(txtCalorie.getText().toString());
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intake);

    // Az első mindig a View-k megkeresése, addig nem használhatod őket!!
    txtCalorie = (EditText) findViewById(R.id.calorie_text);
    txtResult = (TextView) findViewById(R.id.result);
    txtWeight = (EditText) findViewById(R.id.weight_text);
    txtAge = (EditText) findViewById(R.id.age_text);
    btnCountMale = (Button) findViewById(R.id.countM);
    btnCountFemale = (Button) findViewById(R.id.countF);

    btnCountMale.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        MainActivity.this.runOnUiThread(new Runnable() {
          @Override public void run() {
            CalorieTruthM();
          }
        });
      }
    });

    btnCountFemale.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        MainActivity.this.runOnUiThread(new Runnable() {
          @Override public void run() {
            CalorieTruthF();
          }
        });
      }
    });

    if (txtWeight.getText().toString().isEmpty()) {
      Toast.makeText(getApplicationContext(), getString(R.string.name_error), Toast.LENGTH_LONG)
          .show();
      return;
    }

    if (txtAge.getText().toString().isEmpty()) {
      Toast.makeText(getApplicationContext(), getString(R.string.name_error), Toast.LENGTH_LONG)
          .show();
      return;
    }

    SharedPreferences.Editor b =
        getApplicationContext().getSharedPreferences("CaloRee", MODE_PRIVATE) // itt a string a
            // tároló nevét adja meg!
            .edit();
    b.putString("Age", txtAge.getText().toString());
    b.putString("Weight", txtWeight.getText().toString());
    b.apply(); // elég egy SharedPreferences.Editor :)
  }

  public void CalorieTruthM() {
    double availableM = getAvailableCalories(true);
    double idealCalorieM = getIdealCalorie(getAge(), getWeight(), true);

    if (getActualCalorie() == idealCalorieM) {
      txtResult.setText("You can still eat "
          + availableM
          + " calories today! Haha, you can't actually eat anything today anymore.");
    } else if (getActualCalorie() < idealCalorieM) {
      txtResult.setText("Skinny! You can still eat like " + availableM + " calories");
    } else if (getActualCalorie() > idealCalorieM) {
      txtResult.setText("Oops, ain't it a bit too much? Drop that fork, will you? "
          + availableM
          + " points for you!");
    }
  }

  public void CalorieTruthF() {
    double availableF = getAvailableCalories(false);
    double idealCalorieF = getIdealCalorie(getAge(), getWeight(), false);

    if (getActualCalorie() == idealCalorieF) {
      txtResult.setText("You can still eat "
          + availableF
          + " calories today! Haha, you can't actually eat anything today anymore.");
    } else if (getActualCalorie() < idealCalorieF) {
      txtResult.setText("Skinny! You can still eat like " + availableF + " calories");
    } else if (getActualCalorie() > idealCalorieF) {
      txtResult.setText("Oops, ain't it a bit too much? Drop that fork, will you? "
          + availableF
          + " points for you!");
    }
  }
}