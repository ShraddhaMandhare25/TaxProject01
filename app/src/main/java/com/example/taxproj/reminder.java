package com.example.taxproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class reminder extends AppCompatActivity {
EditText input;
Button send;
TextView t1;
DatabaseReference db;


    private String extractPan(String info) {
        String[] lines = info.split("\n");
        for (String line : lines) {
            if (line.startsWith("PAN=")) {
                return line.substring(4).trim();  // removes "PAN="
            }
        }
        return "";
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        input=(EditText) findViewById(R.id.messageInput);
        send=(Button) findViewById(R.id.sendButton);
        t1=(TextView)findViewById(R.id.textView) ;

        String info = getIntent().getStringExtra("INFO");
        String userPan = extractPan(info);
//String txt= getIntent().getExtras().getString("INFO").toString();
//t1.setText(txt);
        db= FirebaseDatabase.getInstance().getReference().child("USER'S signin");

      /*  db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name="USER="+snapshot.child("name").getValue().toString();
                String panNo="\nPAN="+snapshot.child("pan").getValue().toString() ;
                String pass="PASSWORD="+snapshot.child("pass").getValue().toString();

                //String ans= getIntent().getExtras().getString("INFO").toString();
                //  t1.setText(ans);

                loginD obj = new loginD( panNo, pass,name,inp);
                db.child(inp).setValue(obj);

                Toast.makeText(reminder.this, "message has been sent!!!"+ inp, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inp= input.getText().toString();
                db.orderByChild("pan").equalTo(userPan)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                boolean found = false;
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                    userSnapshot.getRef().child("reminder").setValue(inp);
                                    found = true;
                                }

                                if (found) {
                                    t1.setText("Reminder sent to PAN: " + userPan);
                                    Toast.makeText(reminder.this, "Reminder sent!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(reminder.this, "User not found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(reminder.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}