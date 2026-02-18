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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

public class IniciarSesion extends AppCompatActivity {

    private BufferedReader bufferedReader;

    private EditText etUsuario;
    private EditText etClave;
    private Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iniciar_sesion);


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
                    contenidoCompleto.append(linea).append("\n");
                }
                datosCompletos = contenidoCompleto.toString();
            } catch (Exception e){
                Log.e("Error al leer", e.getMessage());
            }
            Log.i("datos leídos", datosCompletos);
        }

        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClaveIniciar);
        btnIniciarSesion = findViewById(R.id.btnIniciaSesion);

        btnIniciarSesion.setOnClickListener(v -> {
            String strCorreo = etUsuario.toString().trim();
            String strClave = etClave.toString().trim();

            if (strCorreo.isBlank() || strClave.isBlank()){
                String mensaje = "Error, hay campos vacíos";
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            } else {
                for ( Usuario usuario : ListaUsuarios.listaUsuarios) {
                    if(usuario.getCorreo().equals(strCorreo)) {
                        if (usuario.claveCorrecta(strClave)){
                            String mensaje = "Acceso correcto";
                            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(IniciarSesion.this, PaginaInicio.class);
                            intent.putExtra("nombre", usuario.getNombre());
                            startActivity(intent);
                        } else {
                            break;
                        }
                    }
                }
                String mensaje = "Usuario o clave incorrecta";
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }
}