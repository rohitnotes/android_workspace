package com.example.sec_22_whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    String activeUser = "";

    ArrayList<String> messages = new ArrayList<>();
    ArrayAdapter adapter;

    public void sendButton(View view){
        final TextView chatEditText = findViewById(R.id.chatEditText);
        final String messageContent =  chatEditText.getText().toString();

        ParseObject message = new ParseObject("Message");
        message.put("sender", ParseUser.getCurrentUser().getUsername());
        message.put("recipient", activeUser);
        message.put("msg", messageContent);
        chatEditText.setText("");
        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(ChatActivity.this, "Message Sent..", Toast.LENGTH_SHORT).show();
                    messages.add(messageContent);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        activeUser = intent.getStringExtra("username");

        setTitle("Chat with "+ activeUser);

        final ListView chatListView = findViewById(R.id.chatListView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, messages);
        chatListView.setAdapter(adapter);

        ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("Message");

        query1.whereEqualTo("sender", ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo("recipient", activeUser);

        ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("Message");
        query1.whereEqualTo("recipient", ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo("sender", activeUser);

        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        queries.add(query1);
        queries.add(query2);

        ParseQuery<ParseObject> query = ParseQuery.or(queries);

        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if(objects.size() > 0){
                        messages.clear();
                        for(ParseObject message : objects){
                            String messageContent = message.getString("msg");
                            if(!message.getString("sender").equals(ParseUser.getCurrentUser().getUsername())){
                                messageContent = "> "+messageContent;
                                chatListView.item
                            }

                            messages.add(messageContent);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }
}
