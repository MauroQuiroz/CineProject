package com.example.cineplanet.ui.peliculas;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.FragmentPeliculasCarteleraBinding;
import com.example.cineplanet.ui.cines.ICiudad;
import com.example.cineplanet.ui.cines.IClickFiltroCiudad;
import com.example.cineplanet.ui.cines.adapter.FiltroCiudadCineAdpater;
import com.example.cineplanet.ui.cines.domain.CineDomain;
import com.example.cineplanet.ui.cines.domain.CiudadesDomain;
import com.example.cineplanet.ui.peliculas.adapters.PeliculasAdapter;
import com.example.cineplanet.ui.peliculas.daos.MovieDAO;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaDetail;
import com.example.cineplanet.ui.peliculas.services.AppDatabase;
import com.example.cineplanet.ui.peliculas.services.DescargarImagenTask;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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
public class PeliculasCarteleraFragment extends Fragment implements IClickFiltroCiudad {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentPeliculasCarteleraBinding bilding;
    private Retrofit retrofit;
    IPeliculaDetail service;
    List<Movie> movies;
    List<Movie> moviesLocal = new ArrayList<>();

    PeliculasAdapter adapter;
    RecyclerView recyclerView;
    String conexion = "si";

    //Database
    private DatabaseReference mDatabase;
    boolean carga = false;
    int size  = 0;
    int sizeIn  = 0;

    private MovieDAO movieDao;

//  Filtro ciudad
    private Retrofit retrofitCiudad;
    ICiudad serviceCiudad;
    FiltroCiudadCineAdpater adapterCiudad;
    RecyclerView recyclerViewCiudad;
    Context context;
    BottomSheetDialog bottomSheetDialog;

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
        context = this.getContext();
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
        if(carga){
            createMovies();
        }
        //  createMovies();
    }
    public void createMovies(){
        recyclerView = bilding.carteleraRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        adapter  = new PeliculasAdapter(movies,conexion);

        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2); // 2 columnas
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
        /*


         */

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ///AWQUIII
        //Instacia firefatabse
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        AppDatabase db = AppDatabase.getInstance(this.getContext());
        movieDao = db.movieDAO();



        // Instanciar Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d2649e7b95ad7fa6c4c14.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        service = retrofit.create(IPeliculaDetail.class);
        ///
        bilding.carteleraBtnFiltroCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Filtro ciudad
                recyclerViewCiudad.setAdapter(adapterCiudad);

                bottomSheetDialog.show();
            }
        });
        service.getAll("cartelera").enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.isSuccessful()){
                    movies = response.body();
                    size  = movies.size();
                    conexion = "si";
                    createMovies();
                    carga = true;



                    for (Movie peli : movies) {
                        // Verificar si la película ya existe
                        if (movieDao.countMovieById(peli.getId()) == 0) {
                            // Si la película no existe, insertarla
                            movieDao.insert(peli);
                            guardarImagenesLocalmente(peli);
                        } else {
                            if(peli.getDatosImagen()==null){
                                guardarImagenesLocalmente(peli);
                            }
                            Log.i("MovieInsertion", "La película con ID " + peli.getId() + " ya existe en la base de datos.");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                carga = true;
                conexion = "no";

                List<Movie> moviess =  movieDao.getMoviesEnCartelera();
                moviess.addAll(moviess);
                movies = moviess;
                createMovies();


            }
        });
        //Filtro ciudad
        retrofitCiudad = new Retrofit.Builder()
                .baseUrl("https://664b8b4835bbda10987d536c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        serviceCiudad =  retrofitCiudad.create(ICiudad.class);
        PeliculasCarteleraFragment peliculasCarteleraFragment = this;
        serviceCiudad.getAll().enqueue(new Callback<List<CiudadesDomain>>() {
            @Override
            public void onResponse(Call<List<CiudadesDomain>> call, Response<List<CiudadesDomain>> response) {
                if(response.isSuccessful()) {

                    adapterCiudad  = new FiltroCiudadCineAdpater(response.body(),peliculasCarteleraFragment,true);



                }

            }

            @Override
            public void onFailure(Call<List<CiudadesDomain>> call, Throwable t) {
                Log.i("kkkkkk","nouu",t);
            }
        });
        crearFiltroCiudad();

    }
    void crearFiltroCiudad(){
        bottomSheetDialog = new BottomSheetDialog(requireContext());
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.filtrar_ciudad_peliculas_main, null);


        bottomSheetDialog.setContentView(view1);


        recyclerViewCiudad = view1.findViewById(R.id.rv_filtro_ciudadd);
        recyclerViewCiudad.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));



        FrameLayout bottomSheet = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
            ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
            int windowHeight = getResources().getDisplayMetrics().heightPixels;
            layoutParams.height = windowHeight;
            bottomSheet.setLayoutParams(layoutParams);
            behavior.setPeekHeight(windowHeight);
        }
    }
    void guardarImagenesLocalmente(Movie movie){

        DescargarImagenTask descargarImagenTask = new DescargarImagenTask(this.getContext(),movie.getId(),true);
        descargarImagenTask.execute(movie.getSrc());

        DescargarImagenTask descargarImagenTask2 = new DescargarImagenTask(this.getContext(),movie.getId(),false);
        descargarImagenTask2.execute(movie.getUrlmini());
    }


    @Override
    public void clickFiltroCiudad(List<Integer> idMovies, String name) {
        ///

        adapter.getResultados().clear();
        bilding.txtCiudadName.setText(name);
        bilding.txtFechaName.setText("Fecha");
        for (Movie movie: adapter.getItems()){

            for(Integer i: idMovies){
                if(movie.getId()==i){

                    adapter.getResultados().add(movie);

                    Log.i("mi mentiraegs",new Gson().toJson(movie));
                }
            }
            Log.i("mi mentiraegs", String.valueOf(movie.getId()));
        }

        adapter.notifyDataSetChanged();
        bottomSheetDialog.dismiss();
    }
}