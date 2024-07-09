package com.example.cineplanet.ui.peliculas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cineplanet.databinding.FragmentPeliculasComprarBinding;
import com.example.cineplanet.ui.peliculas.adapters.CineAdapterPelicula;
import com.example.cineplanet.ui.peliculas.entities.ICinePelicula;
import com.example.cineplanet.ui.peliculas.services.CinePelicula;
import com.example.cineplanet.ui.peliculas.services.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeliculasComprarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeliculasComprarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentPeliculasComprarBinding bilding;
    private Retrofit retrofit;

    ICinePelicula service;

    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    Movie  movie;
    List<CinePelicula> cinePeliculas  =new ArrayList<>();


    public PeliculasComprarFragment(Movie movie) {
        // Required empty public constructor
        this.movie = movie;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PeliculasComprarFragment.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onResume() {
        super.onResume();
        createList();

        for (int i = 0; i<movie.getIdsCinemas().size(); i++){
            service.find(Integer.valueOf(movie.getIdsCinemas().get(i))).enqueue(new Callback<CinePelicula>() {
                @Override
                public void onResponse(Call<CinePelicula> call, Response<CinePelicula> response) {
                    if(response.isSuccessful()){
                        cinePeliculas.add(response.body());
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<CinePelicula> call, Throwable t) {

                }
            });
        }


    }
    void createList(){
        recyclerView = bilding.RVPeliculasDetalles;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        adapter  = new CineAdapterPelicula(cinePeliculas,movie);

        recyclerView.setAdapter(adapter);
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
        bilding  =  FragmentPeliculasComprarBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return bilding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d25e3e7b95ad7fa6c4a63.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        service = retrofit.create(ICinePelicula.class);
    }
}