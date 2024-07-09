package com.example.cineplanet.ui.butaca;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.FragmentAsientoBinding;
import com.example.cineplanet.databinding.FragmentEntradaBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Entrada#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Entrada extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentEntradaBinding bilding;
    int max = 0;
    public static int valueGenera  = 0;
    public static  int valueChild = 0;
    int total = 0;
    public Entrada() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Entrada.
     */
    // TODO: Rename and change types and number of parameters
    public static Entrada newInstance(String param1, String param2) {
        Entrada fragment = new Entrada();
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
        bilding  =   FragmentEntradaBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return bilding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        total = CanvasButaca.asientosEle.size()-1;
        //aqui
        bilding.btnGeneralMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueGenera--;
                if(valueGenera==0){
                    bilding.btnGeneralMin.setColorFilter(Color.parseColor("#A9A7A5"));
                }
                if(valueGenera<0){
                    valueGenera= 0;

                }
                bilding.valueGeneral.setText(String.valueOf(valueGenera));
            }
        });
        bilding.btnGeneralMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(valueGenera+valueChild>total){

                }else{
                    valueGenera++;
                    if(valueGenera>=0){
                        bilding.btnGeneralMin.setColorFilter(R.color.color_text);
                    }
                }

                bilding.valueGeneral.setText(String.valueOf(valueGenera));
            }
        });
        bilding.btnChildMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueChild--;
                if(valueChild==0){
                    bilding.btnChildMin.setColorFilter(Color.parseColor("#A9A7A5"));
                }
                if(valueChild<0){
                    valueChild= 0;

                }
                bilding.valueChild.setText(String.valueOf(valueChild));
            }
        });
        bilding.btnChildMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(valueChild+valueGenera>total){

                }else{
                    valueChild++;
                    if(valueChild>=0){
                        bilding.btnChildMin.setColorFilter(R.color.color_text);
                    }
                }

                bilding.valueChild.setText(String.valueOf(valueChild));
            }
        });

        bilding.btnContinuarPago.setOnContextClickListener(new View.OnContextClickListener() {
            @Override
            public boolean onContextClick(View v) {
                C
            }
        });
    }
}