package com.example.mayank.shoocal;

import android.content.Context;

import com.example.mayank.shoocal.Model.ApiResponse;
import com.example.mayank.shoocal.Model.User;

public class ViewModel {
    public static void callPostApi(User user, Context context){
        RetrofitCall.makeCall(user,context);
    }

    public static void changeView(int reqtType,Context context){
        MainActivity.ResponseView(context,reqtType);
    }
}
