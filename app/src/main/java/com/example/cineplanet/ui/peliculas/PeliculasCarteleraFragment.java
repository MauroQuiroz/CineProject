package com.example.cineplanet.ui.peliculas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.databinding.FragmentMainPeliculasBinding;
import com.example.cineplanet.databinding.FragmentPeliculasCarteleraBinding;
import com.example.cineplanet.ui.peliculas.adapters.PeliculasAdapter;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaShow;
import com.example.cineplanet.ui.peliculas.services.Movies;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeliculasCarteleraFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PeliculasCarteleraFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentPeliculasCarteleraBinding bilding;
    private Retrofit retrofit;
    IPeliculaShow service;
    List<Movies> movies;

    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    public static PeliculasCarteleraFragment newInstance(String param1, String param2) {
        PeliculasCarteleraFragment fragment = new PeliculasCarteleraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PeliculasCarteleraFragment() {
        // Required empty public constructor
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

        bilding  =  FragmentPeliculasCarteleraBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return bilding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
       service.getAll("cartelera").enqueue(new Callback<List<Movies>>() {
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
}