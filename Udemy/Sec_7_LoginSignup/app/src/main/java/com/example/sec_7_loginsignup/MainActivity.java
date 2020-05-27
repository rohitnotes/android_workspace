package com.example.sec_7_loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{
    TextView loginTextView;
    Boolean signupModeActive = true;
    EditText usernameTextView;
    EditText passwordTextView;

    // implements View.OnClickListener will require OnClick to handle on click events on the activity at single place
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginTextView){
            Log.i("Switch", "Switch was tapped");
            Button signupButton = findViewById(R.id.signUpButton);
            if(signupModeActive){
                signupModeActive = false;
                signupButton.setText("Login");
                loginTextView.setText("Or, Sign Up");
            }else{
                signupModeActive = true;
                signupButton.setText("Sign Up");
                loginTextView.setText("Or, Login");
            }
        }else if(v.getId() == R.id.logoImageView || v.getId() == R.id.backgroundLayout){
            // Dismiss the keyboard
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0); // Closes the keyboard
        }
    }

    // implements View.OnKeyListener will require onKey to handle on key events on the activity
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
            Log.i("onKey", "KeyEvent");
            signUpClicked(v);
        }
        return false;
    }

    public void signUpClicked(View view){
        Log.i("Login", "### Button Clicker");

        if(usernameTextView.getText().toString().matches("") || passwordTextView.getText().toString().matches("")){
            Log.i("Login", " No Username & Password");
            Toast.makeText(MainActivity.this, "A username and a password are required", Toast.LENGTH_SHORT).show();
        }else{
            if(signupModeActive) {
                ParseUser user = new ParseUser();
                user.setUsername(usernameTextView.getText().toString());
                user.setPassword(passwordTextView.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.i("Signup", "Done");
                        if (e == null) {
                            Log.i("Signup", "Success");
                            showUserList();
                        } else {
                            Toast.makeText(MainActivity.this, "exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
                ParseUser.logInInBackground(usernameTextView.getText().toString(), passwordTextView.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Log.i("Login", "Login OK !");
                            showUserList();
                        }else{
                            Toast.makeText(MainActivity.this, "exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }
    }

    public void showUserList(){
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ParseUser.getCurrentUser().logOut();   // In case you modify database and invalid session issue

        ImageView logoImageView = findViewById(R.id.logoImageView);
        RelativeLayout backgroundLayout = findViewById(R.id.backgroundLayout);
        logoImageView.setOnClickListener(this);
        backgroundLayout.setOnClickListener(this);

        loginTextView = findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(this);

        usernameTextView = findViewById(R.id.usernameTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        passwordTextView.setOnKeyListener(this);

        if(ParseUser.getCurrentUser() != null){
            showUserList();
        }
    }



}
