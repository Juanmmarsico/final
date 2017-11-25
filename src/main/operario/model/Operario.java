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

    public Operario(String nombre, String apellido, String dni, int legajo) {
        super(nombre, apellido, dni);
        this.legajo = legajo;
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

    public boolean finalizarPasoEnOrdenDeTrabajo(String id,String p,Calendar fechaFin){
        for (OrdenDeTrabajo o: ordenes){
            if (o.getId().equals(id)){
                o.finalizarPaso(p);
                for (Paso paso: o.getPasos()) {
                    if (paso.getDetalle().equals(p)){
                        paso.setFechaFin(fechaFin);
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }
    public boolean empezarPasoEnOrdeDeTrabajo(String id, String p){
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

    public void agregarOrdenDeTrabajoUnSoloPaso(String idOrdenDeTrabajo, Calendar fechaDeAlta, Paso paso, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        int id = Integer.parseInt(idOrdenDeTrabajo.substring(0,5));
        OrdenDeTrabajo ordenDeTrabajo;
        if (ordenes.get(id)!=null){
            ordenDeTrabajo  = new OrdenDeTrabajo(idOrdenDeTrabajo,fechaDeAlta,cantidad,estimacion,comentario,isUrgente);
            ordenes.add(id,ordenDeTrabajo);

        }else{
            ordenDeTrabajo= ordenes.get(id);
        }

        ordenDeTrabajo.agregarUnPaso(paso);
    }
}
