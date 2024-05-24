package com.example.cineplanet.ui.cines;

import com.example.cineplanet.ui.cines.domain.CineDomain;
import com.example.cineplanet.ui.cines.domain.CiudadesDomain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICiudad {
    @GET("/cities")
    Call<List<CiudadesDomain>> getAll();
    @GET("/cities")
    Call<List<CiudadesDomain>> getAll(@Query("status") String status);

    @GET("/cities/{id}")
    Call<CiudadesDomain> find(@Path("id") int id);

    @POST("/cities")
    Call<CiudadesDomain> create(@Body CiudadesDomain contact);

    @PUT("/cities/{id}")
    Call <CiudadesDomain> update(@Path("id") int id, @Body CiudadesDomain movie);

    @DELETE("/cities/{id}")
    Call <Void> delete(@Path("id") int id);
    @GET("/cities")
    Call<List<CiudadesDomain>> getCinesByIds(@Query("ids") String ids);
}
