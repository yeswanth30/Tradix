package com.tradix.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.tradix.Models.NewsModel;
import com.tradix.Models.PortfolioModel;
import com.tradix.Models.TradixModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class dbhelper extends SQLiteOpenHelper {
    byte[] imageInBytes;

    private static final String DATABASE_NAME = "tradix";
    private static final int DATABASE_VERSION = 11;

    // User table
    private static final String TABLE_USER = "user";
    private static final String USER_ID = "userid";
    private static final String  USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_TIME = "time";

    // Your table and column names
    private static final String TABLE_TRADIX = "tradix";
    private static final String TRADIX_ID = "tradixid";
    private static final String TRADIX_USER_ID = "userid";

    private static final String TRADIX_NAME = "name";

    private static final String TRADIX_LOCATION = "location";
    private static final String TRADIX_PRICE = "price";
    private static final String TRADIX_PRICE1 = "price1";
    private static final String TRADIX_TIME = "time1";
    private static final String TRADIX_HOLDINGS1 = "holdings1";
    private static final String TRADIX_HOLDINGS2 = "holdings2";
    private static final String TRADIX_IMAGE = "image";

    private static final String TRADIX_TIME2 = "time2";


    //news
    private static final String TABLE_NEWS = "news";
    private static final String TABLE_NEWS_ID = "newsid";
    private static final String TABLE_USER_ID = "userid";

    private static final String TABLE_NEWS_NAME = "name";

    private static final String TABLE_NEWS_IMAGE = "image";
    private static final String TABLE_NEWS_DESCRIPTION = "description";
    private static final String TABLE_NEWS_DATE = "date";
    private static final String TABLE_NEWS_PERCENTAGE = "percentage";

    private static final String TABLE_NEWS_TIME = "time3";


    //crete for news
    private static final String CREATE_TABLE_NEWS = "CREATE TABLE " + TABLE_NEWS + " ("
            + TABLE_NEWS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TABLE_USER_ID + " TEXT, "
            + TABLE_NEWS_NAME + " TEXT, "
            + TABLE_NEWS_IMAGE + " TEXT, "
            + TABLE_NEWS_DESCRIPTION + " TEXT, "
            + TABLE_NEWS_DATE + " TEXT, "
            + TABLE_NEWS_PERCENTAGE + " TEXT, "

            + TABLE_NEWS_TIME + " TEXT)";

    // Create table statement
    private static final String CREATE_TABLE_TRADIX = "CREATE TABLE " + TABLE_TRADIX + "("
            + TRADIX_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRADIX_USER_ID + " TEXT, "
            + TRADIX_NAME + " TEXT, "
            + TRADIX_LOCATION + " TEXT, "
            + TRADIX_PRICE + " TEXT, "
            + TRADIX_PRICE1 + " TEXT, "
            + TRADIX_TIME + " TEXT, "
            + TRADIX_HOLDINGS1 + " TEXT, "
            + TRADIX_HOLDINGS2 + " TEXT, "
            + TRADIX_IMAGE + " TEXT, "
            + TRADIX_TIME2 + " TEXT)";


    // Create table statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_EMAIL + " TEXT NOT NULL, "
            + USER_PASSWORD + " TEXT NOT NULL, "
            + USER_TIME + " TEXT NOT NULL)";

    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_TRADIX);
        db.execSQL(CREATE_TABLE_NEWS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed, and recreate them
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRADIX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);


        onCreate(db);
    }
    public void register( String email, String password, String formats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_EMAIL, email);
        values.put(USER_PASSWORD, password);
        values.put(USER_TIME,formats);
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public String login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String userRole = null;
        String sql = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "' ;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            userRole = cursor.getString(cursor.getColumnIndexOrThrow("userid"));
        }
        return userRole;
    }
    public long insertData(Context context, String name, String time, String location, String price, String price1, String holdings1, String holdings2, Bitmap image, String formats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ByteArrayOutputStream objectByteOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,20,objectByteOutputStream);
        imageInBytes = objectByteOutputStream.toByteArray();
        SharedPreferences sharedpreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String userid = sharedpreferences.getString("userid", "");
        values.put(TRADIX_USER_ID, userid);
        values.put(TRADIX_NAME, name);
        values.put(TRADIX_TIME, time);
        values.put(TRADIX_LOCATION, location);
        values.put(TRADIX_PRICE, price);
        values.put(TRADIX_PRICE1, price1);
        values.put(TRADIX_HOLDINGS1, holdings1);
        values.put(TRADIX_HOLDINGS2, holdings2);
        values.put(TRADIX_IMAGE, imageInBytes);
        values.put(TRADIX_TIME2,formats);
        long result = db.insert(TABLE_TRADIX, null, values);
        db.close();
        return result;
    }
    public List<TradixModel> getAllData(String id) {
        String sql = "SELECT * FROM tradix WHERE userid = ?;";

        SQLiteDatabase db = this.getReadableDatabase();
        List<TradixModel> dataList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, new String[]{id});

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(2);
                String time = cursor.getString(6);
                String location = cursor.getString(3);
                double price = Double.parseDouble(cursor.getString(4));
                String price1 = cursor.getString(5);

