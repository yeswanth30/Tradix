package com.tradix;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
public class password extends AppCompatActivity {
    ImageView text239,backgroundImage4;

    String userid;
    TextView register, forget;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        backgroundImage4=findViewById(R.id.backgroundImage4);
        backgroundImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent35 = new Intent(password.this, success.class);
                startActivity(intent35);
            }

        });
    }
}