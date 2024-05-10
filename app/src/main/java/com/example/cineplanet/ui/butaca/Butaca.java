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
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.gson.Gson;

public class Butaca extends AppCompatActivity {

    ActivityButacaBinding binding;
    Movie movie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityButacaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String sData = intent.getStringExtra("movie");



        movie = new Gson().fromJson(sData, Movie.class);

        binding.title.setText(movie.getName());

        binding.iconCloseButaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}