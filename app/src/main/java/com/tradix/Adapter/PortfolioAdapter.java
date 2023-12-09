package com.tradix.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tradix.Models.Data;
import com.tradix.Models.PortfolioModel;
import com.tradix.R;
import com.tradix.neo;

import java.util.ArrayList;
import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {

    private ArrayList<Data> dataList;
    private Context context;

    public PortfolioAdapter(ArrayList<Data> dataList) {
        this.dataList = dataList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfoliowraper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = dataList.get(position);
      //  Bitmap bitmapImage = BitmapFactory.decodeByteArray(data.getImageResource(), 0, data.getImageResource().length);

        holder.portfolio1.setText(data.getName());
        holder.portfolio2.setText(data.getTime());
      //  holder.portfolio3.setText(data.getLocation());
        holder.portfolio4.setText("Rs. " + String.valueOf((int) data.getPrice()));
        holder.portfolio5.setText("Rs. " + data.getPrice1());
//        holder.portfolio8.setText(data.getHolding2());
//        holder.portfolio7.setText(data.getHolding1());
       // holder.portfolio6.setImageBitmap(bitmapImage);

//        holder.neoo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "data"+ data.getId(),Toast.LENGTH_LONG).show();
//                Intent intent223 = new Intent(v.getContext(), neo.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("tradixid",data.getId());
//                intent223.putExtras(bundle);
//                v.getContext().startActivity(intent223);
//
//
//
//            }
//
//        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView portfolio1, portfolio2, portfolio3, portfolio4,portfolio5,portfolio8;
        ImageView portfolio6;
        Button portfolio7;
        LinearLayout neoo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            portfolio1 = itemView.findViewById(R.id.portfolio1);
            portfolio2 = itemView.findViewById(R.id.portfolio2);
            portfolio3 = itemView.findViewById(R.id.portfolio3);
            portfolio4 = itemView.findViewById(R.id.portfolio4);
            portfolio5 = itemView.findViewById(R.id.portfolio5);
            portfolio8 = itemView.findViewById(R.id.portfolio8);
            portfolio6 = itemView.findViewById(R.id.portfolio6);
            portfolio7 = itemView.findViewById(R.id.portfolio7);
            neoo = itemView.findViewById(R.id.neo);
        }
    }
}

