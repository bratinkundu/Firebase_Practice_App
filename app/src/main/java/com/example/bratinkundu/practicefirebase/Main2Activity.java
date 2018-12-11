package com.example.bratinkundu.practicefirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    EditText edittext,edittext1,edittext2,edittext3;
    Button button;
    DatabaseReference databaseReference;
    String username,password,confpassword,name,myuser;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edittext =findViewById(R.id.editText1);
        edittext1 = findViewById(R.id.editText2);
        edittext2= findViewById(R.id.editText4);
        edittext3=findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button2);



        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    username=edittext.getText().toString();
                    password=edittext1.getText().toString();
                    confpassword=edittext2.getText().toString();
                    name=edittext3.getText().toString();
                    System.out.println(name+" "+username );
                    boolean valid = validate(password,confpassword);
                    if(valid)
                    {
                        checkdata(username);
                    }
                }
            });

        }


    boolean validate(String pass,String cpass)
    {
        if(pass.equals(cpass))
        {
            return true;
        }
        else
        {
            Toast.makeText(this,"Password and Confirm Password must be same",Toast.LENGTH_LONG).show();
            return false;
        }
    }
    void checkdata(String username1)
    {
         myuser = username1;
        databaseReference =FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(myuser).exists())
                {
                    Toast.makeText(getApplicationContext(),"User already exists",Toast.LENGTH_LONG).show();
                }
                else
                {
                    addData(name,username,password);
                    Toast.makeText(getApplicationContext(),"Sucess",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void addData(String name,String username,String password)
    {
        databaseReference =FirebaseDatabase.getInstance().getReference();
        User user =new User(name,password);
        databaseReference.child("Users").child(username).setValue(user);
    }
}
