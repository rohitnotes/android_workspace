package com.example.sec_12_autocomplet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Auto Complete
        String[] friends = {"Teddy", "Maddy", "Milo","Doron", "Ido", "Sara", "Nino", "Jake"};

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friends);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1); // Set how many characters to trigger the suggestion, by default its 2


        // Auto Fill
        // Add to xml : android:autofillHints="AUTOFILL_HINT_USERNAME"

    }
}
