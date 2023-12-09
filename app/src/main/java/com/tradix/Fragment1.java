package com.tradix;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tradix.Adapter.TradixAdapter;
import com.tradix.Models.Data;
import com.tradix.Models.TradixModel;
import com.tradix.dbhelper.dbhelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment {
    private RecyclerView recyclerView;
    private dbhelper dbhelper;
    private Button myButton22;
    private TextView button2, button3, button4;
    private TradixAdapter adapter;
    private ArrayList<Data> alldata = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        myButton22 = view.findViewById(R.id.myButton22);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);

        myButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent213 = new Intent(getContext(), inserttradix.class);
                startActivity(intent213);
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2134 = new Intent(getContext(), future.class);
                startActivity(intent2134);
            }

        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2135 = new Intent(getContext(), currencies.class);
                startActivity(intent2135);
            }

        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2136 = new Intent(getContext(), future.class);
                startActivity(intent2136);
            }

        });
        dbhelper = new dbhelper(requireContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userid", -1);
        Log.e("UserID", "User IDse: " + userId);


        if (userId != -1) {
            Call<TradixModel> call = RetrofitClient.getInstance().getMyApi().getStockData();
            call.enqueue(new Callback<TradixModel>() {
                @Override
                public void onResponse(Call<TradixModel> call, Response<TradixModel> response) {
                    TradixModel myheroList = response.body();
                    if (response.isSuccessful()) {
                        Log.e("dataaa", new Gson().toJson(response.body()));
                        assert myheroList != null;
                        alldata = myheroList.getData();

                        adapter = new TradixAdapter(alldata);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<TradixModel> call, Throwable t) {
                    Log.e("error", t.toString());
                    Toast.makeText(getContext(), "An error has occurred" + t, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "User ID not found", Toast.LENGTH_SHORT).show();
        }
    }
}
