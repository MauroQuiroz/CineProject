package com.example.cineplanet.ui.butaca;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.FragmentAsientoBinding;
import com.example.cineplanet.databinding.FragmentPeliculasCarteleraBinding;
import com.example.cineplanet.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Asiento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Asiento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentAsientoBinding bilding;
    FirebaseAuth mAuth;
    public static IClickContinuar iClickContinuar;

    public Asiento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Asiento.
     */
    // TODO: Rename and change types and number of parameters
    public static Asiento newInstance(String param1, String param2) {
        Asiento fragment = new Asiento();
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
        bilding  =   FragmentAsientoBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return bilding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();


        bilding.btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CanvasButaca.btnComprar){
                    mAuth = FirebaseAuth.getInstance();
                    Log.i("jhampolog","sds");
                    FirebaseUser currentUser = mAuth.getCurrentUser();

                    if (currentUser == null) {
                        Log.i("registrosfv", "No esta registrado");
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        CanvasButaca.btnComprar = false;
                        startActivity(intent);
                    } else {
                        Log.i("registrosfv", "Siuuuo");
                        iClickContinuar.changeFragment();
                    }
                }

            }
        });

    }
}