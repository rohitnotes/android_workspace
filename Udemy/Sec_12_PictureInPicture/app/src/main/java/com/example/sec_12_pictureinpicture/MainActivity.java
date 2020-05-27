package com.example.sec_12_pictureinpicture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void goPIP(View view){
//         Set in manifest
//        android:resizeableActivity="true"
//        android:supportsPictureInPicture="true"
//        android:configChanges="screenSize|smallestScreenSize|screenLayout|orientation"
        enterPictureInPictureMode();
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);

        TextView textView = findViewById(R.id.textView);
        Button pipButton = findViewById(R.id.button);

        if(isInPictureInPictureMode){
            // Going into PiP
            pipButton.setVisibility(View.INVISIBLE);
            getSupportActionBar().hide();
            textView.setText("$10,004");
        }else{
            // Going out of Pip
            pipButton.setVisibility(View.VISIBLE);
            getSupportActionBar().show();
            textView.setText("Hello");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
