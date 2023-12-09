package com.tradix;

import com.tradix.Models.AmountModel;
import com.tradix.Models.DescriptionModel;
import com.tradix.Models.NewsModel;
import com.tradix.Models.PortfolioModel;
import com.tradix.Models.PriceModel;
import com.tradix.Models.SigninModel;
import com.tradix.Models.SoldTransactionModel;
import com.tradix.Models.TradixModel;
import com.tradix.Models.TransactionModel;
import com.tradix.Models.loginModel;
import com.tradix.Models.sellstocksmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "http://192.168.29.108:3000/";

    @POST("signin")
    Call<SigninModel> signup(@Body SigninModel data);

    @POST("login")
    Call<loginModel> login(@Body SigninModel data);

    @GET("getstockdata")
    Call<TradixModel> getStockData();

    @GET("getstockdata")
    Call<PortfolioModel> getStockData1();


//    @GET("givestock")
//    Call<PriceModel>givestock(@Query("id") Integer id);
    @GET("givestock/{id}")
    Call<PriceModel>givestock(@Path("id") Integer id);

    @GET("getnewsdata")
    Call<NewsModel> getnewsdata();

    @GET("getnews/{id}")
    Call<DescriptionModel>getnews(@Path("id") Integer id);

    @POST("buyit")
    Call<AmountModel> buyit(@Body AmountModel request);



//    @POST("storeTransactionHistory")
//    Call<Void> storeTransactionHistory(@Body TransactionModel transaction);



    @GET("getbought/{userId}")
    Call<TransactionModel> getTransactionHistory(@Path("userId") int userId);

    @POST("sellit")
    Call<sellstocksmodel> sellstocks(@Body sellstocksmodel usersellstocks);


    @GET("getsold/{userId}")
    Call<SoldTransactionModel> getSoldStocks(@Path("userId") int userId);

}


