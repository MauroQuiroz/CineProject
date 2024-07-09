package com.example.cineplanet.ui.peliculas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityPeliculaShowBinding;
import com.example.cineplanet.ui.peliculas.adapters.ViewDetallePeliculasAdapter;
import com.example.cineplanet.ui.peliculas.daos.MovieDAO;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaDetail;
import com.example.cineplanet.ui.peliculas.services.AppDatabase;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculaShow extends AppCompatActivity {

    ActivityPeliculaShowBinding binding;
    int idPelicula;

    private Retrofit retrofit;
    IPeliculaDetail service;
    int id;
    ViewDetallePeliculasAdapter viewPeliculasAdpater;
    String cxt = "no";
    Movie movie;
    private MovieDAO movieDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPeliculaShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppDatabase db = AppDatabase.getInstance(this);
        movieDao = db.movieDAO();

        id = Integer.valueOf(getIntent().getStringExtra("id"));
        cxt = getIntent().getStringExtra("cxt");
        movie =   movieDao.getMovieById(id);
        MostrarDatos();;

        //init();
    }

    private void init() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d2649e7b95ad7fa6c4c14.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        service = retrofit.create(IPeliculaDetail.class);

    }

    @Override
    protected void onResume() {
        super.onResume();



        ///

    }

    private void MostrarDatos() {

        binding.peliculaTitle.setText(movie.getName());
        binding.generoHoraEdad.setText(movie.getGender() + " | " + movie.getDuration() + " | " + movie.getAge());
        Log.i("jhuleeeei", "Datos de imagen recibidos: " + "aNTES DE SIUU");
        if(cxt.equals("si")){
            Picasso.get().load(movie.getUrlmini()).into(binding.imageMini);
            binding.imageMini.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String videoUrl = movie.getUrl(); // Reemplaza VIDEO_ID con el ID del video

                    // Abrir el enlace en la aplicación de YouTube
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
                    startActivity(intent);
                }
            });
        }else{
            byte[] datosImagen = movie.getDatosImagenMini();
            if (datosImagen != null && datosImagen.length > 0) {
                Log.i("jhuleeeei", "Datos de imagen recibidos: " + datosImagen.length + " bytes");
                Bitmap bitmap = BitmapFactory.decodeByteArray(datosImagen, 0, datosImagen.length);
                if (bitmap != null) {
                    binding.imageMini.setImageBitmap(bitmap);
                    Log.i("jhuleeeei", "Imagen decodificada y establecida en ImageView");
                } else {
                    Log.e("jhuleeeei", "La decodificación de la imagen falló.");
                   binding.imageMini.setImageResource(R.drawable.no_internet); // Placeholder
                }
            } else {
                Log.i("jhuleeeei", "Datos de imagen nulos o vacíos");
               binding.imageMini.setImageResource(R.drawable.no_internet); // Placeholder
            }

        }


        ///FrAGMNET
        viewPeliculasAdpater = new ViewDetallePeliculasAdapter(this, movie);
        binding.fragmentContainerPeliculasDetail.setAdapter(viewPeliculasAdpater);



        //
        new TabLayoutMediator(binding.tabPeliculas, binding.fragmentContainerPeliculasDetail, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("DETALLE");
                    break;
                case 1:
                    tab.setText("COMPRAR");
                    break;
                default:
                    tab.setText("Desconocido");
                    break;
            }
        }).attach();
        binding.tabPeliculas.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String sData = new Gson().toJson(movie);
                binding.fragmentContainerPeliculasDetail.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}