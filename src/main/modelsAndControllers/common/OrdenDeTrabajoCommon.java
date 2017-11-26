package main.modelsAndControllers.common;

import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 25/11/2017.
 */
public abstract class OrdenDeTrabajoCommon  {

    protected String id;
    protected Calendar fechaDeAlta;
    protected int cantidad;
    protected Calendar estimacion;
    protected String comentario;
    protected boolean isUrgente;

    public OrdenDeTrabajoCommon() {
    }

    public OrdenDeTrabajoCommon(String id, Calendar fechaDeAlta,  int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        this.id = id;
        this.fechaDeAlta = fechaDeAlta;
        this.cantidad = cantidad;
        this.estimacion = estimacion;
        this.comentario = comentario;
        this.isUrgente = isUrgente;
    }
    public OrdenDeTrabajoCommon(Calendar fechaDeAlta,  int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        this.id = id;
        this.fechaDeAlta = fechaDeAlta;
        this.cantidad = cantidad;
        this.estimacion = estimacion;
        this.comentario = comentario;
        this.isUrgente = isUrgente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Calendar getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Calendar fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Calendar getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(Calendar estimacion) {
        this.estimacion = estimacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isUrgente() {
        return isUrgente;
    }

    public void setUrgente(boolean urgente) {
        isUrgente = urgente;
    }


}
