package com.example.sec_newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> titlesList = new ArrayList<>();
    ArrayList<String> urlList = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    SQLiteDatabase articleDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleDB = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);
        articleDB.execSQL("CREATE TABLE IF NOT EXISTS articles (id INTEGER PRIMARY KEY, articleId, INTEGER, title VARCHAR, titleUrl VARCHAR)");

        DownloadTask task = new DownloadTask();
        try {
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        }catch (Exception e){
            e.printStackTrace();
        }

        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titlesList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                intent.putExtra("url", urlList.get(position));

                startActivity(intent);
            }
        });

        updateListView();

    }

    public void updateListView(){
        Cursor c = articleDB.rawQuery("SELECT * FROM articles", null);
        int urlIndex = c.getColumnIndex("titleUrl");
        int titleIndex = c.getColumnIndex("title");

        if(c.moveToFirst()){
            titlesList.clear();
            urlList.clear();

            do{
                titlesList.add(c.getString(titleIndex));
                urlList.add(c.getString(urlIndex));
                Log.i("Title Received : ", c.getString(titleIndex));
            }while(c.moveToNext());


            arrayAdapter.notifyDataSetChanged();
        }

    }

    public class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            String result = "";

            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader in = new InputStreamReader(inputStream);

                int data = in.read();
                while(data != -1){
                    char current = (char) data;
                    result += current;  // Get the complete data from the URL provided
                    data = in.read();
                }

                // Put the received data into JSON
                JSONArray jsonArray = new JSONArray(result);
                int numberOfItems = 10;

                if(jsonArray.length() < 10){
                    numberOfItems = jsonArray.length();
                }

                articleDB.execSQL("DELETE FROM articles");
                for(int i = 0; i < numberOfItems; i++){
                    String articleId = jsonArray.getString(i);  // Returns the very first item from the list of received things
                    url = new URL( "https://hacker-news.firebaseio.com/v0/item/"+ articleId + ".json?print=pretty");
                    urlConnection = (HttpURLConnection) url.openConnection();

                    inputStream = urlConnection.getInputStream();
                    in = new InputStreamReader(inputStream);

                    data = in.read();
                    String articleInfo = "";

                    while(data != -1){
                        char current = (char) data;
                        articleInfo += current;  // Get article title
                        data = in.read();
                    }
                    //Log.i("Article Info", articleInfo);

                    JSONObject jsonObject = new JSONObject(articleInfo);
                    if (!jsonObject.isNull("title") && !jsonObject.isNull("url")){
                        String articleTitle = jsonObject.getString("title");
                        String articleUrl = jsonObject.getString("url");
                        Log.i("Title & URL : ", articleTitle + "  " + articleUrl);

                        String  sql = "INSERT INTO articles (articleId, title, titleUrl) VALUES (?, ?, ?)";
                        SQLiteStatement statement = articleDB.compileStatement(sql);
                        statement.bindString(1, articleId);
                        statement.bindString(2, articleTitle);
                        statement.bindString(3, articleUrl);
                        statement.execute();

                        /* If you need to download the URL content
                        url = new URL(articleUrl);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        inputStream = urlConnection.getInputStream();
                        in = new InputStreamReader(inputStream);
                        data = in.read();
                        String articleContent = "";
                        while(data != -1){
                            char current = (char)data;
                            articleContent += current;
                            data = in.read();
                        }
                        Log.i("Article : ", articleContent );
                        */
                    }

                }


                Log.i("URL Content : ", result);
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateListView();
        }
    }
}
