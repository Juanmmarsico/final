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
    private Calendar fechaFin;
    private int [] empezada;// <0 NO EMPEZADA, =0 EMPEZADA, >0 Finalizada

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
        empezada = new int [pasos.size()];
    }

    public OrdenDeTrabajo(String id, Calendar fechaDeAlta, ArrayList<Paso> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente, Calendar fechaFin, int empezada[]) {
        this.id = id;
        this.fechaDeAlta = fechaDeAlta;
        this.pasos = pasos;
        this.cantidad = cantidad;
        this.estimacion = estimacion;
        this.comentario = comentario;
        this.isUrgente = isUrgente;
        this.fechaFin = fechaFin;
        this.empezada = empezada;
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

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int[] getEmpezada() {
        return empezada;
    }

    public void setEmpezada(int empezada[]) {
        this.empezada = empezada;
    }

    public void modificarPaso(int paso){
        empezada[paso] = 0;
    }

    public void finalizarPaso(int paso){
        empezada[paso] = 1;

    }
}
