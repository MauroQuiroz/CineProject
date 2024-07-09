package com.example.cineplanet.ui.peliculas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.FragmentPeliculasCarteleraBinding;
import com.example.cineplanet.databinding.FragmentPeliculasDetalleBinding;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeliculasDetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeliculasDetalleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Movie movie;
    FragmentPeliculasDetalleBinding bilding;

    public PeliculasDetalleFragment(Movie data) {
        movie  = data;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bilding.sinopsiss.setText(movie.getSinopsis());
        bilding.director.setText(movie.getDirector());

        String idiomas = "";
        for (String id : movie.getLanguage()){
            idiomas += id+",";
        }
        bilding.idioma.setText(idiomas);

        String disponible = "";
        for (String dis : movie.getAvaliable()){
            disponible += dis+",";
        }
        bilding.disponible.setText(disponible);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bilding  =  FragmentPeliculasDetalleBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return bilding.getRoot();


    }
}