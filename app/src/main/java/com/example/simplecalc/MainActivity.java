package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Ciclo", getLocalClassName() +  ": Activity criada!");
		setContentView(R.layout.activity_main);
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

	public void handleClick(View v) {
		//  Defining views
		final String tag = (String) v.getTag();
		final TextView result = (TextView) findViewById(R.id.result);

		//  Defining values variables
		double value1;
		double value2;

		if(tag.equals("change")) {
			final Intent it = new Intent(getBaseContext(), ComplexCalc.class);
			startActivity(it);
			return;
		}

		try {
			value1 = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.value1)).getText()));
			value2 = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.value2)).getText()));
		} catch(Exception e) {
			result.setText("Preencha os dois campos!");
			return;
		}

		switch(tag) {
			case "add":
				result.setText("O resultado é: " + (value1 + value2));
				break;
			case "sub":
				result.setText("O resultado é: " + (value1 - value2));
				break;
			case "mul":
				result.setText("O resultado é: " + (value1 * value2));
				break;
			case "div":
				if(value2 == 0) {
					result.setText("Divisão por zero é ilegal!");
				} else {
					result.setText("O resultado é: " + (value1 / value2));
				}
				break;
			default:
				break;
		}
	}
}