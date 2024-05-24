package com.example.cineplanet.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.databinding.ActivityRegistroBinding;
import com.example.cineplanet.domain.CineDomain;
import com.example.cineplanet.login.Entities.IUser;
import com.example.cineplanet.login.domain.UserDomain;
import com.example.cineplanet.login.pdf.ShowPDFActivity;
import com.example.cineplanet.perfil.PerfilActivity;
import com.example.cineplanet.ui.peliculas.adapters.CineAdapterPelicula;
import com.example.cineplanet.ui.peliculas.adapters.PeliculasAdapter;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaDetail;
import com.example.cineplanet.ui.peliculas.entities.IPeliculaShow;
import com.example.cineplanet.ui.peliculas.services.CinePelicula;
import com.example.cineplanet.ui.peliculas.services.Movies;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroActivity extends AppCompatActivity implements IRecyFromActivity{
    ActivityRegistroBinding binding;
    private Retrofit retrofit;
    ICine service;
    RecyclerView recycler;
    CineFavoritoAdapter adapter;
    BottomSheetDialog bottomSheetDialog;
    boolean genderSelect  = true;
    boolean genferInit = false;
    //
    private Retrofit retrofit2;
    IUbigeo serviceDepart;

    //
    private Retrofit retrofitRegsitro;
    IUser serviceUser;
    String departamentoSeleccionado;
    String prvinciaSeleccionado;
    String distritoSeleccionad;
    int idCineFavorito = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initGenders();
        initServices();
        registrar();
        initDatos();
        Subrrayados();
    }

    void initDatos(){
        //
        binding.btnUnete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registro();
            }
        });
        //
        retrofit2 = new Retrofit.Builder()
                .baseUrl("https://664b8b4835bbda10987d536c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceDepart = retrofit2.create(IUbigeo.class);
        RegistroActivity registroActivity = this;

        serviceDepart.getAll().enqueue(new Callback<List<Ubigeo>>() {
            @Override
            public void onResponse(Call<List<Ubigeo>> call, Response<List<Ubigeo>> response) {
                if(response.code() == 200){
                    List<String> dep = new ArrayList<>();
                    for (Ubigeo ubigeo: response.body()){
                        dep.add(ubigeo.getName());
                    }
                    //
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(registroActivity, android.R.layout.simple_spinner_item, dep) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            TextView textView = (TextView) view.findViewById(android.R.id.text1);
                            textView.setTextColor(Color.parseColor("#003F89"));
                            textView.setBackgroundColor(Color.WHITE);
                            textView.setPadding(10, 10, 10, 10);

                            return view;
                        }

                        @Override
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);

                            TextView textView = (TextView) view.findViewById(android.R.id.text1);
                            textView.setTextColor(Color.parseColor("#003F89"));
                            textView.setBackgroundColor(Color.WHITE);
                            textView.setPadding(10, 10, 10, 10);

                            return view;
                        }
                    };
                    //
                    Spinner depart = binding.spinnerDepartamento;
                    depart.setAdapter(adapter);

                    depart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            departamentoSeleccionado = parent.getItemAtPosition(position).toString();

                            serviceDepart.getRegionByName(departamentoSeleccionado).enqueue(new Callback<List<Ubigeo>>() {
                                @Override
                                public void onResponse(Call<List<Ubigeo>> call, Response<List<Ubigeo>> response) {
                                    if(response.isSuccessful()){
                                        Log.i("holaaaa","Siuuu");
                                        List<String> provincia = new ArrayList<>();
                                        for (Ubigeo.Province name: response.body().get(0).getProvinces()){
                                            provincia.add(name.getName());
                                        }
                                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(registroActivity, android.R.layout.simple_spinner_item, provincia){
                                            @Override
                                            public View getView(int position, View convertView, ViewGroup parent) {
                                                View view = super.getView(position, convertView, parent);

                                                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                                                textView.setTextColor(Color.parseColor("#003F89"));
                                                textView.setBackgroundColor(Color.WHITE);
                                                textView.setPadding(10, 10, 10, 10);

                                                return view;
                                            }

                                            @Override
                                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                                View view = super.getDropDownView(position, convertView, parent);

                                                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                                                textView.setTextColor(Color.parseColor("#003F89"));
                                                textView.setBackgroundColor(Color.WHITE);
                                                textView.setPadding(10, 10, 10, 10);

                                                return view;
                                            }
                                        };
                                        Spinner provinciaS = binding.spinnerProvincia;
                                        provinciaS.setAdapter(adapter2);
                                        provinciaS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                prvinciaSeleccionado = parent.getItemAtPosition(position).toString();
                                                List<String> distrito = new ArrayList<>();
                                                for (Ubigeo.Province province: response.body().get(0).getProvinces()){
                                                    if(province.getName().equals(prvinciaSeleccionado)){
                                                        distrito = province.getDistricts();
                                                    }
                                                }
                                                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(registroActivity, android.R.layout.simple_spinner_item, distrito){
                                                    @Override
                                                    public View getView(int position, View convertView, ViewGroup parent) {
                                                        View view = super.getView(position, convertView, parent);

                                                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                                                        textView.setTextColor(Color.parseColor("#003F89"));
                                                        textView.setBackgroundColor(Color.WHITE);
                                                        textView.setPadding(10, 10, 10, 10);

                                                        return view;
                                                    }

                                                    @Override
                                                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                                        View view = super.getDropDownView(position, convertView, parent);

                                                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                                                        textView.setTextColor(Color.parseColor("#003F89"));
                                                        textView.setBackgroundColor(Color.WHITE);
                                                        textView.setPadding(10, 10, 10, 10);

                                                        return view;
                                                    }
                                                };
                                                Spinner destri = binding.spinnerDistrito;
                                                destri.setAdapter(adapter3);


                                                destri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        distritoSeleccionad = parent.getItemAtPosition(position).toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Ubigeo>> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<Ubigeo>> call, Throwable t) {

            }
        });
        //init Api user
        retrofitRegsitro = new Retrofit.Builder()
                .baseUrl("https://664b72c235bbda10987cfc33.mockapi.io//")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceUser = retrofitRegsitro.create(IUser.class);

    }
    void changeGenders(CardView cardView){
        if(cardView == binding.cardviewHombre){
            binding.cardviewHombre.setCardBackgroundColor(Color.parseColor("#004A8B"));
            binding.cardviewMujer.setCardBackgroundColor(Color.parseColor("#C7CACF"));
            genderSelect = true;
            genferInit = true;
        }else {
            binding.cardviewMujer.setCardBackgroundColor(Color.parseColor("#004A8B"));
            binding.cardviewHombre.setCardBackgroundColor(Color.parseColor("#C7CACF"));
            genderSelect = false;
            genferInit = true;
        }
    }
    void initGenders(){
        binding.cardviewHombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGenders(binding.cardviewHombre);
            }
        });
        binding.cardviewMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGenders(binding.cardviewMujer);
            }
        });
    }
    void initServices(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://661d25e3e7b95ad7fa6c4a63.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ICine.class);
        RegistroActivity registroActivity = this;
        service.getAll().enqueue(new Callback<List<CineDomain>>() {
            @Override
            public void onResponse(Call<List<CineDomain>> call, Response<List<CineDomain>> response) {
                if(response.isSuccessful()){
                    adapter  = new CineFavoritoAdapter(response.body(),registroActivity);
                }else{

                }
            }
            @Override
            public void onFailure(Call<List<CineDomain>> call, Throwable t) {

            }
        });
    }
    void registrar(){
        binding.fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        binding.cinemaFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openButtomSheet();
            }
        });
    }
    void openButtomSheet(){
        bottomSheetDialog = new BottomSheetDialog(RegistroActivity.this);
        View view1 = LayoutInflater.from(RegistroActivity.this).inflate(R.layout.cinemas_favoritos, null);
        bottomSheetDialog.setContentView(view1);
        recycler= view1.findViewById(R.id.recycler_cines_favorito);
        recycler.setLayoutManager(new LinearLayoutManager(RegistroActivity.this,LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(adapter);

        FrameLayout bottomSheet = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
            ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
            int windowHeight = getResources().getDisplayMetrics().heightPixels;
            layoutParams.height = windowHeight;
            bottomSheet.setLayoutParams(layoutParams);
            behavior.setPeekHeight(windowHeight);
        }
        bottomSheetDialog.show();
        ImageView btn = view1.findViewById(R.id.btn_cierre_cine_f);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }
    void openDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.txtFecha.setText(String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));
            }
        },2022,0,15);
        dialog.show();
    }

    @Override
    public void onItemClick(String name, int id) {
        bottomSheetDialog.dismiss();
        binding.cinemaFavorito.setText(name);
        idCineFavorito = id;
    }

    void Registro(){
        boolean isValid = true;
        //
        String nombre = binding.nombre.getText().toString().trim();
        if (TextUtils.isEmpty(nombre)) {
            binding.errorTextViewNombre.setText("*Ingrese un nombre");
            binding.errorTextViewNombre.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            binding.errorTextViewNombre.setText("");
            binding.errorTextViewNombre.setVisibility(View.GONE);
        }
        //
        String apellidoP = binding.apellidoP.getText().toString().trim();
        if (TextUtils.isEmpty(apellidoP)) {
            binding.errorTextViewApellidoP.setText("*Ingrese un apellido Paterno    ");
            binding.errorTextViewApellidoP.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            binding.errorTextViewApellidoP.setText("");
            binding.errorTextViewApellidoP.setVisibility(View.GONE);
        }
        //
        String apellidoM = binding.apellidoM.getText().toString().trim();
        if (TextUtils.isEmpty(apellidoM )) {
            binding.errorTextViewApellidoM.setText("*Ingrese un apellido Materno");
            binding.errorTextViewApellidoM.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            binding.errorTextViewApellidoM.setText("");
            binding.errorTextViewApellidoM.setVisibility(View.GONE);
        }
        //email
        String correoElectronico = binding.correoElectronico.getText().toString().trim();
        if (TextUtils.isEmpty(correoElectronico)) {
            binding.errorTextViewCorre.setText("El campo Correo Electrónico es obligatorio");
            binding.errorTextViewCorre.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correoElectronico).matches()) {
            binding.errorTextViewCorre.setText("Correo Electrónico no válido");
            binding.errorTextViewCorre.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            binding.errorTextViewCorre.setText("");
            binding.errorTextViewCorre.setVisibility(View.GONE);
        }
        //number
        String celular = binding.celular.getText().toString().trim();
        if (TextUtils.isEmpty(celular)) {
            binding.errorTextViewCelular.setText("El campo Número de Celular es obligatorio");
            binding.errorTextViewCelular.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (!celular.matches("\\d{9}")) { // Asumiendo que el número de celular debe tener 10 dígitos
            binding.errorTextViewCelular.setText("Número de Celular no válido");
            binding.errorTextViewCelular.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            binding.errorTextViewCelular.setText("");
            binding.errorTextViewCelular.setVisibility(View.GONE);
        }
        // Validación para el campo Contraseña
        String contraseña = binding.intContraseAa.getText().toString().trim();
        if (TextUtils.isEmpty(contraseña)) {
            binding.errorTextViewContraseA.setText("El campo Contraseña es obligatorio");
            binding.errorTextViewContraseA.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (!isValidPassword(contraseña)) {
            binding.errorTextViewContraseA.setText("La contraseña debe tener al menos 8 caracteres como minimo, y contener al menos dos de los siguientes elementos: mayúsculas, minúsculas, números y símbolos (-*?!@#$/(){}=.,;:)");
            binding.errorTextViewContraseA.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            binding.errorTextViewContraseA.setText("");
            binding.errorTextViewContraseA.setVisibility(View.GONE);
        }
        //
        String dni = binding.dni.getText().toString().trim();
        if (TextUtils.isEmpty(dni)) {
            binding.errorTextViewDNI.setText("El campo Número del DNI es obligatorio");
            binding.errorTextViewDNI.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (!dni.matches("\\d{8}")) {
            binding.errorTextViewDNI.setText("Número de DNI no válido");
            binding.errorTextViewDNI.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            binding.errorTextViewDNI.setText("");
            binding.errorTextViewDNI.setVisibility(View.GONE);
        }
        //Genero
        if(!genferInit){
            binding.errorTextViewGenero.setText("El género es obligatorio");
            binding.errorTextViewGenero.setVisibility(View.VISIBLE);
            isValid = false;
        }else{
            binding.errorTextViewGenero.setText("");
            binding.errorTextViewGenero.setVisibility(View.GONE);
        }
        //Fecha
        if(binding.txtFecha.getText().equals("DD-MM-AAAA")){
            binding.errorTextViewFecha.setText("Seleccione una fecha de nacimiento");
            binding.errorTextViewFecha.setVisibility(View.VISIBLE);
            isValid = false;
        }else{
            binding.errorTextViewFecha.setText("");
            binding.errorTextViewFecha.setVisibility(View.GONE);
        }
        //Terminso
        if(!binding.terminosCheck.isChecked()){
            binding.errorTextViewTerminos.setText("Debe aceptar los terminos y condiciones");
            binding.errorTextViewTerminos.setVisibility(View.VISIBLE);
            isValid = false;
        }else{
            binding.errorTextViewTerminos.setText("");
            binding.errorTextViewTerminos.setVisibility(View.GONE);
        }
        //Porfin Registro

       if(!isValid){
           binding.errorTextViewTotal.setText("Algunos campos incorrectos");
           binding.errorTextViewTotal.setVisibility(View.VISIBLE);

       }
        if(isValid){
            binding.errorTextViewTotal.setText("");
            binding.errorTextViewTotal.setVisibility(View.GONE);

            String gender = "Hombre";
            if(!genderSelect){
                gender = "Mujer";
            }
            UserDomain userDomain = new UserDomain(
                    String.valueOf(binding.nombre.getText()),
                    String.valueOf(binding.apellidoP.getText()),
                    String.valueOf(binding.apellidoM.getText()),
                    String.valueOf(binding.correoElectronico.getText()),
                    String.valueOf(binding.celular.getText()),
                    String.valueOf(binding.intContraseAa.getText()),
                    String.valueOf(binding.dni.getText()),
                    departamentoSeleccionado,
                    prvinciaSeleccionado,
                    distritoSeleccionad,
                    gender,
                    String.valueOf(binding.txtFecha.getText()),
                    idCineFavorito,
                    0

            );
            serviceUser.create(userDomain).enqueue(new Callback<UserDomain>() {
                @Override
                public void onResponse(Call<UserDomain> call, Response<UserDomain> response) {
                    Intent intent = new Intent(RegistroActivity.this, PerfilActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<UserDomain> call, Throwable t) {
                    Log.i("userReg","snooouu",t);
                }
            });

        }

    }
    private boolean isValidPassword(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;
        String symbols = "-*?!@#$/(){}=.,;:";

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (symbols.indexOf(c) != -1) {
                hasSymbol = true;
            }
        }

        return hasUpperCase || hasLowerCase || hasDigit || hasSymbol;
    }
    void Subrrayados(){
        TextView textView = binding.txtTerminosYCondiciones; //
        String text = "Acepto los Términos y condiciones y la Política de Privacidad";
        SpannableString spannableString = new SpannableString(text);

        int startIndex1 = text.indexOf("Términos y condiciones");
        int endIndex1 = startIndex1 + "Términos y condiciones".length();
        spannableString.setSpan(new UnderlineSpan(), startIndex1, endIndex1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int startIndex2 = text.indexOf("Política de Privacidad");
        int endIndex2 = startIndex2 + "Política de Privacidad".length();
        spannableString.setSpan(new UnderlineSpan(), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);

        binding.txtTerminosYCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(RegistroActivity.this);
                View view1 = LayoutInflater.from(RegistroActivity.this).inflate(R.layout.terminos_condiciones, null);
                bottomSheetDialog.setContentView(view1);
                TextView btnTerminos = view1.findViewById(R.id.txt_terminos_and_condiciones);
                TextView politicas = view1.findViewById(R.id.txt_politicas);
                TextView cancelar = view1.findViewById(R.id.txt_cancelar);

                btnTerminos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegistroActivity.this, ShowPDFActivity.class);
                        intent.putExtra("datosPDF", "Terminos");
                        startActivity(intent);

                    }
                });
                politicas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegistroActivity.this, ShowPDFActivity.class);
                        intent.putExtra("datosPDF", "Politicas");
                        startActivity(intent);
                    }
                });

                bottomSheetDialog.show();
            }
        });
    }
}