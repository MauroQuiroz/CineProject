package com.example.cineplanet.ui.butaca;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.FragmentAsientoBinding;
import com.example.cineplanet.databinding.FragmentPagoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pago#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pago extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentPagoBinding bilding;
    public  static IClickPagar iClickPagar;

    public Pago() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pago.
     */
    // TODO: Rename and change types and number of parameters
    public static Pago newInstance(String param1, String param2) {
        Pago fragment = new Pago();
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
        bilding = FragmentPagoBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return bilding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bilding.total.setText(String.valueOf(Entrada.valueGenera*12));

    }
}