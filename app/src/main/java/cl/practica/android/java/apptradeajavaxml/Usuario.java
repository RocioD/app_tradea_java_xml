package cl.practica.android.java.apptradeajavaxml;

public class Usuario {

    private String nombre;
    private String correo;
    private String clave;

    public Usuario(String nombre, String correo, String clave){
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean claveCorrecta(String claveAVerificar){
        return this.clave.equals(claveAVerificar);
    }
}
