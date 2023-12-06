import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Usuario {

    private int ci;

    private String nombre;

    private String apellido;

    private String telefono;

    private String seccion;

    private String cargo;

    private String fechaIngreso;

    private int sueldo;

    public String ToString(){
        return this.ci + " " + this.nombre + " " + this.apellido + " " + this.telefono + " $" + this.sueldo;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public Usuario(int ci, String nombre, String apellido, String telefono, String seccion, String cargo, int sueldo) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.seccion = seccion;
        this.cargo = cargo;
        this.fechaIngreso = LocalDate.now().toString();
        this.sueldo = sueldo;
    }
}
