package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{


    String data1_2[], data2_2[];
    int images2[];
    Context context2;

    public MyAdapter2(Context ct2, String s1_2[], String s2_2[], int img2[]){
        context2 = ct2;
        data1_2 = s1_2;
        data2_2 = s2_2;

        images2 = img2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent2, int viewType2) {
        LayoutInflater inflater = LayoutInflater.from(context2);
        View view = inflater.inflate(R.layout.my_row2, parent2, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder2, final int position2) {
        holder2.textView1_2.setText(data1_2[position2]);
        holder2.textView2_2.setText(data2_2[position2]);

        holder2.imageView2.setImageResource(images2[position2]);

        holder2.mainLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context2, Emergency2.class);
                i.putExtra("title2",data1_2[position2]);
                i.putExtra("description_1_2",data2_2[position2]);

                i.putExtra("images2",images2[position2]);
                context2.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images2.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView1_2, textView2_2;
        ImageView imageView2;
        ConstraintLayout mainLayout2;

        public MyViewHolder(@NonNull View itemView2) {
            super(itemView2);
            textView1_2 = itemView2.findViewById(R.id.textView2);
            textView2_2 = itemView2.findViewById(R.id.textView2_2_2);

            imageView2 = itemView2.findViewById(R.id.imageView2);
            mainLayout2 = itemView2.findViewById(R.id.mainLayout2);
        }
    }
}
