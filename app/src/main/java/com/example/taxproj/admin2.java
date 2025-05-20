package com.example.taxproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class admin2 extends AppCompatActivity {
TextView t1;
Button gst,income;
DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);
    t1= (TextView) findViewById(R.id.inputField);
    gst=(Button) findViewById(R.id.gstReminderButton);
    income=(Button) findViewById(R.id.incomeTaxButton);

    String ans= getIntent().getExtras().getString("INFO").toString();
    t1.setText(ans);


    gst.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(admin2.this, reminder.class);
            startActivity(i);
        }
    });


    }

}