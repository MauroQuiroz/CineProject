package com.example.cineplanet.ui.peliculas.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cineplanet.ui.peliculas.PeliculasCarteleraFragment;
import com.example.cineplanet.ui.peliculas.PeliculasEstrenosFragment;
import com.example.cineplanet.ui.peliculas.PeliculasFestivalFragment;

public class ViewPeliculasAdpater extends FragmentStateAdapter {


    public ViewPeliculasAdpater(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPeliculasAdpater(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPeliculasAdpater(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if(position==0){
            return new PeliculasCarteleraFragment();
        }
        if(position==1){
            return new PeliculasEstrenosFragment();
        }
        if(position==2){
            return new PeliculasFestivalFragment();
        }

        return new PeliculasCarteleraFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }



}
