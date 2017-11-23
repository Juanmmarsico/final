package main.supervisor.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class OrdenDeTrabajo {
    private static Calendar year = Calendar.getInstance();
    private static int numero = 0;
    private String id;
    private Calendar fechaDeAlta;
    private ArrayList<OrdenDeTrabajoDetalle> pasos = new ArrayList<OrdenDeTrabajoDetalle>();
    private int cantidad;
    private Calendar estimacion;
    private String comentario;
    private boolean isUrgente;
    private boolean isDone=false;

    public OrdenDeTrabajo() {
    }

    public OrdenDeTrabajo(Calendar fechaDeAlta, ArrayList<OrdenDeTrabajoDetalle> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        this.fechaDeAlta = fechaDeAlta;
        this.pasos = pasos;
        this.cantidad = cantidad;
        this.estimacion = estimacion;
        this.comentario = comentario;
        this.isUrgente = isUrgente;
        verifyCalendar();
        generateID();
        numero++;
    }


    public OrdenDeTrabajo(String id, Calendar fechaDeAlta, ArrayList<OrdenDeTrabajoDetalle> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        this.id = id;
        this.fechaDeAlta = fechaDeAlta;
        this.pasos = pasos;
        this.cantidad = cantidad;
        this.estimacion = estimacion;
        this.comentario = comentario;
        this.isUrgente = isUrgente;
    }

    public static String showNextId(){
        OrdenDeTrabajo o = new OrdenDeTrabajo();
        int nextNum = numero+1;
        String nextId= o.generateNextId(nextNum);
        return nextId;
    }

    public static int getNumero() {
        return numero;
    }

    public static void setNumero(int numero) {
        OrdenDeTrabajo.numero = numero;
    }

    private void verifyCalendar() {
        if (year.get(Calendar.YEAR) != fechaDeAlta.get(Calendar.YEAR)){
            year.set(Calendar.YEAR,fechaDeAlta.get(Calendar.YEAR));
            numero=0;
        }
    }

    private String generateID() {
        id= generateNextId(numero);
    return id;
    }

    private String generateNextId(int num){
        String nextId ="";
        if (num>0 && num<100){
            if (num>0 && num<10){
                nextId = "000"+num+"/"+year.get(Calendar.YEAR);
            }else{
                nextId = "00"+num+"/"+year.get(Calendar.YEAR);

            }
        }else {
            if (num>0 && num<1000){
                nextId = "0"+num+"/"+year.get(Calendar.YEAR);
            }else{
                nextId = ""+num+"/"+year.get(Calendar.YEAR);
            }
        }
            return nextId;

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

    public ArrayList<OrdenDeTrabajoDetalle> getPasos() {
        return pasos;
    }

    public void setPasos(ArrayList<OrdenDeTrabajoDetalle> pasos) {
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

    public boolean isDone() {
        for (OrdenDeTrabajoDetalle o:pasos) {
//            o.getPaso().
        }
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


}
