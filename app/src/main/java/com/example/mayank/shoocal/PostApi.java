package com.example.mayank.shoocal;

import com.example.mayank.shoocal.Model.ApiResponse;
import com.example.mayank.shoocal.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostApi {
    @POST("democalltesting")
    Call<ApiResponse> postUserDetails(@Body User user);
}
