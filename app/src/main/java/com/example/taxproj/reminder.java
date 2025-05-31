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
    EditText messageInput, phoneInput;
    Button send;
    TextView statusText;
    DatabaseReference db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        messageInput = findViewById(R.id.messageInput);
        phoneInput = findViewById(R.id.editTextPhone);
        send = findViewById(R.id.sendButton);
        statusText = findViewById(R.id.textView);

        db = FirebaseDatabase.getInstance().getReference().child("USER'S signin");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageInput.getText().toString().trim();
                String phoneNumber = phoneInput.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(reminder.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (message.isEmpty()) {
                    Toast.makeText(reminder.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Query Firebase users by phone number
                db.orderByChild("mob").equalTo(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean found = false;

                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            String userToken = userSnapshot.child("token").getValue(String.class);

                            // Save reminder message to user's node
                            userSnapshot.getRef().child("reminder").setValue(message);

                            if (userToken != null) {
                                FCMHelper.sendNotification(userToken, "New Reminder", message);
                            }

                            found = true;
                        }

                        if (found) {
                            statusText.setText("Reminder sent to phone: " + phoneNumber);
                            Toast.makeText(reminder.this, "Reminder sent!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(reminder.this, "User with this phone number not found", Toast.LENGTH_SHORT).show();
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
