package main.modelsAndControllers.common;

import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Paso {
    private MateriaPrima materiaPrima;
    private String detalle;
    private int empezada;// <0 NO EMPEZADA, =0 EMPEZADA, >0 Finalizada
    private Calendar fechaFin;



    public Paso() {
    }

    public Paso(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
        this.empezada = -1;
    }

    public Paso(MateriaPrima materiaPrima, String detalle) {
        this.materiaPrima = materiaPrima;
        this.detalle = detalle;
        this.empezada = -1;
    }

    public Paso(MateriaPrima materiaPrima, String detalle, int empezada) {
        this.materiaPrima = materiaPrima;
        this.detalle = detalle;
        this.empezada = empezada;
    }

    public Paso(MateriaPrima materiaPrima, String detalle, int empezada, Calendar fechaFin) {
        this.materiaPrima = materiaPrima;
        this.detalle = detalle;
        this.empezada = empezada;
        this.fechaFin = fechaFin;
    }
    public Paso(MateriaPrima materiaPrima,  int empezada, String detalle,Calendar fechaFin) {
        this.materiaPrima = materiaPrima;
        this.detalle = detalle;
        this.empezada = empezada;
        this.fechaFin = fechaFin;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getEmpezada() {
        return empezada;
    }

    public void setEmpezada(int empezada) {
        this.empezada = empezada;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setFecha(String  fecha) {
        this.detalle = detalle;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return  detalle;
    }
}
