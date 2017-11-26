package main.operario.model;

import main.common.OrdenDeTrabajoCommon;
import main.common.Paso;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class OrdenDeTrabajo extends OrdenDeTrabajoCommon {

    private ArrayList<Paso> pasos = new ArrayList<Paso>();
    private int cantidad;


    public OrdenDeTrabajo() {
    }

    public OrdenDeTrabajo(String id, Calendar fechaDeAlta, ArrayList<Paso> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        super(id,fechaDeAlta,cantidad,estimacion,comentario,isUrgente);
        this.pasos = pasos;
        this.cantidad = cantidad;

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
