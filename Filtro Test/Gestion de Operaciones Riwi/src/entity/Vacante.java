package entity;

public class Vacante {

    private int id_vacante;
    private String titulo;
    private String descripcion;
    private String duracion;
    private Boolean estado;
    private String tecnologia;
    private int empresa_id_fk;

    public Vacante() {
    }

    public Vacante(int id_vacante, String titulo, String descripcion, String duracion, Boolean estado, String tecnologia, int empresa_id_fk) {
        this.id_vacante = id_vacante;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
        this.empresa_id_fk = empresa_id_fk;
    }

    public int getId_vacante() {
        return id_vacante;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public int getEmpresa_id_fk() {
        return empresa_id_fk;
    }

    public void setId_vacante(int id_vacante) {
        this.id_vacante = id_vacante;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public void setEmpresa_id_fk(int empresa_id_fk) {
        this.empresa_id_fk = empresa_id_fk;
    }

    @Override
    public String toString() {
        return "Vacante{" +
                "id_vacante=" + id_vacante +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", estado=" + estado +
                ", tecnologia='" + tecnologia + '\'' +
                ", empresa_id_fk=" + empresa_id_fk +
                '}';
    }
}
