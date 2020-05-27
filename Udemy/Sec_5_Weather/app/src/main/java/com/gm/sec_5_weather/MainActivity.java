package com.gm.sec_5_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;



    public void GetWeather(View view){

        if(!editText.equals("")) {
            DownloadTask task = new DownloadTask();
            String result = "";
            try {
                String encodedCityName  = URLEncoder.encode(editText.getText().toString(), "UTF-8");
                result = task.execute("https://openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=439d4b804bc8187953eb36d2a8c26a02").get();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            resultTextView.setText("Enter City Name ");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.resultTextView);

    }

    class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";

            URL url = null;
            HttpURLConnection httpURLConnection;

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while (data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return  result;

            } catch (Exception e) {
                e.printStackTrace();
                resultTextView.setText("Enter City Name ");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");

                JSONArray arr = new JSONArray(weatherInfo);

                String msg = "";
                for(int i=0; i < arr.length(); i++){
                    JSONObject jsonPart = arr.getJSONObject(i);
                    String main = jsonPart.getString("main");
                    String desc = jsonPart.getString("description");

                    if(!main.equals("") && !desc.equals("")){
                        msg += main + " : " + desc;
                    }
                }

                if(!msg.equals("")){
                    resultTextView.setText(msg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
