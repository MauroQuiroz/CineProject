package com.example.cineplanet.search;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivitySearchBinding;
import com.example.cineplanet.search.adapters.BuscadorMainAdapter;
import com.example.cineplanet.ui.peliculas.adapters.PeliculasAdapter;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaDetail;
import com.example.cineplanet.ui.peliculas.services.Movie;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscadorMain extends AppCompatActivity implements SearchView.OnQueryTextListener  {

    private Retrofit retrofit;
    IPeliculaDetail service;
    List<Movie> movies = new ArrayList<>();
    ActivitySearchBinding binding;
    SearchView buscador;


    BuscadorMainAdapter adapter ;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d2649e7b95ad7fa6c4c14.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        service = retrofit.create(IPeliculaDetail.class);

        buscador= findViewById(R.id.buscador);
        buscador.setOnQueryTextListener(this);

        //Color Buscador
        ImageView searchIcon =  buscador.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setColorFilter(Color.WHITE);


        //Regresar
        binding.backSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        service.getAll().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.isSuccessful()){
                    movies = response.body();
                    cargarLista();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
    }
    void cargarLista(){
        recyclerView = binding.RVBuscador;
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter  = new BuscadorMainAdapter(movies);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String c) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String cara) {
        String c = Normalizer.normalize(cara, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        Log.i("jhampoo",c);
        if(c.isEmpty()){
            adapter.getResultados().clear();
            adapter.notifyDataSetChanged();
            binding.noResultados.setVisibility(View.VISIBLE);
            binding.badResultados.setVisibility(View.GONE);
            return false;
        }
        adapter.getResultados().clear();
        binding.noResultados.setVisibility(View.GONE);
        binding.badResultados.setVisibility(View.GONE);
        for (Movie movie : adapter.getItems()){
            String nameMovieNormalizado = Normalizer.normalize(movie.getName(), Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            if(nameMovieNormalizado.toLowerCase().contains(c.toLowerCase())){
                adapter.getResultados().add(movie);
            }
        }
        if(adapter.getResultados().isEmpty()&&c.length()>0){
            binding.badResultados.setVisibility(View.VISIBLE);
            binding.textSearchBad.setText(c);
        }
        adapter.notifyDataSetChanged();
        return false;
    }



}
