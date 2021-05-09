package com.example.whatsappchatsystem.Fragments;

import androidx.annotation.ContentView;

import com.example.whatsappchatsystem.Notifications.MyResponse;
import com.example.whatsappchatsystem.Notifications.Sender;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService
{
    @Headers({
            "Content-Type: application/json",
            "Authorization:key=AAAAeMr-2yU:APA91bH1uPeo-cHWHZBiF_JW27Njw1DobgEKyRMy5Jl3IiitnrefyrhpQ6WS6aQtboGgBYUqP9mmgLw0Eph6oS3uyqzSijWlLvZUD5K32ESDRtX-pnJFE53PW83RRJFZSXZUJ2vW4H8M"
    })

    @POST("fcm/send")
     Call< MyResponse> sendNotification(@Body Sender body);


}
