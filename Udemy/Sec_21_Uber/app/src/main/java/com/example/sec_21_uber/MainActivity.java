package com.example.sec_21_uber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    public void redirectActivity(){
        if(ParseUser.getCurrentUser().get("riderOrDriver").equals("rider")){
            Intent intent = new Intent(getApplicationContext(), RiderActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), ViewRequestActivity.class);
            startActivity(intent);
        }
    }

    public void getStarted(View view){
        Switch userTypeSwitch = (Switch) findViewById(R.id.userTypeSwitch);
        Log.i("UserType : ", String.valueOf(userTypeSwitch.isChecked()));
        String userType = "rider";
        if(userTypeSwitch.isChecked()){
            userType = "driver";
        }
        if(ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().put("riderOrDriver", userType);
            ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    redirectActivity();
                }
            });

        }else{
            Log.i("Get Started", " Not logged in");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        if(ParseUser.getCurrentUser() == null){
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e == null){
                        Log.i("Login", "Anonymous Login Success");
                    }else{
                        Log.i("Login", "Anonymous Login Failed");
                    }
                }
            });
        }else{
            if(ParseUser.getCurrentUser().get("riderOrDriver") != null){
                Log.i("Login", "riderOrDriver : "+ ParseUser.getCurrentUser().get("riderOrDriver"));
                redirectActivity();
            }else {
                Log.i("Login", "No riderOrDriver data");
            }
        }

    }
}
