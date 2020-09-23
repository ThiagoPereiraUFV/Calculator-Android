package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
	private double value1;
	private double value2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void handleClick(View v) {
		final String tag = (String) v.getTag();
		TextView result = (TextView) findViewById(R.id.result);
		try {
			this.value1 = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.value1)).getText()));
			this.value2 = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.value2)).getText()));
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
		}
	}
}