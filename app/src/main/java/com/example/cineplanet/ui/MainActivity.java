package com.example.cineplanet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.login.LoginActivity;
import com.example.cineplanet.login.RegistroActivity;
import com.example.cineplanet.search.BuscadorMain;
import com.example.cineplanet.ui.cines.CinesFragment;
import com.example.cineplanet.ui.home.MainHomeFragment;
import com.example.cineplanet.ui.peliculas.MainPeliculasFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bilding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bilding  = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(bilding.getRoot());
        replaceFragment(new MainHomeFragment());
        bilding.btnNavegation.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.homeM){
                replaceFragment(new MainHomeFragment());
            }
            if(item.getItemId()==R.id.moviesM){
                replaceFragment(new MainPeliculasFragment());
            }
            if(item.getItemId()==R.id.cinemaM){
                replaceFragment(new CinesFragment());
            }
            return true;
        });
        bilding.buscadorSearchMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscadorMain.class);

                startActivity(intent);
            }
        });
        bilding.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransition = fragmentManager.beginTransaction();
        fragmentTransition.replace(R.id.fragment_container_view,fragment);
        fragmentTransition.commit();


    }

}