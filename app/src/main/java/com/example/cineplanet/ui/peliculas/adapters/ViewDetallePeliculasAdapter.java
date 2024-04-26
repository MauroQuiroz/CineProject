package com.example.cineplanet.ui.peliculas.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cineplanet.ui.peliculas.PeliculasCarteleraFragment;
import com.example.cineplanet.ui.peliculas.PeliculasComprarFragment;
import com.example.cineplanet.ui.peliculas.PeliculasDetalleFragment;
import com.example.cineplanet.ui.peliculas.PeliculasEstrenosFragment;
import com.example.cineplanet.ui.peliculas.PeliculasFestivalFragment;

public class ViewDetallePeliculasAdapter extends FragmentStateAdapter {

    public ViewDetallePeliculasAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewDetallePeliculasAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewDetallePeliculasAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new PeliculasDetalleFragment();
        }
        if(position==1){
            return new PeliculasComprarFragment();
        }
        return new PeliculasDetalleFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
