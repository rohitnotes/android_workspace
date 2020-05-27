package com.gm.sec_5_celeberityguess;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button1, button2, button3, button4;
    ArrayList<String> celebUrl = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int choosenCeleb = 0, locationOfAns = 0;
    String[] answers = new String[4];

    public void CelebSelected(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfAns))){
            Toast.makeText(this, "Correct Ans !", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Wrong ! It was" + celebNames.get(choosenCeleb), Toast.LENGTH_SHORT).show();
        }
        NewQuestion();
    }

    public void NewQuestion(){
        Random rand = new Random();
        choosenCeleb = rand.nextInt(celebUrl.size());

        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap celebImg = null;
        try {
            celebImg = imageDownloader.execute(celebUrl.get(choosenCeleb)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(celebImg);

        locationOfAns = rand.nextInt(4);
        int incorrectAns;

        for(int i = 0; i < 4; i++){
            if(i == locationOfAns){
                answers[i] = celebNames.get(choosenCeleb);
            }else{
                incorrectAns = rand.nextInt(celebUrl.size());
                while(incorrectAns == choosenCeleb) {
                    incorrectAns = rand.nextInt(celebUrl.size());
                }

                answers[i] = celebNames.get(incorrectAns);
            }
        }

        button1.setText(answers[0]);
        button2.setText(answers[1]);
        button3.setText(answers[2]);
        button4.setText(answers[3]);
    }

    class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream in = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return  bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = "";

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return  result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("http://www.posh24.se/kandisar").get();
            Log.i("Celebrity Content : ", result);

            String[] splitResult = result.split("<div class=\"listedArticles\">");

            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);

            while(m.find()){
                Log.i("Celebrity Photo ", m.group(1));
                celebUrl.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while(m.find()){
                Log.i("Celebrity Name ", m.group(1));
                celebNames.add(m.group(1));
            }

            NewQuestion();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
