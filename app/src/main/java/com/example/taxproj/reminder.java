package com.example.taxproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class reminder extends AppCompatActivity {
EditText input;
Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        input=(EditText) findViewById(R.id.messageInput);
        send=(Button) findViewById(R.id.sendButton);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(reminder.this, "message has been sent!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}