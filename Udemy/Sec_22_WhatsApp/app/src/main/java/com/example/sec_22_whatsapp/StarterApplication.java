package com.example.sec_22_whatsapp;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class StarterApplication extends Application {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("myappID")
                .clientKey("MzA9Y8AtmAm3")
                .server("http://18.223.23.152/parse")
                .build()
        );

        // Test Code
//        ParseObject object = new ParseObject("ExampleObject");
//        object.put("myNumber", "707");
//        object.put("myString", "Sandy");
//
//        object.saveInBackground(new com.parse.SaveCallback() {
//            @Override
//            public void done(com.parse.ParseException ex) {
//                if (ex == null) {
//                    Log.i("Parse Result", "Successful!");
//                } else {
//                    Log.i("Parse Result", "Failed" + ex.toString());
//                }
//            }
//        });


        // ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
