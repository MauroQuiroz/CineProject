package com.example.cineplanet.ui.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.databinding.ActivityPeliculaShowBinding;
import com.example.cineplanet.databinding.FragmentPeliculasCarteleraBinding;
import com.example.cineplanet.domain.PeliculaDetailDomain;
import com.example.cineplanet.domain.PeliculaDomain;
import com.example.cineplanet.ui.peliculas.adapters.ViewDetallePeliculasAdapter;
import com.example.cineplanet.ui.peliculas.adapters.ViewPeliculasAdpater;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaDetail;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaShow;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.example.cineplanet.ui.peliculas.services.Movies;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculaShow extends AppCompatActivity {

    ActivityPeliculaShowBinding binding;
    int idPelicula;

    private Retrofit retrofit;
    IPeliculaDetail service;
    Movie movie;
    ViewDetallePeliculasAdapter viewPeliculasAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityPeliculaShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idPelicula = getIntent().getIntExtra("idPelicula",1);


        init();

    }
    private  void init(){

        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d2649e7b95ad7fa6c4c14.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        service = retrofit.create(IPeliculaDetail.class);

    }
    @Override
    protected void onResume() {
        super.onResume();
        service.find(idPelicula).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.code() == 200) {
                    movie = response.body();
                    MostrarDatos();

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });



        ///

    }
    private void MostrarDatos(){

        binding.peliculaTitle.setText(movie.getName());
        binding.generoHoraEdad.setText(movie.getGender()+" | "+movie.getDuration()+" | "+movie.getAge());

        ///FrAGMNET
        viewPeliculasAdpater = new  ViewDetallePeliculasAdapter(this,movie);
        binding.fragmentContainerPeliculasDetail.setAdapter(viewPeliculasAdpater);

        //

        binding.tabPeliculas.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String sData = new Gson().toJson(movie);
                binding.fragmentContainerPeliculasDetail.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}