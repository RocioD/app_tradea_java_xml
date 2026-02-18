package cl.practica.android.java.apptradeajavaxml;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CrearCuenta extends AppCompatActivity {

    private BufferedReader bufferedReader;

    private EditText etNombre;
    private EditText etCorreo;
    private EditText etClave;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_cuenta);

        HashMap<String,String> usuarios = new HashMap<>();

        if (ListaUsuarios.listaUsuarios.isEmpty()){
            //Obtener el InputStream del archivo en res/raw/datos.txt
            InputStream is = getResources().openRawResource(R.raw.datos);

            String datosCompletos = "";
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(is));
                String linea;
                StringBuilder contenidoCompleto = new StringBuilder();
                while ((linea = bufferedReader.readLine()) != null){
                    String usuario[] = linea.split(";");
                    usuarios.put(usuario[1], usuario[0]);
                    Usuario user = new Usuario(usuario[0], usuario[1], usuario[2]);
                    ListaUsuarios.listaUsuarios.add(user);
                    contenidoCompleto.append(linea).append("\n");
                }
                datosCompletos = contenidoCompleto.toString();
            } catch (Exception e){
                Log.e("Error al leer", e.getMessage());
            }
            Log.i("datos leídos", datosCompletos);
        } else {
            for (Usuario user: ListaUsuarios.listaUsuarios){
                usuarios.put(user.getCorreo(), user.getNombre());
            }
        }

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etClave = findViewById(R.id.etClave);
        btnCrear = findViewById(R.id.btnCreacionCuenta);

        btnCrear.setOnClickListener(v -> {
            String strNombre = etNombre.getText().toString().trim();
            String strCorreo = etCorreo.getText().toString().trim();
            String strClave = etClave.getText().toString().trim();

            Log.i("DATOSSSSSS", strNombre + strCorreo + strClave);

            if (strNombre.isBlank() || strCorreo.isBlank() || strClave.isBlank()){
                String mensaje = "Error, hay campos vacíos";
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            } else {
                if (usuarios.containsKey(strCorreo)){
                    String mensaje = "Este correo ya está registrado";
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                } else {
                    Usuario usuario = new Usuario(strNombre, strCorreo, strClave);
                    if (guardarCuenta(usuario)){
                        String mensaje = "Usuario creado con éxito";
                        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                    } else {
                        String mensaje = "No se ha podido crear el usuario";
                        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
                    }
                    //Volver a página inicial
                    Intent intent = new Intent(CrearCuenta.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });



    }

    protected boolean guardarCuenta(Usuario usuario){
        return ListaUsuarios.listaUsuarios.add(usuario);
    }
}