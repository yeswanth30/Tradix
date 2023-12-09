package com.tradix;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tradix.Models.SigninModel;
import com.tradix.dbhelper.dbhelper;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup extends AppCompatActivity {
    ImageView signup, login23;
    TextView register12;
    EditText text17, text27;
    private AppBarConfiguration mAppBarConfiguration;
    dbhelper dbhelper;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        register12 = findViewById(R.id.register12);
        text17 = findViewById(R.id.text17);
        text27 = findViewById(R.id.text27);
        login23 = findViewById(R.id.login23);
        dbhelper = new dbhelper(signup.this);

        login23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                long times = System.currentTimeMillis();
//                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
//                String formats = dates.format(new Date(times));
//
//                dbhelper.register(
//                        text17.getText().toString(),
//                        text27.getText().toString(),
//                        formats);
//                Intent intent339 = new Intent(signup.this, login.class);
//                startActivity(intent339);
//                Toast.makeText(signup.this,"Succesfully Registered",Toast.LENGTH_LONG).show();


                // Create request data
                SigninModel sign = new SigninModel();
                sign.setEmail(text17.getText().toString());
                sign.setPassword(text27.getText().toString());

                Call<SigninModel> call = RetrofitClient.getInstance().getMyApi().signup(sign);
                call.enqueue(new Callback<SigninModel>() {
                    @Override
                    public void onResponse(Call<SigninModel> call, Response<SigninModel> response) {
                        if (response.isSuccessful()) {
                            SigninModel data = response.body();
                            Log.e("signup response", data.toString());
                        } else {
                            // Handle other response codes
                        }
                    }

                    @Override
                    public void onFailure(Call<SigninModel> call, Throwable t) {
                        Toast.makeText(signup.this, "An error has occured" + t, Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });

        register12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent39 = new Intent(signup.this, login.class);
                startActivity(intent39);
            }
        });
    }
}