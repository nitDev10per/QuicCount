package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;

public class Singin extends AppCompatActivity {

    Button sing;
    TextView login;
    TextInputLayout Email;
    TextInputLayout Password;
    TextInputLayout Name;
    TextInputLayout Number;
    FirebaseAuth auth;
   FirebaseDatabase databade;


  //  @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        FirebaseApp.initializeApp(this);
        sing=findViewById(R.id.button2);
        login=findViewById(R.id.textView12);
        Email=findViewById(R.id.textInputLayout3);
        EditText email=Email.getEditText();
        Password=findViewById(R.id.textInputLayout5);
        EditText password=Password.getEditText();
        Name=findViewById(R.id.textInputLayout6);
        EditText name=Name.getEditText();
        Number=findViewById(R.id.textInputLayout4);
        EditText number=Number.getEditText();
        databade=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();


        sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                            auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        User user=new User(name.getText().toString(),number.getText().toString(),email.getText().toString(),password.getText().toString());
                                        String id=task.getResult().getUser().getUid();
                                        databade.getReference().child("Users").child(id).setValue(user);
                                        Toast.makeText(Singin.this,"user data save",Toast.LENGTH_LONG).show();
                                        Intent inext=new Intent(Singin.this,MainActivity.class);
                                        startActivity(inext);
                                        finish();

                                    }else{
                                        //  Log.e("FirebaseError", task.getException().getMessage());
                                        Toast.makeText(Singin.this, task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    User user=new User(name.getText().toString(),number.getText().toString(),email.getText().toString(),password.getText().toString());
                                    String id=task.getResult().getUser().getUid();
                                    databade.getReference().child("Users").child(id).setValue(user);
                                    Toast.makeText(Singin.this,"user data save",Toast.LENGTH_LONG).show();
                                    Intent inext=new Intent(Singin.this,MainActivity.class);
                                    startActivity(inext);
                                    finish();

                                }else{
                                  //  Log.e("FirebaseError", task.getException().getMessage());
                                    Toast.makeText(Singin.this, task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inext=new Intent(Singin.this,LoginPage.class);
                startActivity(inext);
                finish();
            }
        });

    }
}