package cl.practica.android.java.apptradeajavaxml;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaginaInicio extends AppCompatActivity {

    private TextView tvBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_inicio);

        String nombre = getIntent().getStringExtra("nombre");

        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvBienvenida.setText("Bienvenid@ " + nombre);
    }
}