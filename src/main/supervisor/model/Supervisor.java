package main.supervisor.model;


import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class Supervisor extends Persona{

    private String pass="letras";
    private ArrayList<OrdenDeTrabajo> ordenDeTrabajos= new ArrayList<OrdenDeTrabajo>();

    public Supervisor(String nombre, String apellido, String dni, String pass, ArrayList<OrdenDeTrabajo> ordenDeTrabajos) {
        super(nombre, apellido, dni);
        this.pass = pass;
        this.ordenDeTrabajos = ordenDeTrabajos;
    }

    public Supervisor(String nombre, String apellido, String dni, ArrayList<OrdenDeTrabajo> ordenDeTrabajos) {
        super(nombre, apellido, dni);
        this.ordenDeTrabajos = ordenDeTrabajos;
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
        return ordenDeTrabajo;
    }

    @Override
    public boolean logIn(String pass) {
        return pass.equals(this.pass);
    }
}
