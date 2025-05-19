package com.example.taxproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    EditText pan,pass,name;
    Button b1;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pan=(EditText) findViewById(R.id.panCardEditText);
        pass=(EditText) findViewById(R.id.passwordEditText);
        name=(EditText)findViewById(R.id.nameEditText);
        b1=(Button) findViewById(R.id.loginButton);

        db= FirebaseDatabase.getInstance().getReference().child("USER'S signin");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String panNo = pan.getText().toString().toUpperCase();
                String p = pass.getText().toString();
                String n = name.getText().toString();
                String passValidation = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";


                if (isValidPAN(panNo)) {
                    if (p.matches(passValidation)) {

                        loginD obj = new loginD(panNo, p, n);
                        db.child(n).setValue(obj);


                        Toast.makeText(getApplicationContext(), "Valid PAN and Password", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(login.this, mainpg.class);
                        startActivity(i);
                    } else if (p != passValidation) {
                        Toast.makeText(login.this, "Enter correct format of password", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(login.this, "invalid PAN number ", Toast.LENGTH_SHORT).show();
                }

            }


            private boolean isValidPAN(String pan) {
                // return pan != null && pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}");
                return pan != null && pan.toUpperCase().matches("[A-Z]{3}[PCHABGJLFZT][A-Z][0-9]{4}[A-Z]");
            }

        });

    }
}