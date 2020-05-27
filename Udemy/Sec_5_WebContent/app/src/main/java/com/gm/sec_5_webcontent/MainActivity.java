package com.gm.sec_5_webcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


class DownloadTask extends AsyncTask<String, Void, String>{

    @Override
    protected String doInBackground(String... strings) {
        Log.i("URL 1", strings[0]);

        String result = null;
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while(data != -1){
                char current = (char)data;
                result += current;
                data = reader.read();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result;
        try {
            result = task.execute("https://www.browxy.com/").get();
            Log.i("URL Result ", result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
