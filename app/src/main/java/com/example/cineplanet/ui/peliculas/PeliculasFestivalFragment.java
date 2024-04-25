package com.example.cineplanet.ui.peliculas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.FragmentPeliculasEstrenosBinding;
import com.example.cineplanet.databinding.FragmentPeliculasFestivalBinding;
import com.example.cineplanet.ui.peliculas.adapters.PeliculasAdapter;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaShow;
import com.example.cineplanet.ui.peliculas.services.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeliculasFestivalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeliculasFestivalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentPeliculasFestivalBinding bilding;
    private Retrofit retrofit;
    IPeliculaShow service;
    List<Movies> movies;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    public PeliculasFestivalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PeliculasFestivalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PeliculasFestivalFragment newInstance(String param1, String param2) {
        PeliculasFestivalFragment fragment = new PeliculasFestivalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bilding = FragmentPeliculasFestivalBinding.inflate(inflater,container,false);
        return  bilding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ///AWQUIII
        // Instanciar Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d2649e7b95ad7fa6c4c14.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        service = retrofit.create(IPeliculaShow.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        service.getAll("festival").enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                if (response.code() == 200) {
                    movies = response.body();
                    createMovies();
                }
            }
            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {

            }
        });
    }
    public void createMovies(){

        recyclerView = bilding.carteleraRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        adapter  = new PeliculasAdapter(movies);

        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2); // 2 columnas
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


    }
}