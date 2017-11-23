package main.supervisor.gui;


import main.Manager;
import main.supervisor.model.OrdenDeTrabajo;
import main.supervisor.model.Supervisor;

import javax.swing.*;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class SupervisorPanel extends JPanel {
   Supervisor supervisor;
   private JButton ingreso,cerrar,detalles;
   private JList<OrdenDeTrabajo> ordenDeTrabajo;
   private Manager m = Manager.getInstanced();

    public SupervisorPanel(Supervisor supervisor) {
        this.supervisor = supervisor;
        buildPanel();
    }
    public void buildPanel(){


    }
}
