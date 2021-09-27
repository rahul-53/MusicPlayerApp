package com.example.musicplayerapp;

import java.util.ResourceBundle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search?term=[from search view]")
    Call<ResponseDTO> getSong(@Query("trackName") String trackName);
}
