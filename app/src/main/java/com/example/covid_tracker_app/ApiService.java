package com.example.covid_tracker_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("v1/us/{postId}")
    Call<List<ResponseModel>> getPost(@Path("postId") String postId);
}
