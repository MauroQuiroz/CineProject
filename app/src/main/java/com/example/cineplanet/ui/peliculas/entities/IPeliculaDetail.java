package com.example.cineplanet.ui.peliculas.entities;

import com.example.cineplanet.ui.peliculas.services.Movie;
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

public interface IPeliculaDetail {
    @GET("/movie-detail")
    Call<List<Movie>> getAll();
    @GET("/movie-detail")
    Call<List<Movie>> getAll(@Query("status") String status);

    @GET("/movie-detail/{id}")
    Call<Movie> find(@Path("id") int id);

    @POST("/movie-detail")
    Call<Movie> create(@Body Movie contact);

    @PUT("/movie-detail/{id}")
    Call <Movie> update(@Path("id") int id, @Body Movie movie);

    @DELETE("/movie-detail/{id}")
    Call <Void> delete(@Path("id") int id);
}
