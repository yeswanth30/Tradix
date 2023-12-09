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

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
public class neo extends AppCompatActivity {
    ImageView text239,backgroundImage4,imageView1;

    Integer tradixid;
    TextView register, forget;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neo);
        imageView1 = findViewById(R.id.imageView1);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                tradixid = extras.getInt("tradixid");

            }
        }


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent329 = new Intent(neo.this, currencyexchange.class);
                Bundle bundle = new Bundle();
                bundle.putInt("tradixid", tradixid);
                intent329.putExtras(bundle);
                startActivity(intent329);

            }
        });
    }
}