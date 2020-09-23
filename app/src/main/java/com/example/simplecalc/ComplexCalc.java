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

    public void handleClick(Button v) {
        //  Getting button attributes
        final String tag = (String) v.getTag();
        final String btn = (String) v.getText();

        //  Defining expression View
        final EditText expression = (EditText) findViewById(R.id.expression);

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
            case "simple":
                finish();
                break;
            default:
                expression.setText(expression.getText() + btn);
                break;
        }
    }
}