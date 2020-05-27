package com.gm.sec_5_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Long time = new Lo;
                try {
                     time = millisUntilFinished /1000;
                }catch (Throwable tr){
                    Log.i("Time", "Seconds Left : ", time, tr);
                }
            }

            @Override
            public void onFinish() {
                Log.i("Time", "Finished Here ! ");
            }
        }.start();



//        final Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Timer", " A sec passed by");
//                handler.postDelayed(this,1000);
//            }
//        };
//
//        handler.post(run);
    }
}
