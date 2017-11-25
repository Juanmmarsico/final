package main.supervisor.model;


import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Paso {
    private MateriaPrima materiaPrima;
    private int requerido;
    private String detalle;
    private Calendar fechaFin;


    public Paso() {
    }

    public Paso(MateriaPrima materiaPrima, int requerido, String detalle) {
        this.materiaPrima = materiaPrima;
        this.requerido = requerido;
        this.detalle = detalle;
    }

    public Paso(MateriaPrima materiaPrima, int requerido, String detalle, Calendar fechaFin) {
        this.materiaPrima = materiaPrima;
        this.requerido = requerido;
        this.detalle = detalle;
        this.fechaFin = fechaFin;
    }

    public String getDetalle() {
        return detalle;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public int getRequerido() {
        return requerido;
    }

    public void setRequerido(int requerido) {
        this.requerido = requerido;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }
}
