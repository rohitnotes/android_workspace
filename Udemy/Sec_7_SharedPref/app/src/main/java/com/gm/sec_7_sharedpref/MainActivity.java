package com.gm.sec_7_sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Storing string Shared Pref
        /*
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.gm.sec_7_sharedpref", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", "Tushar").apply();
        String username = sharedPreferences.getString("username",  "");
        Log.i("SharedPref", "Value : "+username);
        */

        ArrayList<String> friends = new ArrayList<String>();
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.gm.sec_7_sharedpref", Context.MODE_PRIVATE);

        friends.add("Tushar");
        friends.add("Mayur");
        friends.add("Gauri");
        friends.add("Neha");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
            Log.i("Serialize : ", ObjectSerializer.serialize(friends));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newFriends = new ArrayList<String>();
        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize((new ArrayList<String>()))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("New Friends :", newFriends.toString());


    }
}
