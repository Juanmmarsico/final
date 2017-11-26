package main.modelsAndControllers.supervisor.model;

import main.DAO.SupervisorDAO;
import main.modelsAndControllers.common.OrdenDeTrabajoCommon;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class OrdenDeTrabajo extends OrdenDeTrabajoCommon{
    private static Calendar year = Calendar.getInstance();
    private static int numero = 0;
    private ArrayList<OrdenDeTrabajoDetalle> pasos = new ArrayList<OrdenDeTrabajoDetalle>();
    private Calendar fechaFin;
    private boolean isDone=false;

    public OrdenDeTrabajo() {
    }

    public OrdenDeTrabajo(Calendar fechaDeAlta, ArrayList<OrdenDeTrabajoDetalle> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        super(fechaDeAlta,cantidad,estimacion,comentario,isUrgente);
        this.pasos = pasos;
        int i = SupervisorDAO.getLastId();
        numero = i;
        verifyCalendar();
        setId(generateID());
        numero++;
    }


    public OrdenDeTrabajo(String id, Calendar fechaDeAlta, ArrayList<OrdenDeTrabajoDetalle> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        super(id,fechaDeAlta,cantidad,estimacion,comentario,isUrgente);
        this.pasos = pasos;
       this.isDone = verifyIFIsDone();
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    private boolean verifyIFIsDone() {
        boolean d = true;
        for (OrdenDeTrabajoDetalle o:pasos) {
            if(o.getEstado()<=0){
                d= false;
            }
        }
        if (d){
            Calendar ff =Calendar.getInstance();
            ff.set(Calendar.YEAR,1900);
            for (OrdenDeTrabajoDetalle o:pasos) {
                if (o.getPaso().getFechaFin().after(ff)){
                    ff=o.getPaso().getFechaFin();
                }
            }
            fechaFin=ff;
        }
        return d;
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
                nextId = "000"+num+"/"+(""+year.get(Calendar.YEAR)).substring(2);
            }else{
                nextId = "00"+num+"/"+(""+year.get(Calendar.YEAR)).substring(2);

            }
        }else {
            if (num>0 && num<1000){
                nextId = "0"+num+"/"+(""+year.get(Calendar.YEAR)).substring(2);
            }else{
                nextId = ""+num+"/"+(""+year.get(Calendar.YEAR)).substring(2);
            }
        }
            return nextId;

    }

    public ArrayList<OrdenDeTrabajoDetalle> getPasos() {
        return pasos;
    }

    public void setPasos(ArrayList<OrdenDeTrabajoDetalle> pasos) {
        this.pasos = pasos;
    }


    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


}
