package com.example.taxproj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list extends AppCompatActivity {
     ListView listView;
    ArrayList myArrayList = new ArrayList();
    //String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
DatabaseReference db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpg);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        db = FirebaseDatabase.getInstance().getReference().child("USER'S signin");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(list.this, android.R.layout.simple_list_item_1, myArrayList);

       // ArrayAdapter<String> adapter = new ArrayAdapter<>(list.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myArrayList.clear() ;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    // String u="User="+snapshot1.child("user").getValue().toString()+"MOB="+snapshot1.child("mob").getValue().toString() ;
                    String u="USER="+snapshot1.child("name").getValue().toString()+"\nPAN="+snapshot1.child("pan").getValue().toString() ;;

                    myArrayList.add(u.toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Set up click listeners for the buttons
        /*listView.setOnItemClickListener((parent, view, position, id) -> {
            // Get references to the buttons
            Button gstButton = view.findViewById(R.id.gstReminderButton);
            Button incomeTaxButton = view.findViewById(R.id.incomeTaxButton);

            // Set click listeners for GST button
            gstButton.setOnClickListener(v -> {
                String selectedItem = items[position];
                Toast.makeText(list.this,
                        "GST Reminder clicked for: " + selectedItem,
                        Toast.LENGTH_SHORT).show();

                // You can add navigation to GST reminder screen here
                // Intent intent = new Intent(MainActivity.this, GSTReminderActivity.class);
                // startActivity(intent);
            });

            // Set click listeners for Income Tax button
            incomeTaxButton.setOnClickListener(v -> {
                String selectedItem = items[position];
                Toast.makeText(list.this,
                        "Income Tax clicked for: " + selectedItem,
                        Toast.LENGTH_SHORT).show();

                // You can add navigation to Income Tax screen here
                // Intent intent = new Intent(MainActivity.this, IncomeTaxActivity.class);
                // startActivity(intent);
            });
        });*/
    }
}