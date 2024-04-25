package com.example.cineplanet.ui.peliculas.entities;

import com.example.cineplanet.ui.peliculas.services.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPeliculaShow {

    @GET("/movies")
    Call<List<Movies>> getAll();
    @GET("/movies")
    Call<List<Movies>> getAll(@Query("status") String status);

    @GET("/movies/{id}")
    Call<Movies> find(@Path("id") int id);

    @POST("/movies")
    Call<Movies> create(@Body Movies contact);

    @PUT("/movies/{id}")
    Call <Movies> update(@Path("id") int id, @Body Movies movie);

    @DELETE("/movies/{id}")
    Call <Void> delete(@Path("id") int id);
}
