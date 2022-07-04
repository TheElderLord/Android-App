package com.example.finalproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface URLapi {
    @GET("api/characters")
    Call<List<Characters>> getCharacters();

    @GET("api/characters/{char_id}")
    Call<List<Characters>> getCharacterById(@Path("char_id") Integer char_id);
}
