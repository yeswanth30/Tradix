package com.tradix;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tradix.Adapter.NewsAdapter;
import com.tradix.Adapter.TradixAdapter;
import com.tradix.Models.Data;
import com.tradix.Models.NewsModel;
import com.tradix.Models.TradixModel;
import com.tradix.dbhelper.dbhelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment3 extends Fragment {
ImageView backgroundImage2;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
Button button11;
    dbhelper dbhelper;
    ArrayList<Data> alldata = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout3, container, false);
        backgroundImage2 = view.findViewById(R.id.backgroundImage2);
        button11 = view.findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent213 = new Intent(getContext(), insertnews.class);
                startActivity(intent213);
            }

        });

//        backgroundImage2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent223 = new Intent(getContext(), editorial.class);
//                startActivity(intent223);
//            }
//
//        });
        dbhelper = new dbhelper(requireContext());
        recyclerView = view.findViewById(R.id.recyclerView1);
        setUpRecyclerView();
        return view;
    }
    private void setUpRecyclerView() {


//        dbhelper dbHelper = new dbhelper(getContext());
//        SharedPreferences sharedpreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
//        String userid = sharedpreferences.getString("userid", "");

        //api data start
        //api name getStockData

        Call<NewsModel> call = RetrofitClient.getInstance().getMyApi().getnewsdata();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel myheroList = response.body();
                if(response.isSuccessful()){
                    Log.e("dataaa",new Gson().toJson(response.body()));
                    assert myheroList != null;
                    //sucessArraylist = myheroList.getData();
                    alldata = myheroList.getData();

                    // Log.e("alldata",name);
                    adapter = new NewsAdapter(alldata); // Replace with your adapter class
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.e("error",t.toString());
                Toast.makeText(getContext(), "An error has occured"+t, Toast.LENGTH_LONG).show();
            }

        });
    }


    //api data end
    }
