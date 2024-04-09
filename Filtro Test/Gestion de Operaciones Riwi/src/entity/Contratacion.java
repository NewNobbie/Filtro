package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Contratacion {
    private int id_contratacion;
    private LocalDate fecha_aplicacion;
    private boolean estado_contratacion;
    private float salario;
    private int vacante_id_fk;
    private int coder_id_fk;

    public Contratacion() {
    }

    public Contratacion(int id_contratacion, LocalDate fecha_aplicacion, boolean estado_contratacion, float salario, int vacante_id_fk, int coder_id_fk) {
        this.id_contratacion = id_contratacion;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado_contratacion = estado_contratacion;
        this.salario = salario;
        this.vacante_id_fk = vacante_id_fk;
        this.coder_id_fk = coder_id_fk;
    }

    public int getId_contratacion() {
        return id_contratacion;
    }

    public LocalDate getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public boolean getEstado_contratacion() {
        return estado_contratacion;
    }

    public float getSalario() {
        return salario;
    }

    public int getVacante_id_fk() {
        return vacante_id_fk;
    }

    public int getCoder_id_fk() {
        return coder_id_fk;
    }

    public void setId_contratacion(int id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public void setFecha_aplicacion(LocalDate fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public void setEstado_contratacion(boolean estado_contratacion) {
        this.estado_contratacion = estado_contratacion;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setVacante_id_fk(int vacante_id_fk) {
        this.vacante_id_fk = vacante_id_fk;
    }

    public void setCoder_id_fk(int coder_id_fk) {
        this.coder_id_fk = coder_id_fk;
    }

    @Override
    public String toString() {
        return "Contratacion{" +
                "id_contratacion=" + id_contratacion +
                ", fecha_aplicacion=" + fecha_aplicacion +
                ", estado_contratacion=" + estado_contratacion +
                ", salario=" + salario +
                ", vacante_id_fk=" + vacante_id_fk +
                ", coder_id_fk=" + coder_id_fk +
                '}';
    }
}
