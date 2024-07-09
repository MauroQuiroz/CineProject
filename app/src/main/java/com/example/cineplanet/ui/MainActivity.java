package com.example.cineplanet.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityMainBinding;
import com.example.cineplanet.login.LoginActivity;
import com.example.cineplanet.login.RegistroActivity;
import com.example.cineplanet.login.domain.UserDomain;
import com.example.cineplanet.perfil.Perfil;
import com.example.cineplanet.search.BuscadorMain;
import com.example.cineplanet.ui.cines.CinesFragment;
import com.example.cineplanet.ui.home.MainHomeFragment;
import com.example.cineplanet.ui.peliculas.MainPeliculasFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bilding;
    FirebaseAuth mAuth;
    DatabaseReference mDataBase;
    UserDomain usuario;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bilding  = ActivityMainBinding.inflate(getLayoutInflater());
        FirebaseApp.initializeApp(this);
        setContentView(bilding.getRoot());
        bilding.progressBar.setVisibility(View.VISIBLE);

        replaceFragment(new MainHomeFragment());
        bilding.btnNavegation.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.homeM){
                replaceFragment(new MainHomeFragment());
            }
            if(item.getItemId()==R.id.moviesM){
                replaceFragment(new MainPeliculasFragment());
            }
            if(item.getItemId()==R.id.cinemaM){
                replaceFragment(new CinesFragment());
            }
            return true;
        });
        bilding.buscadorSearchMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscadorMain.class);

                startActivity(intent);
            }
        });
        bilding.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        bilding.btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        setContainerEnabled(false);

        bilding.btnUser.setVisibility(View.GONE);
        mDataBase  = FirebaseDatabase.getInstance().getReference();
       mAuth = FirebaseAuth.getInstance();
        auth();


    }


    void auth(){
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            Log.i("registrosfv","No etsa registrado ");
            setContainerEnabled(true);
            bilding.progressBar.setVisibility(View.GONE);
        } else {

            getAllUsers();

        }
    }
    private void getAllUsers() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mDataBase.child("Users").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserDomain user = snapshot.getValue(UserDomain.class);
                    Log.i("registrosfv",new Gson().toJson(user));
                    if(snapshot.getKey().equals(currentUser.getUid())){
                        setContainerEnabled(true);
                        bilding.progressBar.setVisibility(View.GONE);
                        usuario = user;
                        bilding.menuTop.setBackgroundColor(Color.parseColor("#5283D6"));
                        bilding.txtCineplaner.setTextColor(Color.WHITE);
                        bilding.btnRegistro.setVisibility(View.GONE);
                        bilding.btnUser.setVisibility(View.VISIBLE);

                        String nombre = usuario.getName();
                        String apellido = usuario.getLastNameP();
                        char primerNombre = nombre.charAt(0);
                        char primerApellido = apellido.charAt(0);

                        // Establecer texto en el botón o donde sea necesario
                        bilding.btnUser.setText(String.valueOf(primerNombre) + String.valueOf(primerApellido));
                        bilding.buscadorSearchMain.setImageResource(R.drawable.ic_search_white);
                        return;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                setContainerEnabled(true);
                bilding.progressBar.setVisibility(View.GONE);
                Log.e("MainActivity", "Error al obtener los usuarios", databaseError.toException());
            }
        });
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransition = fragmentManager.beginTransaction();
        fragmentTransition.replace(R.id.fragment_container_view,fragment);
        fragmentTransition.commit();


    }
    private void showDialog() {
        dialog = new Dialog(this, R.style.CustomDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_user);

        LinearLayout option1Button = dialog.findViewById(R.id.option1Button);
        LinearLayout option2Button = dialog.findViewById(R.id.option2Button);
        LinearLayout option3Button = dialog.findViewById(R.id.option3Button);
        LinearLayout option4Button = dialog.findViewById(R.id.option4Button);
        LinearLayout option5Button = dialog.findViewById(R.id.option5Button);

        option1Button.setOnClickListener(v -> {

            Gson gson = new Gson();
            String userJson = gson.toJson(usuario);
            Intent intent = new Intent(MainActivity.this, Perfil.class);
            intent.putExtra("user", userJson);
            startActivity(intent);
            dialog.dismiss();
        });

        option2Button.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Opción 2 seleccionada", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        option3Button.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Opción 3 seleccionada", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        option4Button.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Opción 4 seleccionada", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        option5Button.setOnClickListener(v -> {

            dialog.dismiss();
            mAuth.signOut();
            recreate();
        });

        // Configurar para cerrar el diálogo al hacer clic fuera de él
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }
    private void setContainerEnabled(boolean enabled) {
        bilding.main.setEnabled(enabled);
        bilding.btnRegistro.setEnabled(enabled);
        bilding.buscadorSearchMain.setEnabled(enabled);
        bilding.btnNavegation.setEnabled(enabled);
        if (enabled) {
            bilding.main.setAlpha(1f); // Restaura la opacidad completa
        } else {
            bilding.main.setAlpha(0.5f); // Aplica una opacidad reducida para indicar inactividad
        }
    }
}