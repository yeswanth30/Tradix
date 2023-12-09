package com.tradix.Adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tradix.Models.TransactionModel;
import com.tradix.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<TransactionModel.TransactionData> transactionList;

    public HistoryAdapter(ArrayList<TransactionModel.TransactionData> transactionList) {
        this.transactionList = transactionList;
    }
    public interface OnItemClickListener {
        void onItemClick(String stockName, int stockPrice);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionModel.TransactionData transaction = transactionList.get(position);

        holder.stockNameTextView.setText("Stock Name: " +  transaction.getStockName());
        holder.stockPriceTextView.setText("Stock Price: " +  String.valueOf(transaction.getStockPrice()));

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stockNameTextView;
        TextView stockPriceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stockNameTextView = itemView.findViewById(R.id.stockNameTextView);
            stockPriceTextView = itemView.findViewById(R.id.stockPriceTextView);

        }
    }
}
