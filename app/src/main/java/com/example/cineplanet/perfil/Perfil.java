package com.example.cineplanet.perfil;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.databinding.ActivityPerfilBinding;
import com.example.cineplanet.login.domain.UserDomain;
import com.example.cineplanet.ui.peliculas.adapters.ViewPeliculasAdpater;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;

public class Perfil extends AppCompatActivity {
    ActivityPerfilBinding bilding;
    UserDomain user;
    ViewPeliculasAdpater viewPeliculasAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bilding = ActivityPerfilBinding.inflate(getLayoutInflater());

        String userJson = getIntent().getStringExtra("user");

        // Convertir el JSON string de nuevo a un objeto UserDomain
        Gson gson = new Gson();
        user = gson.fromJson(userJson, UserDomain.class);
        bilding.nameUser.setText(user.getName()+" "+user.getLastNameP());
        setContentView(bilding.getRoot());

        bilding.backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //
        viewPeliculasAdpater = new ViewPeliculasAdpater(this);
        bilding.fragmentContainerPeliculas.setAdapter(viewPeliculasAdpater);

        new TabLayoutMediator(bilding.tabPeliculas, bilding.fragmentContainerPeliculas,(tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Mi perfil");
                    break;
                case 1:
                    tab.setText("Mis datos");
                    break;
            }
        }).attach();
    }
}