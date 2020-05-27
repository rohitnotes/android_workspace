package com.gm.higherlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    class Number{
        int number;

        public boolean isSquare(){
            double d = Math.sqrt(number);
            if(d == Math.floor(d)){
                return true;
            }else{
                return false;
            }
        }

        public boolean isTriangular(){
            int x = 1;
            int triangularNumber = 1;
            while(triangularNumber < number){
                x++;
                triangularNumber += x;
            }

            if(triangularNumber == number){
                return true;
            }else{
                return false;
            }
        }
    }

    public void testNumber(View view){
        Log.i("Test", "Button Pressed");
        Number myNumber = new Number();

        EditText editText = (EditText) findViewById(R.id.editText);
        String msg = editText.getText().toString();

        if(editText.getText().toString().isEmpty()){
            msg = "Please Enter a Number";
        }else {

            myNumber.number = Integer.parseInt(editText.getText().toString());
            Log.i("Test", " Sq Number " + myNumber.isSquare());

            if (myNumber.isSquare() && myNumber.isTriangular()) {
                msg += "is Square and triangular";
            } else if (myNumber.isSquare()) {
                msg += "is Square but Not triangular";
            } else if (myNumber.isTriangular()) {
                msg += "is Not Square but triangular";
            } else {
                msg += "is neither Square nor triangular";
            }
        }

        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
