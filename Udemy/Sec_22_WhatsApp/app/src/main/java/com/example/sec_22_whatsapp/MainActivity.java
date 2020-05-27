package com.example.sec_22_whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    Boolean loginModeActive = false;
    EditText usernameEditText, passwordEditText;
    Button signupButton;
    TextView toggleLoginMode;

    public void redirectIfLoggedIn(){
        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
            startActivity(intent);
        }
    }

    public  void toggleLoginMode(View view){

        if(loginModeActive){
            toggleLoginMode.setText("Log In ");
            signupButton.setText("Or SignUp");
        }else{
            loginModeActive = true;
            toggleLoginMode.setText("Or SignUp");
            signupButton.setText("Log In");
        }
    }

    public void signUpLogin(View view){
        ParseUser user = new ParseUser();
        if(loginModeActive){
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e == null){
                        Log.i("INFO", "User Logged In");
                        redirectIfLoggedIn();
                    }else{
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else {

            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null){
                        Log.i("INFO", " User Signed up !");
                        redirectIfLoggedIn();
                    }else{
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Whats App Login !");
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signupButton = findViewById(R.id.signupButton);
        toggleLoginMode = findViewById(R.id.toggleLoginModeTextView);
        redirectIfLoggedIn();
    }
}
