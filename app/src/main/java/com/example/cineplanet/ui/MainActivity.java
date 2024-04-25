package com.example.cineplanet.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransitionImpl;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.ui.home.MainHomeFragment;
import com.example.cineplanet.ui.peliculas.MainPeliculasFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

            return true;
        });

    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransition = fragmentManager.beginTransaction();
        fragmentTransition.replace(R.id.fragment_container_view,fragment);
        fragmentTransition.commit();


    }

}