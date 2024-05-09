package com.example.cineplanet.ui.peliculas.entities;

import com.example.cineplanet.ui.peliculas.services.CinePelicula;
import com.example.cineplanet.ui.peliculas.services.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICinePelicula {
    @GET("/cinemas")
    Call<List<CinePelicula>> getAll();
    @GET("/cinemas")
    Call<List<CinePelicula>> getAll(@Query("status") String status);

    @GET("/cinemas/{id}")
    Call<CinePelicula> find(@Path("id") int id);

    @POST("/cinemas")
    Call<CinePelicula> create(@Body CinePelicula contact);

    @PUT("/cinemas/{id}")
    Call <CinePelicula> update(@Path("id") int id, @Body CinePelicula movie);

    @DELETE("/cinemas/{id}")
    Call <Void> delete(@Path("id") int id);
    @GET("/cinemas")
    Call<List<CinePelicula>> getCinesByIds(@Query("ids") String ids);
}
