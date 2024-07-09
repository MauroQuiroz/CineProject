package com.example.cineplanet.ui.cines;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.example.cineplanet.R;
import com.example.cineplanet.databinding.FragmentCinesBinding;
import com.example.cineplanet.ui.cines.adapter.CineAdapter;
import com.example.cineplanet.ui.cines.adapter.FiltroCiudadCineAdpater;
import com.example.cineplanet.ui.cines.domain.CalculateDistanceTask;
import com.example.cineplanet.ui.cines.domain.CineDomain;
import com.example.cineplanet.ui.cines.domain.CiudadesDomain;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CinesFragment extends Fragment implements IClickFiltroCiudad, OnMapReadyCallback {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //
    FragmentCinesBinding binding;
    private Retrofit retrofit;
    ICineAPI service;
    RecyclerView recyclerView;
    CineAdapter adapter;
    BottomSheetDialog bottomSheetDialog;
    BottomSheetDialog bottomSheetDialogSala;
    //
    private Retrofit retrofitCiudad;
    ICiudad serviceCiudad;
    RecyclerView recyclerViewCiudad;
    FiltroCiudadCineAdpater adapterCiudad;

    List<CineDomain> ciness;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private Marker currentLocationMarker;
    private ListView distanceListView;
    Boolean adapaterCargado = false;

    public CinesFragment() {
        // Required empty public constructor
    }


    public static CinesFragment newInstance(String param1, String param2) {
        CinesFragment fragment = new CinesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.i("Estoydsfd","hola");
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {

                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    Log.i("Estoydsfd","holasss");
                    updateLocation(location);

                }
            }

        };
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            startLocationUpdates();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCinesBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Context context = this.getContext();
        service.getAll().enqueue(new Callback<List<CineDomain>>() {
            @Override
            public void onResponse(Call<List<CineDomain>> call, Response<List<CineDomain>> response) {
                if (response.isSuccessful()) {

                    ciness = response.body();

                    recyclerView = binding.RvCinesMain;
                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    adapter = new CineAdapter(response.body());
                    adapter.setResultados(response.body());
                    recyclerView.setAdapter(adapter);
                    adapaterCargado = true;
                }
            }

            @Override
            public void onFailure(Call<List<CineDomain>> call, Throwable t) {

            }
        });
        startLocationUpdates();
    }
    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }
    private void updateLocation(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (currentLocationMarker != null) {
            currentLocationMarker.setPosition(latLng);
        } else {
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Ubicación Actual");
            //currentLocationMarker = mMap.addMarker(markerOptions);
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        if(adapaterCargado){
            new CalculateDistanceTask(ciness, location, adapter).execute();
        }
    }
    private void calculateDistances(Location currentLocation) {
        //Log.i("sdfdsgf",String.valueOf(currentLocation.getLatitude()));


        if(adapaterCargado){
            for (CineDomain cinema : ciness) {
                Location cinemaLocation = new Location("");
                cinemaLocation.setLatitude(cinema.getLatitude());
                cinemaLocation.setLongitude(cinema.getLongitude());

                float distance = currentLocation.distanceTo(cinemaLocation);

                cinema.setDistancia(distance);

            }
            adapter.notifyDataSetChanged();
        }



    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init();

    }




    void init(){



        binding.filtroCiudadCineMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewCiudad.setAdapter(adapterCiudad);
                //openFiltroCiudad();
                bottomSheetDialog.show();
            }
        });
        binding.btnSalaFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogSala.show();
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d25e3e7b95ad7fa6c4a63.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        service = retrofit.create(ICineAPI.class);

        retrofitCiudad = new Retrofit.Builder()
                .baseUrl("https://664b8b4835bbda10987d536c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instanciar IContactService
        serviceCiudad =  retrofitCiudad.create(ICiudad.class);
        //
        createFiltroCiudad();
        createFiltroSala();
        CinesFragment cinesFragment = this;
        serviceCiudad.getAll().enqueue(new Callback<List<CiudadesDomain>>() {
            @Override
            public void onResponse(Call<List<CiudadesDomain>> call, Response<List<CiudadesDomain>> response) {
                if(response.isSuccessful()) {

                    adapterCiudad  = new FiltroCiudadCineAdpater(response.body(),cinesFragment,false);
                    Log.i("mi mentira",new Gson().toJson(response.body()));
                }

            }

            @Override
            public void onFailure(Call<List<CiudadesDomain>> call, Throwable t) {
                Log.i("kkkkkk","nouu",t);
            }
        });
    }
    void createFiltroSala(){

        bottomSheetDialogSala = new BottomSheetDialog(requireContext());
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.filtro_sala, null);
        bottomSheetDialogSala.setContentView(view1);

        eventBtnSalas(view1);



        FrameLayout bottomSheet = bottomSheetDialogSala.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
            ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
            int windowHeight = getResources().getDisplayMetrics().heightPixels;
            layoutParams.height = windowHeight;
            bottomSheet.setLayoutParams(layoutParams);
            behavior.setPeekHeight(windowHeight);
        }

    }
    void filtroFromSala(String data){

        adapter.getResultados().clear();
        binding.txtNameSala.setText(data);
        binding.txtNameCine.setText("Ciudad");
        for (CineDomain cineDomain: adapter.getItems()){
            for(String dataCine: cineDomain.getAvaliable()){
                if(dataCine.equals(data)){
                    adapter.getResultados().add(cineDomain);
                }
            }

        }

        adapter.notifyDataSetChanged();
        bottomSheetDialogSala.dismiss();
    }
    void eventBtnSalas(View view1){
        LinearLayout txt2d = view1.findViewById(R.id.txt_2d);
        LinearLayout txtRegular = view1.findViewById(R.id.txt_regular);
        LinearLayout txt3d = view1.findViewById(R.id.txt_3d);
        LinearLayout txtPrime = view1.findViewById(R.id.txt_prime);
        LinearLayout txtXtreme = view1.findViewById(R.id.txt_xtreme);
        LinearLayout txtScreenX = view1.findViewById(R.id.txt_screenx);

        txt2d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtroFromSala("2D");
            }
        });

        txtRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtroFromSala("REGULAR");
            }
        });

        txt3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtroFromSala("3D");
            }
        });

        txtPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtroFromSala("PRIME");
            }
        });

        txtXtreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtroFromSala("XTREME");
            }
        });

        txtScreenX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtroFromSala("SCREENX");
            }
        });
    }

    void createFiltroCiudad(){
        bottomSheetDialog = new BottomSheetDialog(requireContext());
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.filtrar_ciudad_peliculas_main, null);
        bottomSheetDialog.setContentView(view1);
        Context context = this.getContext();

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

    @Override
    public void clickFiltroCiudad(List<Integer> idCines,String name) {

        adapter.getResultados().clear();
        binding.txtNameCine.setText(name);
        binding.txtNameSala.setText("Sala");
        for (CineDomain cineDomain: adapter.getItems()){

            for(Integer i: idCines){
                if(String.valueOf(i).equals(cineDomain.getId())){

                    adapter.getResultados().add(cineDomain);
                }
            }

        }

        adapter.notifyDataSetChanged();
        bottomSheetDialog.dismiss();
    }
    //
    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
}