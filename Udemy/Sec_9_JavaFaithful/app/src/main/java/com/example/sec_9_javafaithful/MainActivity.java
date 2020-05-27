package com.example.sec_9_javafaithful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Kotlin and Java together
        // Go to package - right click - create class in kotlin Dog
        Dog myDog = new Dog("Milo", 3);

        // To convert exiting Java file to kotlin
        // Click on file - go to code and select convert to kotlin

    }
}
