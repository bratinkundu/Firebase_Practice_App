package com.example.bratinkundu.practicefirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button button,button1;
    EditText edittext,edittext1;
    DatabaseReference databaseReference;
    String userr ,pass;
    String username="",password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button3);
        button1 =findViewById(R.id.button4);
        edittext=findViewById(R.id.editText5);
        edittext1=findViewById(R.id.editText6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userr= edittext.getText().toString().trim();
                pass =edittext1.getText().toString().trim();
                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       boolean b =dataSnapshot.child(userr).exists();
                        System.out.println(b);
                        if(b)
                        {
                             username = dataSnapshot.child(userr).child("name").getValue().toString();
                             password = dataSnapshot.child(userr).child("password").getValue().toString();
                             if(password.equals(pass))
                             {
                                 Toast.makeText(getApplicationContext(),"Sucessful Login",Toast.LENGTH_LONG).show();
                                 Intent intent =new Intent(getApplicationContext(),MapsActivity.class);
                                 startActivity(intent);
                             }
                             else
                             {
                                 Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_LONG).show();
                             }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please provide the correct username",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
