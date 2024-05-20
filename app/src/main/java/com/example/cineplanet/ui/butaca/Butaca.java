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

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityButacaBinding;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.ui.peliculas.services.CinePelicula;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.gson.Gson;

public class Butaca extends AppCompatActivity {

    ActivityButacaBinding binding;
    Movie movie;

    CinePelicula cinePelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityButacaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String sData = intent.getStringExtra("movie");
        String sData2 = intent.getStringExtra("cine");


        movie = new Gson().fromJson(sData, Movie.class);
        cinePelicula = new Gson().fromJson(sData2, CinePelicula.class);

        binding.title.setText(movie.getName());

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

}