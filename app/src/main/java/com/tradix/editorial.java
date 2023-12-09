package com.tradix;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.tradix.Models.DescriptionModel;
import com.tradix.Models.PriceModel;
import com.tradix.dbhelper.dbhelper;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editorial extends AppCompatActivity {
    ImageView text239, backgroundImage4;

    Integer newsid;
    TextView textView422, forget;
    List<DescriptionModel.DataItem> alldata;
    private AppBarConfiguration mAppBarConfiguration;

    dbhelper dbhelper;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editorial);
        textView422 = findViewById(R.id.textView422);

        dbhelper = new dbhelper(this);

        Intent intent13 = getIntent();
        if (intent13 != null) {
            Bundle extras = intent13.getExtras();
            if (extras != null) {
                newsid = extras.getInt("newsid");
                Log.d("Debug", " in currencyexchange: " + newsid);

            }}
        //api data start
        //api name getStockData
        Call<DescriptionModel> call = RetrofitClient.getInstance().getMyApi().getnews(newsid);
        call.enqueue(new Callback<DescriptionModel>() {
            @Override
            public void onResponse(Call<DescriptionModel> call, Response<DescriptionModel> response) {
                DescriptionModel var = response.body();
                if (response.isSuccessful()) {
                    Log.e("dataa currency", new Gson().toJson(response.body()));
                    DescriptionModel.DataItem firstItem = var.getData().get(0);
                    String newsText = firstItem.getNews();
                    textView422.setText(newsText);

                }
            }
            @Override
            public void onFailure(Call<DescriptionModel> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(editorial.this, "An error has occured" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //api data end
}


//        Intent intent = getIntent();
//        if (intent != null) {
//            int newsId = intent.getIntExtra("newsId", -1);
//            Log.e("editorial", "Received newsId: " + newsId);
//
//            if (newsId != -1) {
//                String description = String.valueOf(dbhelper.getNewsDescription(String.valueOf(newsId)));
//                textView42.setText(description);
//            } else {
//                Toast.makeText(this, "Invalid newsId received from the Intent", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "Intent is null", Toast.LENGTH_SHORT).show();
//        }
