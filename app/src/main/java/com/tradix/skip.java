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
public class skip extends AppCompatActivity {
    ImageView text23,text49;
    TextView yourTextViewId55;

    String userid;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skip);
       text23 = findViewById(R.id.text23);
        text49=findViewById(R.id.text49);

        yourTextViewId55=findViewById(R.id.yourTextViewId55);
        text23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent37 = new Intent(skip.this, contentmain.class);
                startActivity(intent37);
            }

        });
        text49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent35 = new Intent(skip.this, skip2.class);
                startActivity(intent35);
            }

        });
    }
}