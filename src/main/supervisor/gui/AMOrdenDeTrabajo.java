package main.supervisor.gui;


import main.Manager;
import main.supervisor.model.OrdenDeTrabajoDetalle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class AMOrdenDeTrabajo {
    private static Calendar fechaDeAlta = Calendar.getInstance();
    Manager m = Manager.getInstanced();
    private JTextArea id;
    private ArrayList<OrdenDeTrabajoDetalle> pasos = new ArrayList<OrdenDeTrabajoDetalle>();
    private int cantidad;
    private Calendar estimacion;
    private String comentario;
    private boolean isUrgente;
}
