package com.example.myapplication;

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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class LoginPage extends AppCompatActivity {

    Button login;
    TextView registor;
    FirebaseAuth aath;
    TextInputLayout T;
    TextInputLayout P;
   static FirebaseUser curront;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //FirebaseApp.initializeApp(this);
        T=findViewById(R.id.textInputLayout);
        EditText email=T.getEditText();
        P=findViewById(R.id.textInputLayout2);
        EditText password=P.getEditText();
         login=findViewById(R.id.button);
         registor=findViewById(R.id.textView9);
         aath=FirebaseAuth.getInstance();
         curront=aath.getCurrentUser();


         login.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 aath.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()) {
                             Intent inext=new Intent(LoginPage.this,MainActivity.class);
                             startActivity(inext);

                             finish();


                         }else{
                             Toast.makeText(LoginPage.this, task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                         }
                     }
                 });

             }

         });
         registor.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 Intent inext = new Intent(LoginPage.this, Singin.class);
                 //    inext.putExtra("context", (CharSequence) LoginPage.this);
                 startActivity(inext);
                 finish();


             }
         });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(curront!=null){
            Intent inext=new Intent(LoginPage.this,MainActivity.class);
            startActivity(inext);

            finish();
        }
    }
}