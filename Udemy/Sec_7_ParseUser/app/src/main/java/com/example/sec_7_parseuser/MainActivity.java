package com.example.sec_7_parseuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/*
    We are adding our own user login information hence make sure to comment out
    ParseUser.enableAutomaticUser();

    Due to Automatic User parser creates default without any info user

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // ######## Section 1
        // Sign-up mechanism
        /*
        ParseUser user = new ParseUser();
        user.setUsername("Teddy");
        user.setPassword("myPass");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    // OK
                    Log.i("Sign Up", "Signed succesfully");
                }else{
                    e.printStackTrace();
                }
            }
        });
         */

        // ######## Section 2
        // Log in mechanism
        /*
        ParseUser.logInInBackground("Teddy", "myPass", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null){
                    Log.i("Login ", " Success");
                }else{
                    e.printStackTrace();
                }
            }
        });
        */

        // ######## Section 3
        // If logged already in mechanism

        if(ParseUser.getCurrentUser() != null){
            Log.i("Signed in", ParseUser.getCurrentUser().getUsername());
        }else{
            Log.i("Login", " No Signed in");
        }

        // ######## Section 4
        // To logged out
        ParseUser.logOut();

    }
}
