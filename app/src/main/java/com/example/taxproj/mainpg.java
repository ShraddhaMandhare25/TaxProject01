package com.example.taxproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainpg extends AppCompatActivity {
Button gst,income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpg);
        gst=(Button) findViewById(R.id.buttonGST);
        income=(Button) findViewById(R.id.buttonIncome);

        gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mainpg.this, list.class);
                startActivity(i);
            }
        });

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mainpg.this, list.class);
                startActivity(i);
            }
        });
    }
}