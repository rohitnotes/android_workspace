package com.gm.sec_4_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // boolean bartIsVisible = true;
    public void fade(View view){
        Log.i("Anim", "Image view clicked");
        ImageView bartImageView = (ImageView) findViewById(R.id.bartImageView);
        ImageView homerImageView = (ImageView) findViewById(R.id.homerImageView);
        // Move Right / down transY
        // bartImageView.animate().translationX(-2000).setDuration(2000);

        //Rotate
       //bartImageView.animate().rotation(1800).setDuration(1000);

        //Rotate & fade
        //bartImageView.animate().rotation(1800).alpha(0).setDuration(1000);

        // Scale
        // bartImageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);


        // Bart and Homer switch
//        if(bartIsVisible) {
//            bartImageView.animate().alpha(0).setDuration(2000);
//            homerImageView.animate().alpha(1).setDuration(2000);
//            bartIsVisible = false;
//        }else {
//            homerImageView.animate().alpha(0).setDuration(2000);
//            bartImageView.animate().alpha(1).setDuration(2000);
//            bartIsVisible = true;
//        }
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
