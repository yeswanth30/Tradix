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
public class skip2 extends AppCompatActivity {
    ImageView text239,text48;
    TextView yourTextViewId545;

    String userid;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skip2);
        text239 = findViewById(R.id.text239);
        text48 = findViewById(R.id.text48);

        yourTextViewId545=findViewById(R.id.yourTextViewId545);
        text239.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(skip2.this, skip.class);
                startActivity(intent3);
            }

        });
        text48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(skip2.this, login.class);
                startActivity(intent3);
            }

        });
    }
}