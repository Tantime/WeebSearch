package com.example.apiproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeebService {

    String BASE_URL = "https://api.jikan.moe/v3/";

    @GET("/anime/{id}/pictures")
    Call<Weeb> getRandomImageById(@Path("id") String title);
}
