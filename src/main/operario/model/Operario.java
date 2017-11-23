package main.operario.model;

import main.supervisor.model.Persona;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class Operario extends Persona {

    private int legajo;
    private ArrayList<OrdenDeTrabajo> ordenes= new ArrayList<OrdenDeTrabajo>();

    public Operario(String nombre, String apellido, String dni, int legajo, ArrayList<OrdenDeTrabajo> ordenes) {
        super(nombre, apellido, dni);
        this.legajo = legajo;
        this.ordenes = ordenes;
    }

    public Operario() {
    }

    public Operario(int legajo) {
        this.legajo = legajo;
        this.ordenes = ordenes;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public ArrayList<OrdenDeTrabajo> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(ArrayList<OrdenDeTrabajo> ordenes) {
        this.ordenes = ordenes;
    }

    public boolean finalizarPasoEnOrdenDeTrabajo(String id,int p,Calendar fechaFin){
        for (OrdenDeTrabajo o: ordenes){
            if (o.getId().equals(id)){
                o.finalizarPaso(p);
                o.setFechaFin(fechaFin);
                return true;
            }
        }
        return false;
    }
    public boolean empezarPasoEnOrdeDeTrabajo(String id, int p){
        for (OrdenDeTrabajo o: ordenes){
            if (o.getId().equals(id)){
                o.modificarPaso(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean logIn(String pass) {
        return true;
    }
}
