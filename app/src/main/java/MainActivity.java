package com.example.bosto.calculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String display;
    private String currentOp = "";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView) findViewById(R.id.textview);
        screen.setText(display);
    }

    public void updatescreen() {
        screen.setText(display);
    }

    public void onClickNum(View v) {
        if(result != ""){
            clear();
            updatescreen();
        }
        Button button = (Button) v;
        display += button.getText();
        updatescreen();
    }

    public boolean isOperator(char op) {
        if (op == '+') {
            return true;
        } else if (op == '-') {
            return true;
        } else if (op == '*') {
            return true;
        } else if (op == '/') {
            return true;
        }
        else {
            return false;
        }
    }

    public void onClickOperator(View v) {
        if(display == ""){
            return;
        }
        Button button = (Button) v;

        if(result != ""){
            String  display1 = result;
            clear();
            display = display1;
        }

        if(currentOp != ""){
            Log.d("CalcX","" + display.charAt(display.length()-1));
            if (isOperator(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1), button.getText().charAt(0));
                updatescreen();
                return;
            }else{
                getresult();
                display = result;
                result = "";
            }
            currentOp = button.getText().toString();
        }
        display += button.getText();
        currentOp = button.getText().toString();
        updatescreen();
    }

    public void clear() {
        display = "";
        currentOp = "";
        result = "";
    }

    public void onClickClear(View v) {
        clear();
        updatescreen();
        result = "";
    }

    public Double operate(String a, String b, String op) {
        switch(op){
            case "+": try{
                return Double.valueOf(a) + Double.valueOf(b);
            }catch(Exception e){
                Log.d("calculation error", e.getMessage());
            }
            case"-":try{
                return Double.valueOf(a) - Double.valueOf(b);
            }catch(Exception e){
                Log.d("calculation error", e.getMessage());
            }
            case"*":  try{
                return Double.valueOf(a) * Double.valueOf(b);
            }catch(Exception e){
                Log.d("calculation error", e.getMessage());
            }
            case"/": try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch(Exception e){
                Log.d("calculation error", e.getMessage());
            }
                double temp;
                temp = -1.0;
                return temp;
        }
        double temp;
        temp = -1.0;
        return temp;
    }

    public boolean getresult(){
        if(currentOp == ""){
            return false;
        }
        String[] operation = display.split(Pattern.quote(currentOp));
        if (operation.length < 2){
            return false;
        }
        result = String.valueOf(operate(operation[0], operation[1], currentOp));
        return true;
    }

    public void onClickEqual(View v) {
        if(display == ""){
            return;
        }
        if(!getresult()){
            return;
        }
        screen.setText(display + "\n" + String.valueOf(result));
    }
}

