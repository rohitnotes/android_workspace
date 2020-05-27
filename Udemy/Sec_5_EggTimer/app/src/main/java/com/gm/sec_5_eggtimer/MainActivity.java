package com.gm.sec_5_eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerText;
    SeekBar seekBar;
    boolean counterIsActive = false;
    Button goButton;

    public  void buttonClick(View view){
        Log.i("Time", " Button Pressed");
        counterIsActive = true;
        seekBar.setEnabled(false);
        goButton.setText("Stop");

        CountDownTimer countDownTimer = new CountDownTimer(seekBar.getProgress()*1000+100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Log.i("Time", " Timer Finished");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.welcome);
                mediaPlayer.start();
            }
        }.start();
    }

    public void updateTimer(int progress){
        int minutes = progress / 60;
        int seconds = progress - (minutes * 60);

        String secondsString= Integer.toString(seconds);
        if(seconds <= 9){
            secondsString = "0"+seconds;
        }
        timerText.setText(Integer.toString(minutes) + " : " + secondsString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        timerText = findViewById(R.id.textView);
        goButton = findViewById(R.id.goButton);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
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
