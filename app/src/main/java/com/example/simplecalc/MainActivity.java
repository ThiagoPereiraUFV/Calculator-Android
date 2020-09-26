package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Ciclo", getLocalClassName() +  ": Activity criada!");
		setContentView(R.layout.activity_main);
		setTitle(R.string.mainActivitytitle);
	}

	protected void onStart() {
		super.onStart();
		Log.d("Ciclo", getLocalClassName() +  ": Activity iniciada!");
	}

	protected void onRestart() {
		super.onRestart();
		Log.d("Ciclo", getLocalClassName() +  ": Activity reiniciada!");
	}

	protected void onResume() {
		super.onResume();
		Log.d("Ciclo", getLocalClassName() +  ": Activity retomada!");
	}

	protected void onPause() {
		super.onPause();
		Log.d("Ciclo", getLocalClassName() +  ": Activity pausada!");
	}

	protected void onStop() {
		super.onStop();
		Log.d("Ciclo", getLocalClassName() +  ": Activity interrompida!");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d("Ciclo", getLocalClassName() +  ": Activity destruída!");
	}

	//  Function to return string from resources given id
	private String getResourceString(final int id) {
		return getApplicationContext().getResources().getString(id);
	}

	public void handleClick(final View v) {
		//  Defining views
		final String tag = (String) v.getTag();
		final TextView result = (TextView) findViewById(R.id.result);

		//  Verify if user clicked to change calculator
		if(tag.equals("change")) {
			final Intent it = new Intent(getBaseContext(), ComplexCalc.class);
			startActivity(it);
			return;
		}

		try {
			//  Defining value variables
			final BigDecimal value1 = new BigDecimal(String.valueOf(((EditText) findViewById(R.id.value1)).getText()));
			final BigDecimal value2 = new BigDecimal(String.valueOf(((EditText) findViewById(R.id.value2)).getText()));

			try {
				//  Setting operation result
				switch(tag) {
					case "add":
						result.setText(getResourceString(R.string.result) + " " + value1.add(value2));
						break;
					case "sub":
						result.setText(getResourceString(R.string.result) + " " + value1.subtract(value2));
						break;
					case "mul":
						result.setText(getResourceString(R.string.result) + " " + value1.multiply(value2));
						break;
					case "div":
						if(value2.equals(new BigDecimal("0"))) {
							Log.e("Error", getResourceString(R.string.divisionByZero));
							result.setText(getResourceString(R.string.divisionByZero));
						} else {
							result.setText(getResourceString(R.string.result) + " " + value1.divide(value2, MathContext.DECIMAL128));
						}
						break;
					default:
						break;
				}
			} catch(Exception e) {
				Log.e("Error", Objects.requireNonNull(e.getMessage()));
				result.setText(getResourceString(R.string.error));
			}
		} catch(Exception e) {
			Log.e("Error", Objects.requireNonNull(e.getMessage()));
			result.setText(getResourceString(R.string.fillFields));
		}
	}
}