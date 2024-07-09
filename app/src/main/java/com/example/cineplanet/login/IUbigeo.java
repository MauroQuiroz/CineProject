package com.example.cineplanet.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUbigeo {
    @GET("/regions")
    Call<List<Ubigeo>> getAll();
    @GET("/regions")
    Call<List<Ubigeo>> getAll(@Query("status") String status);

    @GET("/regions/{id}")
    Call<Ubigeo> find(@Path("id") int id);

    @POST("/regions")
    Call<Ubigeo> create(@Body Ubigeo contact);

    @PUT("/regions/{id}")
    Call <Ubigeo> update(@Path("id") int id, @Body Ubigeo movie);

    @DELETE("/regions/{id}")
    Call <Void> delete(@Path("id") int id);
    @GET("/regions")
    Call<List<Ubigeo>> getRegionByName(@Query("name") String name);

}
