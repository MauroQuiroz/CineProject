package com.example.cineplanet.ui.peliculas;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.databinding.FragmentMainPeliculasBinding;
import com.example.cineplanet.ui.peliculas.adapters.ViewPeliculasAdpater;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainPeliculasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPeliculasFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    FragmentMainPeliculasBinding bilding;
    ViewPeliculasAdpater viewPeliculasAdpater;

    public MainPeliculasFragment() {
        // Required empty public constructor

    }

    public static MainPeliculasFragment newInstance(String param1, String param2) {
        MainPeliculasFragment fragment = new MainPeliculasFragment();
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
        bilding  =  FragmentMainPeliculasBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return bilding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPeliculasAdpater = new ViewPeliculasAdpater(this);
        bilding.fragmentContainerPeliculas.setAdapter(viewPeliculasAdpater);

        bilding.tabPeliculas.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bilding.fragmentContainerPeliculas.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        configureTabLayout();
    }
    private void configureTabLayout() {
        // Set text colors
        bilding.tabPeliculas.setTabTextColors(Color.parseColor("#6D6B69"), Color.parseColor("#E50246"));
        // Set indicator color
        bilding.tabPeliculas.setSelectedTabIndicatorColor(Color.parseColor("#E50246"));
    }
}