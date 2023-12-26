package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements SelectItem {
    private static final int REQUEST_CODE_SYSTEM_ALERT_WINDOW = 2;
    private WindowManager windowManager;
    private View floatingWidgetView;
    ArrayList<contentModel> arra=new ArrayList<>();
    ArrayList<contectModel2> arrr=new ArrayList<>();
    ArrayList<Uri> Aimg=new ArrayList<>();
   // ArrayList<contectModel2> ar2=new ArrayList<>();
   // HashMap<Integer,ArrayList<contectModel2>> hash=new HashMap<>();
    RecyclerView recycl;


    int requestCode = 2;
    String num;
    String count;
    String name;
    Uri img;
    int sum=0;

    int no=0;
    String time;
    String date;
    String rate;
    Date currentDate = new Date();
   // int count2=0;
   ImageView img0;
   ImageView img1;
   Toolbar tolbar;
   FirebaseAuth auth;
   FirebaseUser corront;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.optmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid=item.getItemId();

        if(itemid==R.id.out){
           auth.signOut();
           Intent inext=new Intent(this,LoginPage.class);

           startActivity(inext);

           finish();
        } else if (itemid==R.id.help) {
            Toast.makeText(this,"Help",Toast.LENGTH_LONG).show();
        } else if (itemid==R.id.info) {
            Intent inext=new Intent(MainActivity.this,Notification.class);
            startActivity(inext);
        } else if (itemid==R.id.search) {
            Toast.makeText(this,"Search",Toast.LENGTH_LONG).show();
        } else if (itemid==R.id.sat) {
            Toast.makeText(this,"Satting",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //  @SuppressLint("MissingInflatedId")
   // @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ownbtn = findViewById(R.id.ownbtn);
        auth=FirebaseAuth.getInstance();
        corront= auth.getCurrentUser();
       recycl = findViewById(R.id.recycle);
     //  img0=findViewById(R.id.imgaaa0);
     //  img1=findViewById(R.id.imgaaa1);
     //   @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        tolbar=findViewById(R.id.toolbar);
        setSupportActionBar(tolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("FastStore");

        }



        recycl.setLayoutManager(new LinearLayoutManager(this));

        //ArrayList<contentModel> arr = new ArrayList<>();


        ownbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNext = new Intent(MainActivity.this, Newadd.class);
                startActivityForResult(iNext, requestCode);
            }
        });



        GlobleVar gv = new GlobleVar();

      /*  if (gv.flag == true) {
           // Intent fromAct = getIntent();
          //  int i = R.drawable.ic_launcher_foreground;                                                            // fromAct.getIntExtra("image", 0);
          /*  String num = "12";
            String count = fromAct.getStringExtra("count");
            String name = fromAct.getStringExtra("name");
            Uri img=(Uri)fromAct.getParcelableExtra("image");

            if(img!=null && num!=null && count!=null && name!=null) {
                arr.add(new contentModel(img, num, count, name));
                Toast.makeText(this, name, Toast.LENGTH_LONG).show();


                   BoxaAdapter adapter = new BoxaAdapter(this, arr);
                // recycl.setAdapter(adapter);
                gv.flag = false;
            }
       }
        BoxaAdapter adapter = new BoxaAdapter(this, arr);
        recycl.setAdapter(adapter);
*/
    //    RecyclerView recycl = findViewById(R.id.recycle);
     //   BoxaAdapter adapter = new BoxaAdapter(this, arr);
       //
        // recycl.setAdapter(adapter);

        //---------------------------------------------------------------------------------------------------------------

        floatingWidgetView = LayoutInflater.from(this).inflate(R.layout.activity_floting_w, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            // If the permission is not granted, ask the user to grant it
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_CODE_SYSTEM_ALERT_WINDOW);
        }

        // Set up the layout parameters for the floating widget
        WindowManager.LayoutParams params;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
        } else {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
        }

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 100;

        // Initialize the WindowManager
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        // Add the view to the WindowManager
        windowManager.addView(floatingWidgetView, params);

        // Set up click listeners or other interactions for the floating widget

        // Example: Launch an activity when the widget is clicked
        floatingWidgetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        //-----------------------------------------------------------------------------------------------------------------------------
    }


    int position=0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SYSTEM_ALERT_WINDOW) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                // Permission granted, you can now add your overlay view
            } else {
                // Permission denied, handle it accordingly
            }
        }
        if(requestCode==7049 && resultCode == Activity.RESULT_OK){
            if(data.getExtras() != null){
                arrr=data.getParcelableArrayListExtra("Data");
                int posi=Integer.parseInt(data.getStringExtra("posi"));
                int no=arrr.size();
                if(arrr!=null&&arra.get(posi).imga!=null) {
                    contentModel c=new contentModel(arra.get(posi).imga,String.valueOf(no),arra.get(posi).count,arra.get(posi).name, arrr);
                    arra.set(posi,c);
                }
            }
        }
        if (requestCode == requestCode && resultCode == RESULT_OK) {
            if (data.getExtras() != null) {
                //   num = data.getStringExtra("num");
                count = data.getStringExtra("count");
                name = data.getStringExtra("name");
                img = (Uri) data.getParcelableExtra("image");

                num=String.valueOf(no);

                if (img != null && count != null && name != null) {
                   // contectModel2 c2=new  contectModel2("sd","s","s","d","d");
                    //ar2.add(c2);
                  //  contentModel c1=new contentModel(img.toString(), num, count, name);
                      arra.add(new contentModel(img.toString(), num, count, name));

//                      int drawableResourceId=R.drawable.ic_launcher_background;
//                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + drawableResourceId);
//                    arra.add(new contentModel(uri.toString(), "1", "1", "1"));
//                    drawableResourceId=R.drawable.ic_launcher_foreground;
//                    uri = Uri.parse("android.resource://" + getPackageName() + "/" + drawableResourceId);
//                    arra.add(new contentModel(uri.toString(), "1", "1", "1"));

//                   if(position==0) {
//                      //  contentModel c1=new contentModel(img.toString(), num, count, name);
//                     //   arra.add(c1);
//                        Aimg.add(img);
//
//                    }
//                    else if(position>=1){
//                        Aimg.add(img);
//                        Aimg.set(position-1, img);
//                        contentModel c1=new contentModel(img.toString(), num, count, name);
//                        arra.add(c1);
//
//                    }
//                    position++;
//




                 //  RecyclerView recycl = findViewById(R.id.recycle);
                   // BoxaAdapter adapter = new BoxaAdapter(this, arr);
                    //recycl.setAdapter(adapter);
                    //Toast.makeText(this, name, Toast.LENGTH_LONG).show();


                }

               // RecyclerView recycl = findViewById(R.id.recycle);
            //    if(position>=2){

                BoxaAdapter adapter = new BoxaAdapter(this, arra,this);
                recycl.setAdapter(adapter);
            }
        }
    }

    @Override
    public void listener(contentModel pos,String posi) {
        //int pos=Integer.parseInt(posi);
        Intent intent=new Intent(this,MainActivity2.class);

       // intent.putExtra("image",);
       // intent.putExtra("name",pos.name);
       // intent.putExtra("count",pos.count);
        intent.putExtra("data",pos.arry);
        intent.putExtra("posi",posi);

    //   // tans.add(pos.arry);
    //  Toast.makeText(this,pos.arry.get(Integer.parseInt(pos.num)-1).no,Toast.LENGTH_LONG).show();
       // intent.putExtra("num",pos.num);

        startActivityForResult(intent,7049);
    }

    @Override
    public void click1(String pos,ArrayList<contentModel> arr) {
        // Toast.makeText(this,arr.get(position).num,Toast.LENGTH_LONG).show();
        int position = Integer.parseInt(pos);
        Toast.makeText(this,pos, Toast.LENGTH_LONG).show();
        // contentModel ctyp=new contentModel(arr.get(position).no+1);

        contentModel ctyp = null;
      //  int sum =0;
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
//---------------------------------------------------------------------------------------------------------------------------
    // flotingWidget




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (floatingWidgetView != null) {
            windowManager.removeView(floatingWidgetView);
        }
    }
//--------------------------------------------------------------------------------------------------------------------------
}