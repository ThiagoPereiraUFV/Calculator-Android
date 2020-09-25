package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;

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

	private void toast(String message) {
		final Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
		toast.show();
	}

	public void handleClick(View v) {
		//  Getting button attributes
		final String tag = (String) (((Button) v).getTag());
		final String btn = (String) (((Button) v).getText());

		//  Defining expression View
		final EditText expression = (EditText) findViewById(R.id.expression);

		switch(tag) {
			case "backspace":
				if(expression.getText().length() > 0) {
					expression.setText(expression.getText().subSequence(0, expression.getText().length() - 1));
				}
				break;
			case "clear":
				expression.setText("");
				break;
			case "result":
				try {
					final String[] exp = expression.getText().toString().split(" ");
					final double v1 = Double.parseDouble(exp[0]), v2 = Double.parseDouble(exp[2]);
					final String op = exp[1];

					if(op.equals("+")) {
						expression.setText(Double.toString(v1 + v2));
					} else if(op.equals("-")) {
						expression.setText(Double.toString(v1 - v2));
					} else if(op.equals("*")) {
						expression.setText(Double.toString(v1 * v2));
					} else if(op.equals("/")){
						if(v2 != 0) {
							expression.setText(Double.toString(v1 / v2));
						} else {
							toast(getApplicationContext().getResources().getString(R.string.divisionByZero));
							break;
						}
					}
				} catch(Exception e) {
					final String message = "Erro de sintaxe!";
					Log.e("DebugCalc", message);
					toast(getApplicationContext().getResources().getString(R.string.syntaxError));
					break;
				}
				break;
			case "change":
				finish();
				return;
			default:
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
					} else if(Character.toString(expression.getText().charAt(expression.getText().length() - 1)).matches("[0-9]")) {
						expression.setText(expression.getText() + " " + btn + " ");
						Log.d("DebugCalc", "Adicionou novo símbolo +- n");
					} else if(Character.toString(expression.getText().charAt(expression.getText().length() - 1)).matches(" ")) {
						expression.setText(expression.getText() + btn);
						Log.d("DebugCalc", "Adicionou novo símbolo +- s");
					}
				} else if(btn.matches("\\.")) {
					if(expression.getText().length() == 0) {
						break;
					} else if(Character.toString(expression.getText().charAt(expression.getText().length() - 1)).matches("\\.")) {
						break;
					} else {
						expression.setText(expression.getText() + btn);
					}
				} else {
					expression.setText(expression.getText() + btn);
					Log.d("DebugCalc", "Adicionou novo símbolo");
				}
				//Log.d("DebugCalc", "Adicionou novo símbolo");
				break;
		}
	}
}