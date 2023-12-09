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
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tradix.Adapter.PortfolioAdapter;
import com.tradix.Adapter.TradixAdapter;
import com.tradix.Models.Data;
import com.tradix.Models.PortfolioModel;
import com.tradix.Models.TradixModel;
import com.tradix.dbhelper.dbhelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment {
    Button myButton90;
    LinearLayout neo;
    private RecyclerView recyclerView;
    dbhelper dbhelper;
    Button myButton22;
    private PortfolioAdapter adapter;
    ArrayList<Data> alldata = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout2, container, false);
        myButton90 = view.findViewById(R.id.myButton90);
        neo = view.findViewById(R.id.neo);

//        neo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent223 = new Intent(getContext(), neo.class);
//                startActivity(intent223);
//            }
//
//        });
        dbhelper = new dbhelper(requireContext());
        recyclerView = view.findViewById(R.id.recyclerView12);
        setUpRecyclerView();
        return view;

    }

    private void setUpRecyclerView() {

//
//        dbhelper dbHelper = new dbhelper(getContext());
//        SharedPreferences sharedpreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
//        String userid = sharedpreferences.getString("userid", "");

        //api data start
        //api name getStockData

        Call<PortfolioModel> call = RetrofitClient.getInstance().getMyApi().getStockData1();
        call.enqueue(new Callback<PortfolioModel>() {
            @Override
            public void onResponse(Call<PortfolioModel> call, Response<PortfolioModel> response) {
                PortfolioModel myheroList = response.body();
                if (response.isSuccessful()) {
                    Log.e("dataaa", new Gson().toJson(response.body()));
                    assert myheroList != null;
                    alldata = myheroList.getData();

                    adapter = new PortfolioAdapter(alldata);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                }

//                    String[] oneHeroes = new String[myheroList.size()];
//                    Log.e("Tag data", "Response " + response.body());
//                    for (int i = 0; i < myheroList.size(); i++) {
//                        oneHeroes[i] = myheroList.get(i).getName();
//                        Log.e("Tag data for", "Response " + oneHeroes[i]);
//                    }
//                    adapter = new TradixAdapter(myheroList); // Replace with your adapter class
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                    recyclerView.setAdapter(adapter);
//                    layout1.setAdapter(new TradixAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<PortfolioModel> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(getContext(), "An error has occured" + t, Toast.LENGTH_LONG).show();
            }

        });
    }
}

//api data end
