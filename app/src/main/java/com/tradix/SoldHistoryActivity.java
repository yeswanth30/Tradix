package com.tradix;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tradix.Adapter.HistoryAdapter;
import com.tradix.Adapter.SoldStockAdapter;
import com.tradix.Models.SoldTransactionModel;
import com.tradix.Models.TransactionModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoldHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SoldStockAdapter soldStockAdapter;
    int userId;
    private ArrayList<SoldTransactionModel.SoldTransactionData> alldata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerViewSoldStocks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userid", -1);

        if (userId != -1) {
            Call<SoldTransactionModel> call = RetrofitClient.getInstance().getMyApi().getSoldStocks(userId);
            call.enqueue(new Callback<SoldTransactionModel>() {
                @Override
                public void onResponse(Call<SoldTransactionModel> call, Response<SoldTransactionModel> response) {
                    if (response.isSuccessful()) {
                        SoldTransactionModel transactionList = response.body();

                        assert transactionList != null;
                        alldata = transactionList.getData();
                        soldStockAdapter = new SoldStockAdapter(alldata);
                        recyclerView.setAdapter(soldStockAdapter);
                    } else {
                        Log.e("History", "Failed to fetch data. Response code: " + response.code());
                        Toast.makeText(SoldHistoryActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SoldTransactionModel> call, Throwable t) {
                    Log.e("History", "An error has occurreds: " + t.getMessage());
                    Toast.makeText(SoldHistoryActivity.this, "An error has occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "User ID not available", Toast.LENGTH_SHORT).show();
        }
    }
}
