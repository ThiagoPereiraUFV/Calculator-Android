package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class ComplexCalc extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Ciclo", getLocalClassName() +  ": Activity criada!");
		setContentView(R.layout.activity_complex_calc);
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

	//  Function to show a toast message given a string
	private void toast(final String message) {
		final Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
		toast.show();
	}

	//  Function to return string from resources given id
	private String getResourceString(final int id) {
		return getApplicationContext().getResources().getString(id);
	}

	//  Function to pop the last char from a string and return it given a string
	private CharSequence popLastChar(final String s) {
		return s.subSequence(0, s.length() - 1);
	}

	//  Function to return the last char given a string
	private String getLastChar(final String s) {
		return Character.toString(s.charAt(s.length() - 1));
	}

	public void handleClick(View v) {
		//  Getting button attributes
		final String tag = (String) (((Button) v).getTag());
		final String btn = (String) (((Button) v).getText());

		//  Defining expression View
		final EditText expression = (EditText) findViewById(R.id.expression);

		label:
		switch(tag) {
			case "backspace":
				if(expression.getText().length() > 0) {
					expression.setText(popLastChar(expression.getText().toString()));

					if(expression.getText().length() > 0 && getLastChar(expression.getText().toString()).matches(" ")) {
						expression.setText(popLastChar(expression.getText().toString()));
					}
				}
				break;
			case "clear":
				expression.setText("");
				break;
			case "result":
				try {
					//  Getting expression operands and operator
					final String[] exp = expression.getText().toString().split(" ");
					final double v1 = Double.parseDouble(exp[0]), v2 = Double.parseDouble(exp[2]);
					final String op = exp[1];

					try {
						switch(op) {
							case "+":
								expression.setText(Double.toString(v1 + v2));
								break;
							case "-":
								expression.setText(Double.toString(v1 - v2));
								break;
							case "*":
								expression.setText(Double.toString(v1 * v2));
								break;
							case "/":
								if(v2 != 0) {
									expression.setText(Double.toString(v1 / v2));
								} else {
									Log.e("Error", getResourceString(R.string.divisionByZero));
									toast(getResourceString(R.string.divisionByZero));
									break label;
								}
								break;
						}
					} catch(Exception e) {
						Log.e("Error", Objects.requireNonNull(e.getMessage()));
						toast(getResourceString(R.string.error));
						break;
					}
				} catch(Exception e) {
					Log.e("Error", Objects.requireNonNull(e.getMessage()));
					toast(getResourceString(R.string.syntaxError));
					break;
				}
				break;
			case "change":
				finish();
				return;
			default:
				try {
					if(btn.matches("[*/]")) {
						if(expression.getText().length() == 0) {
							break;
						} else if(expression.getText().toString().contains("*") || expression.getText().toString().contains("/")) {
							break;
						} else {
							expression.setText(expression.getText() + " " + btn + " ");
						}
					} else if(btn.matches("[-+]")) {
						if(expression.getText().length() == 0) {
							expression.setText(btn);
						} else if(getLastChar(expression.getText().toString()).matches("[0-9]")) {
							expression.setText(expression.getText() + " " + btn + " ");
						} else if(getLastChar(expression.getText().toString()).matches(" ")) {
							expression.setText(expression.getText() + btn);
						} else if(getLastChar(expression.getText().toString()).matches("[*/]")) {
							expression.setText(expression.getText() + " " + btn);
						}
					} else if(btn.matches("\\.")) {
						if(expression.getText().length() == 0) {
							break;
						} else if(getLastChar(expression.getText().toString()).matches("\\.")) {
							break;
						} else {
							expression.setText(expression.getText() + btn);
						}
					} else {
						if(expression.getText().length() == 0) {
							expression.setText(btn);
						} else if(getLastChar(expression.getText().toString()).matches("[*/]")) {
							expression.setText(expression.getText() + " " + btn);
						} else {
							expression.setText(expression.getText() + btn);
						}
					}
				} catch(Exception e) {
					Log.e("Error", Objects.requireNonNull(e.getMessage()));
					toast(getResourceString(R.string.error));
				}
				break;
		}
	}
}