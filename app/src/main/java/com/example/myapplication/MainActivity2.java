package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity{
    ArrayList<contectModel2> arr;
   // RecyclerView recyclerView=findViewById(R.id.recyclerView);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
  //  arrData tans;
    String posi;

    @Override
    public void onBackPressed() {
        // Create an Intent to pass data back to the previous activity
        Intent intent = new Intent();
        intent.putExtra("Data", arr);
        intent.putExtra("posi",posi);// Replace "key" and "value" with your data

        // Set the result and finish the current activity
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
     //  ArrayList<contectModel2> arr=new ArrayList<>();
       // arr=tans.add();
       // arr=new ArrayList<>();

        Intent intent=getIntent();
        arr=intent.getParcelableArrayListExtra("data");
        posi=intent.getStringExtra("posi");
      //  Toast.makeText(this,arr.get(0).count,Toast.LENGTH_LONG).show();

      //  Intent inext=new Intent();



        //    Bundle bundle=getIntent().getExtras();



       // arr.add(new contectModel2("13","8:aug:023","7:20 AM","7","1150"));
        tableAdapter T=new tableAdapter(this,arr);
        recyclerView.setAdapter(T);


    }

}