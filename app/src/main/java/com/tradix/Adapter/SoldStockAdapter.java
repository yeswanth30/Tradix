package com.tradix.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tradix.Models.SoldTransactionModel;
import com.tradix.R;

import java.util.ArrayList;

public class SoldStockAdapter extends RecyclerView.Adapter<SoldStockAdapter.ViewHolder> {

    private ArrayList<SoldTransactionModel.SoldTransactionData> soldStockList;

    public SoldStockAdapter(ArrayList<SoldTransactionModel.SoldTransactionData> soldStockList) {
        this.soldStockList = soldStockList;
    }

    public interface OnItemClickListener {
        void onItemClick(String stockName, int stockPrice);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sold_stock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SoldTransactionModel.SoldTransactionData soldStock = soldStockList.get(position);
        holder.stockNameTextView.setText("Stock Name: " + soldStock.getStockName());
        holder.soldPriceTextView.setText("Stock Price: " + String.valueOf(soldStock.getSoldPrice()));
    }

    @Override
    public int getItemCount() {
        return soldStockList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stockNameTextView;
        TextView soldPriceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stockNameTextView = itemView.findViewById(R.id.stockNameTextView1);
            soldPriceTextView = itemView.findViewById(R.id.stockPriceTextView1);
        }
    }
}
