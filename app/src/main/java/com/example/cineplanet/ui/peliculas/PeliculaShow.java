package com.example.cineplanet.ui.peliculas;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.databinding.ActivityPeliculaShowBinding;
import com.example.cineplanet.databinding.FragmentPeliculasCarteleraBinding;
import com.google.android.material.tabs.TabLayout;

public class PeliculaShow extends AppCompatActivity {

    ActivityPeliculaShowBinding binding;
    int idPelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityPeliculaShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int idPelicula = getIntent().getIntExtra("idPelicula",1);
        binding.tabPeliculas.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.fragmentContainerPeliculas.setCurrentItem(tab.getPosition());
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