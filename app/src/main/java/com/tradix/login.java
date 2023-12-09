package com.tradix;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tradix.Models.SigninModel;
import com.tradix.Models.loginModel;
import com.tradix.dbhelper.dbhelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    ImageView text239, login76;
    EditText text91, text02;
    TextView register, forget;
    Integer userid;
    Integer id;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        register = findViewById(R.id.register);
        text91 = findViewById(R.id.text91);
        text02 = findViewById(R.id.text02);
        forget = findViewById(R.id.forget);
        login76 = findViewById(R.id.login76);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(login.this, signup.class);
                startActivity(intent3);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent32 = new Intent(login.this, password.class);
                startActivity(intent32);
            }
        });

        login76.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = text91.getText().toString();
                String password = text02.getText().toString();

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(login.this, "Please Enter Values", Toast.LENGTH_LONG).show();
                } else {
                    SigninModel signinData = new SigninModel();
                    signinData.setEmail(name);
                    signinData.setPassword(password);

                    Call<loginModel> call = apiService.login(signinData);
                    call.enqueue(new Callback<loginModel>() {
                        @Override
                        public void onResponse(Call<loginModel> call, Response<loginModel> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                loginModel signinResponse = response.body();

                                List<loginModel.DataItem1> dataList = signinResponse.getData();
                                for (loginModel.DataItem1 dataItem : dataList) {
                                    id = dataItem.getId();
                                }

                                if (id != null) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                                    SharedPreferences.Editor myedit = sharedPreferences.edit();
                                    myedit.putInt("userid", id.intValue());
                                    Log.e("UserID", "User ID: " + id);

                                    myedit.apply();

                                    Toast.makeText(login.this, "Successfully login", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(login.this, MainActivity.class);
                                    startActivity(intent);
                                } else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                                    builder.setTitle("Login Failed")
                                            .setMessage("Invalid credentials. Please check your email and password.")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // You can optionally add functionality here if the user clicks "OK"
                                                }
                                            })
                                            .show();
                                }
                            } else {
                                // Handle the case where the response is not successful
                                Toast.makeText(login.this, "Login failed. Please check your credentials.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<loginModel> call, Throwable t) {
                            // Handle network errors or request failure
                            Toast.makeText(login.this, "Login failed. Please try again later.", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
