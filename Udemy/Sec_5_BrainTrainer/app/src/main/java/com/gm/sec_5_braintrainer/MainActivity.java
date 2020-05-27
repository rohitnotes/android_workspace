package com.gm.sec_5_braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton, button0, button1, button2, button3, playAgainButton;
    TextView sumTextView, resultTextView, scoreTextView, timerTextView;
    ConstraintLayout gameLayout;

    int num1, num2;
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Random rand = new Random();
    int locationOfCorrectAns, score = 0, numberOfQuestions = 0;

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        generateNewQuestion();
        PlayAgain(timerTextView);
    }

    public void ChooseAnswer(View view){
        Log.i("Brain", view.getTag().toString());
        if(Integer.toString(locationOfCorrectAns).equals(view.getTag().toString())){
            Log.i("Brain : ", "Correct Ans");
            resultTextView.setText("Correct !");
            score++;
            generateNewQuestion();
        }else{
            Log.i("Brain : ", "Wrong Ans");
            resultTextView.setText("Wrong !");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));

    }

    public void PlayAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        generateNewQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time Out !");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void generateRandom(){

        num1 = rand.nextInt(21);
        num2 = rand.nextInt(21);
    }

    public  void generateNewQuestion(){
        generateRandom();
        sumTextView.setText(Integer.toString(num1) +  " + " + Integer.toString(num2)) ;

        locationOfCorrectAns = rand.nextInt(4);
        arrayList.clear();
        for(int iCnt = 0; iCnt < 4; iCnt++){
            if(iCnt == locationOfCorrectAns){
                arrayList.add(num1+num2);
            }else{
                int wrongAns = rand.nextInt(41);
                while(wrongAns == num1+num2){
                    wrongAns = rand.nextInt(41);
                }
                arrayList.add(wrongAns);
            }

        }

        button0.setText(Integer.toString(arrayList.get(0)));
        button1.setText(Integer.toString(arrayList.get(1)));
        button2.setText(Integer.toString(arrayList.get(2)));
        button3.setText(Integer.toString(arrayList.get(3)));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);

        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);

        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView= findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
