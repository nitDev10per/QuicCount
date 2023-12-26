package com.example.myapplication;

import android.net.Uri;

import java.util.ArrayList;

public class contentModel {
   String count;

    String num;
    String imga;
   String name;
   ArrayList<contectModel2> arry=new ArrayList<>();
 //  int no=0;
  // int position;
    contentModel(String img, String num,String count,String name){
        this.count=count;
        this.imga=img;
        this.name=name;
        this.num=num;
    }
    contentModel(String img,String num,String count,String name,ArrayList<contectModel2> arry){
        this.imga=img;
        this.num=num;
        this.count=count;
        this.name=name;
        this.arry=arry;
    }



}
