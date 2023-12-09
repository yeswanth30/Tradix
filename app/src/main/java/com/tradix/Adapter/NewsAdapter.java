package com.tradix.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tradix.Models.Data;
import com.tradix.Models.NewsModel;
import com.tradix.Models.TradixModel;
import com.tradix.R;
import com.tradix.editorial;
import com.tradix.neo;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<Data> dataList;
    private Context context;

    public NewsAdapter(ArrayList<Data> dataList)  {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newswraper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = dataList.get(position);
        //Bitmap bitmapImage = BitmapFactory.decodeByteArray(data.getImageResource(), 0, data.getImageResource().length);
        holder.nameTextView.setText(data.getName());
        holder.price1TextView.setText(data.getNews());
        holder.locationTextView.setText(data.getDate());
      //  holder.imageview123.setImageBitmap(bitmapImage);
     //   holder.price1TextView.setText(data.getDescription());

        holder.newss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent223465 = new Intent(v.getContext(), editorial.class);
                Bundle bundle = new Bundle();
                bundle.putInt("newsid",data.getId());
                intent223465.putExtras(bundle);
                v.getContext().startActivity(intent223465);
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
        ImageView imageview123;
        TableLayout newss;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView3);
           // timeTextView = itemView.findViewById(R.id.textView1);
            locationTextView = itemView.findViewById(R.id.textView2);
            imageview123 = itemView.findViewById(R.id.photoImageView);
            price1TextView = itemView.findViewById(R.id.textView4);
            newss = itemView.findViewById(R.id.newss);

        }
    }
}