//                TradixModel data = new TradixModel(name,time,location,price,price1);
//                data.setName(name);
//                data.setTime(time);
//                data.setLocation(location);
//                data.setPrice(Double.parseDouble(String.valueOf(price)));
//                data.setPrice1(price1);

               // dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dataList;
    }
    public long insertnews(String Name, Bitmap image, String Description , String date, String percentage,String userid, String formats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ByteArrayOutputStream objectByteOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,20,objectByteOutputStream);
        imageInBytes = objectByteOutputStream.toByteArray();
        values.put("name", Name);
        values.put("image", imageInBytes);
        values.put("description", Description);
        values.put("date", date);
        values.put("percentage", percentage);
        values.put("userid", userid);
        values.put("time3", formats);
        long newRowId = -1;
        try {
            newRowId = db.insert("news", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return newRowId;
    }
    public List<NewsModel> getNewsByUserId(String userId) {
        List<NewsModel> newsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                TABLE_NEWS_ID,
                TABLE_USER_ID,
                TABLE_NEWS_NAME,
                TABLE_NEWS_IMAGE,
                TABLE_NEWS_DESCRIPTION,
                TABLE_NEWS_DATE,
                TABLE_NEWS_PERCENTAGE,
                TABLE_NEWS_TIME
        };

        String selection = TABLE_USER_ID + "=?";
        String[] selectionArgs = {userId};

        Cursor cursor = db.query(
                TABLE_NEWS,  // The table to query
                projection,  // The columns to return
                selection,   // The columns for the WHERE clause
                selectionArgs,  // The values for the WHERE clause
                null,        // Don't group the rows
                null,        // Don't filter by row groups
                null         // The sort order
        );

        if (cursor.moveToFirst()) {
            do {
                int newsId = cursor.getInt(cursor.getColumnIndexOrThrow(TABLE_NEWS_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NEWS_NAME));
                byte[]  image = cursor.getBlob(cursor.getColumnIndexOrThrow(TABLE_NEWS_IMAGE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NEWS_DESCRIPTION));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NEWS_DATE));
                String percentage = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_NEWS_PERCENTAGE));

         //       NewsModel news = new NewsModel(newsId, name, image, description, date, percentage);
              //  newsList.add(news);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return newsList;
    }
    public List<PortfolioModel> getAllData2(String id) {
        String sql = "SELECT * FROM tradix WHERE userid = ?;";

        SQLiteDatabase db = this.getReadableDatabase();
        List<PortfolioModel> dataList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, new String[]{id});

        if (cursor.moveToFirst()) {
            do {
                int tradixid = cursor.getInt(cursor.getColumnIndexOrThrow(TRADIX_ID));
                String name = cursor.getString(2);
                String time = cursor.getString(6);
                String location = cursor.getString(3);
                double price = Double.parseDouble(cursor.getString(4));
                String price1 = cursor.getString(4);
                String holdings1 = cursor.getString(7);

                String holdings2 = cursor.getString(8);
                byte[]  image = cursor.getBlob(9);


//                PortfolioModel data = new PortfolioModel(tradixid,name,time,location,price,price1,holdings1,holdings2,image);
//                data.setName(name);
//                data.setTime(time);
//                data.setLocation(location);
//                data.setPrice(Double.parseDouble(String.valueOf(price)));
//                data.setPrice1(price1);
//                data.setHolding1(holdings1);
//                data.setHolding2(holdings2);

               // dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dataList;
    }
    public double getAmount(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        double amount = 0;

        if (id != null) {
            String sql = "SELECT price FROM tradix WHERE tradixid = '"+id+"';";
            Cursor cursor = null;

            try {
                cursor = db.rawQuery(sql, null);

                if (cursor.moveToFirst()) {
                    amount = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        return amount;
    }


    public String getNewsDescription(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String amount = "";

        String sql = "SELECT description FROM news WHERE newsid = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        Log.e("user  id-",id);
        if (cursor.moveToFirst()) {
            amount = cursor.getString(cursor.getColumnIndexOrThrow("description"));
        }

        cursor.close();
        return amount;
    }


}

