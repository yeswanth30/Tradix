package com.tradix.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tradix.Models.Data;
import com.tradix.Models.TradixModel;
import com.tradix.R;
import com.tradix.neo;

import java.util.ArrayList;
import java.util.List;

public class TradixAdapter extends RecyclerView.Adapter<TradixAdapter.ViewHolder> {

    private ArrayList<Data> dataList;
    private Context context;

    public TradixAdapter(ArrayList<Data> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tradixwraper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.nameTextView.setText(data.getName());
        holder.timeTextView.setText(data.getTime());
//        holder.locationTextView.setText(data.getLocation());
        holder.priceTextView.setText("Rs. " + String.valueOf((int) data.getPrice()));
       holder.price1TextView.setText("Rs. " + data.getPrice1());

        holder.tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent22345 = new Intent(v.getContext(), neo.class);
                Bundle bundle = new Bundle();
                bundle.putInt("tradixid",data.getId());
                intent22345.putExtras(bundle);
                v.getContext().startActivity(intent22345);
                Toast.makeText(v.getContext(), "data"+ data.getId(),Toast.LENGTH_LONG).show();

            }

        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, timeTextView, locationTextView, price1TextView;
        Button priceTextView;
        TableLayout tab;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewSingle);
            timeTextView = itemView.findViewById(R.id.textViewHorizontal1);
            locationTextView = itemView.findViewById(R.id.textViewHorizontal2);
            priceTextView = itemView.findViewById(R.id.textViewVertical1);
            price1TextView = itemView.findViewById(R.id.textViewVertical2);
            tab=itemView.findViewById(R.id.tab);
        }
    }
}



