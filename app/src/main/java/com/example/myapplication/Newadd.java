package com.example.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Newadd extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    int cameraCode=1;
    ImageView img;
    Uri uri;

 //-----------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newadd);
        Button next,cancle,click;

        next=findViewById(R.id.edtnext);
        EditText name = findViewById(R.id.edt_T);
        click=findViewById(R.id.edtclick);
        cancle=findViewById(R.id.cancl);
       EditText count=findViewById(R.id.edt_count);
        img=findViewById(R.id.edtimg);


        EditText edt=findViewById(R.id.edt_T);

     //   click.setOnClickListener(new View.OnClickListener() {
       //     @Override
         //   public void onClick(View view) {
           //     Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             //   startActivityForResult(icamera,cameraCode);
    //        }
      //  });


// ...

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arePermissionsGranted()) {
                    Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(icamera, cameraCode);
                } else {
                    // Request permissions
                    requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
                }
            }
        });

// ...




        // Intent iNex=new Intent(this,);

      //  GlobleVar gv=new GlobleVar();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNex=new Intent();
                String nam=name.getText().toString().trim();
                String coun=count.getText().toString().trim();

              //  Toast.makeText(Newadd.this,coun,Toast.LENGTH_LONG).show();


                iNex.putExtra("name",nam);
                if(uri!=null){
                 iNex.putExtra("image",uri);
             //   Toast.makeText(Newadd.this,"image capture",Toast.LENGTH_LONG).show();
                    }
                else{
                    Toast.makeText(Newadd.this,"NO image capture",Toast.LENGTH_LONG).show();
                    return;
                }

                iNex.putExtra("count",coun);
              //  gv.flag=true;
                setResult(Activity.RESULT_OK,iNex);
                finish();



            }
        });


    }
//------------------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
                if(requestCode==cameraCode){
                   // uri=data.getData();
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                  //  Log.d("camer","imageUri :"+uri);

                    if(thumbnail!=null) {
                        img.setImageBitmap(thumbnail);
                        uri=getImageUriFromBitmap(thumbnail);
                    }
                }
                }
    }
//------------------------------------------------------------------------------------------------------------------------------------
private Uri getImageUriFromBitmap(Bitmap bitmap) {
    try {
        File cachePath = new File(getCacheDir(), "images");
        cachePath.mkdirs();
        File imageFile = new File(cachePath, "temp_image.jpg");

        FileOutputStream outStream = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();

        return Uri.fromFile(imageFile);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

    //-----------------------------------------------------------------------------------------------------------------------------------
    private boolean arePermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (arePermissionsGranted()) {
                Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(icamera, cameraCode);
            } else {
                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }




}