package com.tradix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class contentmain extends AppCompatActivity {
    ImageView yourImageViewId;
    TextView yourTextViewId5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userid", -1);

        if (userId != -1) {
            Intent intent = new Intent(contentmain.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.content_main);

        yourTextViewId5 = findViewById(R.id.yourTextViewId5);
        yourImageViewId = findViewById(R.id.yourImageViewId);

        yourTextViewId5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(contentmain.this, login.class);
                startActivity(intent3);
            }
        });

        yourImageViewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(contentmain.this, skip.class);
                startActivity(intent3);
            }
        });
    }
}
