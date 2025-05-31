package com.example.taxproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class usermainpg extends AppCompatActivity {
Button gst1,inc1;
DatabaseReference db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermainpg);
        gst1=(Button) findViewById(R.id.btnGST);
        inc1=(Button) findViewById(R.id.btnIncome);

        db= FirebaseDatabase.getInstance().getReference().child("USER'S signin");

gst1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String pan = getIntent().getExtras().getString("INFO").toString();
        Intent i=new Intent(usermainpg.this, usergst.class);
        i.putExtra("INFO",pan);
        startActivity(i);
    }
});

inc1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String pan = getIntent().getExtras().getString("INFO").toString();
        Intent i2=new Intent(usermainpg.this, userincome.class);
        i2.putExtra("INFO",pan);
        startActivity(i2);
    }
});
    }
}