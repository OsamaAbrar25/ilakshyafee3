package com.example.ilakshyafee3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeeApi {
    @GET("profile")
    Call<List<FeeInfo>> getData();
}
