package com.example.taxproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signin extends AppCompatActivity {
    EditText name,pass;
    Button btn,btn2;
    DatabaseReference db;
    TextView btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        name=(EditText) findViewById(R.id.name);
        pass=(EditText) findViewById(R.id.pass);
        btn=(Button) findViewById(R.id.btn);
        btn2=(Button)  findViewById(R.id.btn2);
        btn3=(TextView) findViewById(R.id.btn3);

        db= FirebaseDatabase.getInstance().getReference().child("USER'S signin");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = name.getText().toString();
                String p = pass.getText().toString();

                db.child(u).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String authpass=task.getResult().child("pass").getValue().toString();
                        if(p.equals(authpass)){
                            Toast.makeText(signin.this,"login successful",Toast.LENGTH_LONG).show() ;

                            String panNO= task.getResult().child("pan").getValue().toString();

                            Intent i=new Intent(signin.this, usermainpg.class);
                          i.putExtra("INFO",panNO);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(signin.this,"username or password incorrect",Toast.LENGTH_LONG).show() ;
                        }
                    }
                });           }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent i=new Intent(signin.this,resetpassword.class);
               // startActivity(i);
            }

        }) ;
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i99=new Intent(signin.this,login.class);
                startActivity(i99);
            }
        }) ;
    }
}