package com.example.taxproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class usergst extends AppCompatActivity {

    TextView txt;
    Button ok;
    DatabaseReference db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usergst);
        txt=(TextView) findViewById(R.id.messageInput);
        ok=(Button) findViewById(R.id.okayButton);



        db= FirebaseDatabase.getInstance().getReference("USER'S signin");

        String panNo=getIntent().getExtras().getString("INFO").toString();

 db.orderByChild("pan").equalTo(panNo).addListenerForSingleValueEvent(new ValueEventListener() {
     @Override
     public void onDataChange(@NonNull DataSnapshot snapshot) {

         boolean found = false;
         for (DataSnapshot userSnap : snapshot.getChildren()) {
             String reminder = userSnap.child("reminder").getValue(String.class);

             if (reminder != null && !reminder.isEmpty()) {
                 txt.setText(reminder);
             } else {
                 txt.setText("\n\n\nNo reminders right now!\nWe’ll notify you as soon as something comes up.");
             }
             found = true;
         }

         if (!found) {
             Toast.makeText(usergst.this, "User not found with given PAN", Toast.LENGTH_SHORT).show();
             txt.setText("No user found.");
         }

     }

     @Override
     public void onCancelled(@NonNull DatabaseError error) {

     }
 });
ok.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(usergst.this,usermainpg.class);
        startActivity(i);
    }
});


    }
}