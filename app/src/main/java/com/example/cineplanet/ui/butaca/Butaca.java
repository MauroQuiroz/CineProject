package com.example.cineplanet.ui.butaca;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityButacaBinding;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.ui.cines.CinesFragment;
import com.example.cineplanet.ui.home.MainHomeFragment;
import com.example.cineplanet.ui.peliculas.MainPeliculasFragment;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaDetail;
import com.example.cineplanet.ui.peliculas.services.CinePelicula;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Butaca extends AppCompatActivity implements IClickContinuar,IClickPagar{

    ActivityButacaBinding binding;
    Movie movie;

    CinePelicula cinePelicula;
    private Retrofit retrofit;
    IPeliculaDetail service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityButacaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Asiento.iClickContinuar = this;
        Pago.iClickPagar  =this;
        CanvasButaca.asientosEle  =new ArrayList<>();
        replaceFragment(new Asiento());



        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d2649e7b95ad7fa6c4c14.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        service = retrofit.create(IPeliculaDetail.class);
        Intent intent = getIntent();
        String idM = intent.getStringExtra("idM");
        Log.i("bropoSer",idM);
        String sData2 = intent.getStringExtra("cine");
        cinePelicula = new Gson().fromJson(sData2, CinePelicula.class);

        service.find(Integer.valueOf(idM)).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful()){
                    movie = response.body();
                    binding.title.setText(movie.getName());
                }

            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });








        String ava = "";
        for (String s : cinePelicula.getAvaliable()){
            ava+=s+",";
        }
        binding.descripcionButaca.setText(cinePelicula.getAddress()+" | "+ava);
        binding.iconCloseButaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void initi(){

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransition = fragmentManager.beginTransaction();
        fragmentTransition.replace(R.id.fragment_container_butaca,fragment);
        fragmentTransition.commit();


    }


    @Override
    public void changeFragment() {
        replaceFragment(new Entrada());
    }

    @Override
    public void openPagar() {
        replaceFragment(new Pago());
    }
}