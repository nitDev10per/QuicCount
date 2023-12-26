package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class flotingW extends AppCompatActivity implements SelectItem {
    ArrayList<contentModel> arra=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floting_w);
        RecyclerView recycle;
        recycle=findViewById(R.id.Recycl);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        arra.add(new contentModel(null,"n","v","fm"));
        arra.add(new contentModel(null,"n","v","fm"));
        arra.add(new contentModel(null,"n","v","fm"));
        arra.add(new contentModel(null,"n","v","fm"));


        BoxaAdapter adapter = new BoxaAdapter(this, arra,this);
        recycle.setAdapter(adapter);
    }

    @Override
    public void listener(contentModel pos, String posi) {

    }

    @Override
    public void click1(String pos, ArrayList<contentModel> arr) {
        // Toast.makeText(this,arr.get(position).num,Toast.LENGTH_LONG).show();
        int position = Integer.parseInt(pos);
        Toast.makeText(this,pos, Toast.LENGTH_LONG).show();
        // contentModel ctyp=new contentModel(arr.get(position).no+1);

        contentModel ctyp = null;
          int sum =0;
        try {
            int numValue = Integer.parseInt(arr.get(position).num);
            int countValue = Integer.parseInt(arr.get(position).count);
            sum = numValue + countValue;
            // ctyp = new contentModel(arr.get(position).img,String.valueOf(sum),arr.get(position).count,arr.get(position).name);
        } catch (NumberFormatException e) {
            // Handle the case where the strings couldn't be parsed as integers
            // e.printStackTrace(); // You can print the exception for debugging
            // Handle the error appropriately, e.g., setting a default value or logging an error message.
        }


        //   num=String.valueOf((Integer.parseInt(arr.get(position).num))+(Integer.parseInt(arr.get(position).count)));
        // arr.set(position, (ctyp));

        //  int no = arr.get(position).no;
        //    String sno = String.valueOf(no);

        // pos.arr.get(1).count+Integer.parseInt(count);
        // String date = currentDate.toString();
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int dat=calendar.get(Calendar.DAY_OF_MONTH);
        String date=String.valueOf(dat);
        //int second = calendar.get(Calendar.SECOND);
        String time = String.valueOf(hour) + ":" + String.valueOf(minute);
        String rate = String.valueOf(1234);

        contectModel2 c2 = new contectModel2(String.valueOf(sum), date, time, arr.get(position).count, rate);
        ArrayList<contectModel2> arr2=new ArrayList<>();
        arr2=arr.get(position).arry;
        arr2.add(c2);

        //arr2.set(0,c2);
        contentModel c3 = new contentModel(arr.get(position).imga,String.valueOf(sum),arr.get(position).count,arr.get(position).name,arr2);
        arr.set(position, c3);
        //      Toast.makeText(this,arr.get(position).arry.get(0).rate,Toast.LENGTH_LONG).show();


    }
}