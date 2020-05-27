package com.gm.sec_5_timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView timesTableListView;

    public void generateTimeTable(int timeTableNumber){
        ArrayList<String> timesTableContent = new ArrayList<String>();
        for(int i = 1; i <= 10; i++){
            int temp = timeTableNumber * i;
            timesTableContent.add(Integer.toString(temp));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTableContent);
        timesTableListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timesTableSeekBar = findViewById(R.id.seekBar);
        timesTableListView = findViewById(R.id.listView);

        timesTableSeekBar.setMax(20);
        timesTableSeekBar.setProgress(10);
        generateTimeTable(10);

        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTableNumber;
                if(progress < min){
                    timesTableNumber = min;
                    timesTableSeekBar.setProgress(timesTableNumber);
                }else{
                    timesTableNumber = progress;
                }
                Log.i("TimesTable","Seeker Value : " + Integer.toString(timesTableNumber));
                generateTimeTable(timesTableNumber);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
