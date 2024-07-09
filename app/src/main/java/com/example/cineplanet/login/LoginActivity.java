package com.example.cineplanet.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cineplanet.R;
import com.example.cineplanet.databinding.ActivityLoginBinding;
import com.example.cineplanet.databinding.ActivityRegistroBinding;
import com.example.cineplanet.perfil.PasswordOlvide;
import com.example.cineplanet.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        setContentView(binding.getRoot());
        setContainerEnabled(true);
        binding.backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.loginRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);

                startActivity(intent);
                finish();
            }
        });
        binding.olvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PasswordOlvide.class);

                startActivity(intent);
            }
        });
        binding.inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                setContainerEnabled(false);
                inicarSesion();
            }
        });
    }
    void inicarSesion(){
        String email = binding.correoElectronico.getText().toString();
        String password = binding.pass.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    binding.progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        setContainerEnabled(true);
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        setContainerEnabled(true);
                        binding.errorTextViewCorreo.setVisibility(View.GONE);
                        binding.errorTextViewContraseA.setText("Correo o contraseña invalidos");
                        binding.errorTextViewContraseA.setVisibility(View.VISIBLE);
                    }
                }
            });

        } else {
            setContainerEnabled(true);
            binding.progressBar.setVisibility(View.GONE);
            binding.errorTextViewCorreo.setVisibility(View.GONE);
            binding.errorTextViewContraseA.setVisibility(View.GONE);
            if (email.isEmpty()){
                binding.errorTextViewCorreo.setText("El correo es obligatorio");
                binding.errorTextViewCorreo.setVisibility(View.VISIBLE);
            }
            if (password.isEmpty()){
                binding.errorTextViewContraseA.setText("La contraseña es obligatoria");
                binding.errorTextViewContraseA.setVisibility(View.VISIBLE);
            }

        }
    }
    private void setContainerEnabled(boolean enabled) {
        binding.main.setEnabled(enabled);
        if (enabled) {
            binding.main.setAlpha(1f); // Restaura la opacidad completa
        } else {
            binding.main.setAlpha(0.5f); // Aplica una opacidad reducida para indicar inactividad
        }
    }
}