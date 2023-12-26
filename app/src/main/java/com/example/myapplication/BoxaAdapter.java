package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BoxaAdapter extends RecyclerView.Adapter<BoxaAdapter.ViewHolder>{
    Context context;
    ArrayList<contentModel> arr;
    ArrayList<Uri> Aimg;

    SelectItem Itemclick;

   // contentModel pos;
  // String posi;

    BoxaAdapter(Context context, ArrayList<contentModel> arr,SelectItem Item){
        this.context=context;
        this.arr=arr;
        this.Itemclick=Item;
        this.Aimg=Aimg;
     //   Toast.makeText(MainActivity.class,Item,Toast.LENGTH_LONG).show();
    }

   // MainActivity2 m2=new MainActivity2(this);


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                                                                              // inflat meanse fill karna
        View v= LayoutInflater.from(context).inflate(R.layout.box, parent, false);//yaha par ham box layout se attach
        ViewHolder viewHolder=new ViewHolder(v);                                             //honge, yaha se ViewHolder class ka object ko return kar denge
        return viewHolder;                                                                   //ViewHolder class niche banai hai jisme hamne view ko as a referance diya hai
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       final String posi=String.valueOf(position);
       final contentModel pos=arr.get(position);
      //  arr.set(position,position).position;
        holder.count.setText(pos.count);           // yaha par arraylist ke perticuler index(position) se data nikalke
        holder.img.setImageURI(Uri.parse(pos.imga));// ViewHilder class ke object ke variable me set kar denge
        holder.name.setText(pos.name);      // position ke value apne aap gnrat hogi;
        holder.num.setText(pos.num);       // ? jab holder object value set karta hai to doosre type ka object uoose value ko access kar sakta hai
        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Itemclick.listener(pos,posi);
              // MainActivity2 m2=new MainActivity2(pos,1);
              //  tans.add(pos);
               Toast.makeText(context,pos.count,Toast.LENGTH_LONG).show();
                return true;
            }
        });
        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Itemclick.click1(posi,arr);
                holder.num.setText(arr.get(Integer.parseInt(posi)).num);
              //  Toast.makeText(context,pos.num,Toast.LENGTH_LONG).show();
               // holder.img.setImageURI(pos.img);
            }
        });
    }
    @Override
    public int getItemCount() {   // yaha par hame recycle view ke size ko batate hai
        return arr.size();
    }

  //  @Override
    //public ArrayList<contectModel2> add() {
      //  return pos.arry;
   // }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView num, count ,name;
        CardView llrow;
// yaha per view ko hold karte hai box.xml se matlab view attach hoga
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.show_img);
            num=itemView.findViewById(R.id.ttno);
            count=itemView.findViewById(R.id.no);
            name=itemView.findViewById(R.id.nam);
            llrow=itemView.findViewById(R.id.llrow);

        }
    }

}
