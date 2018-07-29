package com.example.mayank.shoocal;

import android.content.Context;
import android.util.Log;

import com.example.mayank.shoocal.Model.ApiResponse;
import com.example.mayank.shoocal.Model.User;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCall {
    public static String BASE_URL = "http://api.shoocal.com/test/manager/";
    public static Retrofit inititialize(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        //Create a new Interceptor.
        final String authToken = Credentials.basic("admin","1234");
        Interceptor headerAuthorizationInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                Headers headers = request.headers().newBuilder().add("Authorization", authToken).build();
                request = request.newBuilder().headers(headers).build();
                return chain.proceed(request);
            }
        };
        //Add the interceptor to the client builder.
        clientBuilder.addInterceptor(headerAuthorizationInterceptor);

        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static void makeCall(User user, final Context context){
        Retrofit retrofit = inititialize();
        PostApi postApi = retrofit.create(PostApi.class);
        postApi.postUserDetails(user).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
               if(response.isSuccessful()){
                   Log.d("Hello", "post submitted to API." + response.body().toString());
                   ViewModel.changeView(response.body().getMessage().get(0).getStatus(),context);
               }else{
                   Log.d("Hello","Not successful" + response.code() + " " + response.toString());
               }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Hello : ", "onFailure : ");
                ViewModel.changeView(2,context);
            }
        });
    }
}
