package com.tradix;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tradix.dbhelper.dbhelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class inserttradix extends AppCompatActivity {
    private EditText editTextholdings1,editTextholdings2,editTextname, time22, editTextAmount,location22,editTextDate;
    private Button btnAddExpense,SelectImage;
    dbhelper dbhelper;
    ImageView imageview1234;
    private Uri selectedImage;
    String imagepath,StringName;

    private final int GALLERY_REQ_CODE = 1000;
    private Bitmap imageTostore;
    String userid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertradix);

        editTextname = findViewById(R.id.editTextname);
        time22 = findViewById(R.id.time);
        editTextAmount = findViewById(R.id.editTextAmount);
        location22 = findViewById(R.id.location);
        editTextDate = findViewById(R.id.editTextDate);
        imageview1234 = findViewById(R.id.imageview1234);
        editTextholdings1 = findViewById(R.id.editTextholdings1);
        editTextholdings2 = findViewById(R.id.editTextholdings2);

        SelectImage = findViewById(R.id.SelectImage1);
        btnAddExpense = findViewById(R.id.btnAddExpense);

        SelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the image gallery
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);

            }
        });

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = editTextname.getText().toString();
                String time = time22.getText().toString();
                double amount = Double.parseDouble(editTextAmount.getText().toString());
                String location = location22.getText().toString();
                String date = editTextDate.getText().toString();
                String holdings1 = editTextholdings1.getText().toString();
                String holdings2 = editTextholdings2.getText().toString();
                dbhelper = new dbhelper(inserttradix.this);
                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                userid = sharedpreferences.getString("userid", "");
                long times = System.currentTimeMillis();
                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formats = dates.format(new Date(times));

                long insertId = dbhelper.insertData(getApplicationContext(), category, time, location, String.valueOf(amount), date, holdings1, holdings2, imageTostore, formats);


                if (insertId != -1) {
                    Toast.makeText(inserttradix.this, "Stock added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(inserttradix.this, "Error adding Stock", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK && requestCode == GALLERY_REQ_CODE) {
                selectedImage = data.getData();
                imageview1234.setImageURI(selectedImage);
                try {
                    imageTostore = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

}
