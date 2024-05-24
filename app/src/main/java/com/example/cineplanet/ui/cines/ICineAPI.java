package com.example.cineplanet.ui.cines;

import com.example.cineplanet.ui.cines.domain.CineDomain;
import com.example.cineplanet.ui.peliculas.services.CinePelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICineAPI   {
    @GET("/cinemas")
    Call<List<CineDomain>> getAll();
    @GET("/cinemas")
    Call<List<CineDomain>> getAll(@Query("status") String status);

    @GET("/cinemas/{id}")
    Call<CineDomain> find(@Path("id") int id);

    @POST("/cinemas")
    Call<CineDomain> create(@Body CineDomain contact);

    @PUT("/cinemas/{id}")
    Call <CineDomain> update(@Path("id") int id, @Body CineDomain movie);

    @DELETE("/cinemas/{id}")
    Call <Void> delete(@Path("id") int id);
    @GET("/cinemas")
    Call<List<CineDomain>> getCinesByIds(@Query("ids") String ids);
}
