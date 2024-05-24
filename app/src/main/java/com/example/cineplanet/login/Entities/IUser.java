package com.example.cineplanet.login.Entities;

import com.example.cineplanet.domain.CineDomain;
import com.example.cineplanet.login.domain.UserDomain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUser {
    @GET("/users")
    Call<List<UserDomain>> getAll();
    @GET("/users")
    Call<List<UserDomain>> getAll(@Query("status") String status);

    @GET("/users/{id}")
    Call<UserDomain> find(@Path("id") int id);

    @POST("/users")
    Call<UserDomain> create(@Body UserDomain contact);

    @PUT("/users/{id}")
    Call <UserDomain> update(@Path("id") int id, @Body UserDomain movie);

    @DELETE("/users/{id}")
    Call <Void> delete(@Path("id") int id);

}
