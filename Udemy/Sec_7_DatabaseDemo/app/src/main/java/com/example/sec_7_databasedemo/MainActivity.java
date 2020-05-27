package com.example.sec_7_databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(4))");
//            myDatabase.execSQL("INSERT INTO users (name, age) VALUES('XYZ', 34)");
//            myDatabase.execSQL("INSERT INTO users (name, age) VALUES('DTZ', 30)");

            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);

            //Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE name = 'Tushar'", null);

            // TO delete and limit one deletion only  then use Primary Key
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(4), id INTEGER PRIMARY KEY)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES('XYZ', 34)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES('DTZ', 30)");
            myDatabase.execSQL("DELETE FROM user WHERE name = 'DTZ'");

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            c.moveToFirst();

            while(!c.isAfterLast()){
                Log.i("Database", "Name : " + c.getString(nameIndex));
                Log.i("Database", "Age : " + Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();
        }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
