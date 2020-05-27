package com.example.sec_7_parseadvance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ########## Section 1 : ###########
        /* DO This first : To Store in Parse server
        ParseObject score = new ParseObject("Score");
        score.put("username", "Teddy");
        score.put("score", 100);

        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    //OK
                    Log.i("Success", "We saved the Score");
                }else{
                    e.printStackTrace();
                }
            }
        });*/


        // ########## Section 2 : ###########

        /*
        // To retrieve from Parse server

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score"); // Parse Object name
        query.getInBackground("M8ymCZIaQL", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null && object != null){
                    Log.i("Username : ", object.getString("username"));
                    Log.i("Scored : ", String.valueOf(object.getInt("score")));

                    // You can update score here also
                    object.put("score", 85);
                    object.saveInBackground();
                    Log.i("Scored after update : ", String.valueOf(object.getInt("score")));
                }else{
                    e.printStackTrace();
                }
            }
        });
         */

        // ########## Section 3 : ###########
        /*
        // Tweet Ex
        ParseObject tweet = new ParseObject("Tweet");
        tweet.put("username", "learning");
        tweet.put("tweetmsg", "Learning fun !");

        tweet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.i("Success", "Tweet Saved");
                }else{
                    e.printStackTrace();
                }
            }
        });
         */

        // ########## Section 4 : ###########
        //Get the list of objects stored on parse

//        ParseObject score = new ParseObject("Score");
//        score.put("username", "Teddy");
//        score.put("score", 100);
//        score.put("username", "Mady");
//        score.put("score", 100);
//        score.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e != null){
//                    e.printStackTrace();
//                }
//            }
//        });


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score"); // Parse Object name
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if(objects.size() > 0){
                        for(ParseObject object : objects){
                            Log.i("username : ", object.getString("username"));
                            Log.i("score : ", String.valueOf(object.getInt("score")));
                        }
                    }
                }
            }
        });

        // Filter with username = Teddy
        query.whereEqualTo("username", "Teddy");
        query.setLimit(1); // How many username with name Teddy you want
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if(objects.size() > 0){
                        for(ParseObject object : objects){
                            Log.i("username ", " with name filter : " + object.getString("username"));
                            Log.i("score  ", " with name filter : " +String.valueOf(object.getInt("score")));
                        }
                    }
                }
            }
        });

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Score"); // Parse Object name
        query2.whereGreaterThan("score", 90);
        query2.setLimit(1); // How many username with name Teddy you want
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if(objects.size() > 0){
                        for(ParseObject object : objects){
                            Log.i("username ", " with score filter : " + object.getString("username"));
                            Log.i("score ", " with score filter : " +String.valueOf(object.getInt("score")));
                        }
                    }
                }
            }
        });
    }
}
