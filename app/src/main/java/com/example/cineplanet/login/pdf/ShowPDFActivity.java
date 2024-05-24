package com.example.cineplanet.login.pdf;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityPeliculaShowBinding;
import com.example.cineplanet.databinding.ActivityShowPdfactivityBinding;
import com.pdfview.PDFView;

import java.io.File;

public class ShowPDFActivity extends AppCompatActivity {

    ActivityShowPdfactivityBinding binding;
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityShowPdfactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pdfView  = binding.pdfContainer;

        Intent intent = getIntent();
        String datoString = "";
        datoString = intent.getStringExtra("datosPDF");
        if(datoString.equals("Politicas")){

            pdfView.fromAsset("Politicas_Privacidadv2.pdf").show();
        }else{
            pdfView.fromAsset("Terminos_y_condiciones.pdf").show();
        }

    }
}