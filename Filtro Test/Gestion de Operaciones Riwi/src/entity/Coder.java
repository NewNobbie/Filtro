package entity;

public class Coder {
    private int id_coder;
    private String nombre;
    private String apellidos;
    private String documento;
    private int cohorte;
    private String cv;
    private String clan;

    public Coder() {
    }

    public Coder(int id_coder, String nombre, String apellidos, String documento, int cohorte, String cv, String clan) {
        this.id_coder = id_coder;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documento = documento;
        this.cohorte = cohorte;
        this.cv = cv;
        this.clan = clan;
    }

    public int getId_coder() {
        return id_coder;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public int getCohorte() {
        return cohorte;
    }

    public String getCv() {
        return cv;
    }

    public String getClan() {
        return clan;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setCohorte(int cohorte) {
        this.cohorte = cohorte;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    @Override
    public String toString() {
        return "Coder{" +
                "id_coder=" + id_coder +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", documento='" + documento + '\'' +
                ", cohorte=" + cohorte +
                ", cv='" + cv + '\'' +
                ", clan='" + clan + '\'' +
                '}';
    }
}
