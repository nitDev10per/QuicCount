package com.example.myapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tableAdapter extends RecyclerView.Adapter<tableAdapter.Viewholder> {
    Context context;
    ArrayList<contectModel2> arr;


    tableAdapter(Context context, ArrayList<contectModel2> arr){
        this.arr=arr;
        this.context=context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.table,parent,false);
        Viewholder viewholder=new Viewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final int pos=position;
        holder.count.setText(arr.get(position).count);
        holder.no.setText(arr.get(position).no);
        holder.date.setText(arr.get(position).date);
        holder.time.setText(arr.get(position).time);
        holder.rate.setText(arr.get(position).rate);
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.right.setVisibility(View.VISIBLE);
                holder.wrong.setVisibility(View.VISIBLE);
                contectModel2 c=arr.get(pos);
                c.setOk(false);
                arr.set(pos,c);
                return true;
            }
        });
        if(arr.get(pos).ok==true){
            holder.right.setVisibility(View.GONE);
            holder.wrong.setVisibility(View.GONE);
        }
        holder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.right.setVisibility(View.GONE);
                holder.wrong.setVisibility(View.GONE);
                contectModel2 c=arr.get(pos);
                c.setOk(true);
                arr.set(pos,c);

            }
        });
        holder.wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr.remove(pos);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (arr != null) {
            return arr.size();
        } else {
            // Handle the case where arr is null, e.g., return 0 or another appropriate value.
            return 0;
        }
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView no;
        TextView date;
        TextView time;
        TextView count;
        TextView rate;
        Button right;
        Button wrong;

        CardView card;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.No);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            count=itemView.findViewById(R.id.count);
            rate=itemView.findViewById(R.id.rate);
            right=itemView.findViewById(R.id.button4);
            wrong=itemView.findViewById(R.id.button5);
            card=itemView.findViewById(R.id.card);
        }
    }
}
