package main.modelsAndControllers.supervisor.model;


import main.modelsAndControllers.common.Paso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class Supervisor extends Persona{

    private String pass="letras";
    private ArrayList<OrdenDeTrabajo> ordenDeTrabajos= new ArrayList<OrdenDeTrabajo>();
    private Map<String,Integer> posicionOrdenesDeTrabajo = new TreeMap<String ,Integer>();

    public Supervisor(String nombre, String apellido, String dni, String pass, ArrayList<OrdenDeTrabajo> ordenDeTrabajos) {
        super(nombre, apellido, dni);
        this.pass = pass;
        this.ordenDeTrabajos = ordenDeTrabajos;
        for(int i = 0; i<ordenDeTrabajos.size();i++){
            posicionOrdenesDeTrabajo.put(ordenDeTrabajos.get(i).getId(),i);
        }
    }

    public Supervisor(String nombre, String apellido, String dni, ArrayList<OrdenDeTrabajo> ordenDeTrabajos) {
        super(nombre, apellido, dni);
        this.ordenDeTrabajos = ordenDeTrabajos;
        for(int i = 0; i<ordenDeTrabajos.size();i++){
            posicionOrdenesDeTrabajo.put(ordenDeTrabajos.get(i).getId(),i);
        }
    }

    public Supervisor(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public Supervisor(){}

    public OrdenDeTrabajo crearOrdenDeTrabajo(int [] operarios, ArrayList<Paso> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente){
       ArrayList<OrdenDeTrabajoDetalle> pasoss= new ArrayList<OrdenDeTrabajoDetalle>();

       for (int i = 0; i<pasos.size();i++) {
            if (operarios[i]>=0){
                pasoss.add(new OrdenDeTrabajoDetalle(operarios[i],pasos.get(i)));
            }else{
                pasoss.add(new OrdenDeTrabajoDetalle(pasos.get(i)));
            }
        }
        OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo(Calendar.getInstance(),pasoss, cantidad, estimacion,  comentario,  isUrgente);
        ordenDeTrabajos.add(ordenDeTrabajo);
        posicionOrdenesDeTrabajo.put(ordenDeTrabajo.getId(),ordenDeTrabajos.indexOf(ordenDeTrabajo));
        return ordenDeTrabajo;
    }

    public void eliminarOrdenDeTrabajo(String id){
        ordenDeTrabajos.remove(posicionOrdenesDeTrabajo.get(id));
    }

    public void modificarPasosDeOrdenDeTrabajo(String id,ArrayList<OrdenDeTrabajoDetalle> ordenDeTrabajoDetalles){
        ordenDeTrabajos.get(posicionOrdenesDeTrabajo.get(id)).setPasos(ordenDeTrabajoDetalles);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ArrayList<OrdenDeTrabajo> getOrdenDeTrabajos() {
        return ordenDeTrabajos;
    }

    public void setOrdenDeTrabajos(ArrayList<OrdenDeTrabajo> ordenDeTrabajos) {
        this.ordenDeTrabajos = ordenDeTrabajos;
    }

    public Map<String, Integer> getPosicionOrdenesDeTrabajo() {
        return posicionOrdenesDeTrabajo;
    }

    public void setPosicionOrdenesDeTrabajo(Map<String, Integer> posicionOrdenesDeTrabajo) {
        this.posicionOrdenesDeTrabajo = posicionOrdenesDeTrabajo;
    }

    @Override
    public boolean logIn(String pass) {
        return pass.equals(this.pass);
    }
}
