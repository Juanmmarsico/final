package main.operario.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class OrdenDeTrabajo {
    private String id;
    private Calendar fechaDeAlta;
    private ArrayList<Paso> pasos = new ArrayList<Paso>();
    private int cantidad;
    private Calendar estimacion;
    private String comentario;
    private boolean isUrgente;

    public OrdenDeTrabajo() {
    }

    public OrdenDeTrabajo(String id, Calendar fechaDeAlta, ArrayList<Paso> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        this.id = id;
        this.fechaDeAlta = fechaDeAlta;
        this.pasos = pasos;
        this.cantidad = cantidad;
        this.estimacion = estimacion;
        this.comentario = comentario;
        this.isUrgente = isUrgente;
    }

    public OrdenDeTrabajo(String id, Calendar fechaDeAlta, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        this.id = id;
        this.fechaDeAlta = fechaDeAlta;
        this.pasos = pasos;
        this.cantidad = cantidad;
        this.estimacion = estimacion;
        this.comentario = comentario;
        this.isUrgente = isUrgente;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }


    public Calendar getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Calendar fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public ArrayList<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(ArrayList<Paso> pasos) {
        this.pasos = pasos;
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


    public void modificarPaso(String paso){
        for (Paso paso1: pasos) {
            if (paso1.getDetalle().equals(paso)){
                paso1.setEmpezada(0);
            }
        }
    }

    public void finalizarPaso(String paso){
        for (Paso paso1: pasos) {
            if (paso1.getDetalle().equals(paso)){
                paso1.setEmpezada(1);
            }
        }

    }
    public void agregarUnPaso(Paso paso) {
        pasos.add(paso);
    }
}
