package com.tradix;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tradix.Models.AmountModel;
import com.tradix.Models.PriceModel;
import com.tradix.Models.sellstocksmodel;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class currencyexchange extends AppCompatActivity {
    ImageView text239, backgroundImage4, history;
    Button imageView125, imageView2;
    String userid;

    List<PriceModel.DataItem> alldata;
    TextView register, forget, secondTextView;
    Integer tradixid;
    int userId, stocksId, stockPrice;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curencyexchange);
        secondTextView = findViewById(R.id.secondTextView);
        history = findViewById(R.id.history);
        imageView2 = findViewById(R.id.imageView2);

        imageView125 = findViewById(R.id.imageView125);
        Intent intent19 = getIntent();
        if (intent19 != null) {
            Bundle extras = intent19.getExtras();
            tradixid = extras.getInt("tradixid");
            stocksId = tradixid;
            SharedPreferences sharedPreferences = currencyexchange.this.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
            userId = sharedPreferences.getInt("userid", -1);
            Log.e("Debug", "TradixID in currencyexchange: " + tradixid);
        }

        imageView125.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyStock(userId, stocksId, stockPrice, count);
                showSuccessDialog();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                int userId = sharedPreferences.getInt("userid", 0);
                sellstocksmodel sellstocks = new sellstocksmodel();
                sellstocks.setStock_id(stocksId);
                sellstocks.setStock_price(Integer.parseInt(String.valueOf(stockPrice)));
                sellstocks.setUser_id(userId);
                sellstocks.setCount(Integer.parseInt(String.valueOf(1)));

                int totalPrice = stockPrice * count;
                sellstocks.setTotal_price(totalPrice);

                Call<sellstocksmodel> call = RetrofitClient.getInstance().getMyApi().sellstocks(sellstocks);
                call.enqueue(new Callback<sellstocksmodel>() {
                    @Override
                    public void onResponse(Call<sellstocksmodel> call, Response<sellstocksmodel> response) {
                        if (response.isSuccessful()) {
                            sellstocksmodel usersellstocks = response.body();
                          //  Toast.makeText(currencyexchange.this, "Successfully sell stock", Toast.LENGTH_SHORT).show();
                            Log.e("card response", usersellstocks.toString());
                            showSuccessDialog1();  // Call the success dialog method here
                        } else {
                            Log.e("Retrofit Error", "Error Code: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<sellstocksmodel> call, Throwable t) {
                        Log.e("Retrofit Failure", t.getMessage());
                    }
                });
            }
        });

        Call<PriceModel> call = RetrofitClient.getInstance().getMyApi().givestock(tradixid);
        call.enqueue(new Callback<PriceModel>() {
            @Override
            public void onResponse(Call<PriceModel> call, Response<PriceModel> response) {
                PriceModel var = response.body();
                if (response.isSuccessful()) {
                    Log.e("dataa currency", new Gson().toJson(response.body()));
                    PriceModel.DataItem firstItem = var.getData().get(0);
                    stockPrice = firstItem.getPrice();
                    secondTextView.setText(String.valueOf(stockPrice));
                }
            }

            @Override
            public void onFailure(Call<PriceModel> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(currencyexchange.this, "An error has occurred" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(currencyexchange.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.success_layout, null);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // You can optionally add functionality here if the user clicks "OK"
                    }
                })
                .show();
    }

    private void buyStock(int userId, int stocksId, int stockPrice, int count) {
        AmountModel amountModel = new AmountModel();
        amountModel.setId(userId);
        amountModel.setId1(stocksId);
        amountModel.setPrice(stockPrice);
        amountModel.setCount(1);

        int pricee = stockPrice * 1;
        amountModel.setPricee(pricee);

        Log.e("BuyStock", "UserId: " + amountModel.getId() +
                ", StocksId: " + amountModel.getId1() +
                ", StockPrice: " + amountModel.getPrice() +
                ", Count: " + amountModel.getCount() +
                ", TotalPrice: " + amountModel.getPricee());

        Call<AmountModel> call = RetrofitClient.getInstance().getMyApi().buyit(amountModel);
        call.enqueue(new Callback<AmountModel>() {
            @Override
            public void onResponse(Call<AmountModel> call, Response<AmountModel> response) {
                if (response.isSuccessful()) {
                    AmountModel result = response.body();
                    // Toast.makeText(currencyexchange.this, "Successfully bought stock", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(currencyexchange.this, "Failed to buy stock", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AmountModel> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(currencyexchange.this, "An error has occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSuccessDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(currencyexchange.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.stock, null);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // You can optionally add functionality here if the user clicks "OK"
                    }
                })
                .show();
    }
}
