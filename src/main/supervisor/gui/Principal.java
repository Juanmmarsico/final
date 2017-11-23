package main.supervisor.gui;


import main.operario.model.Operario;
import main.supervisor.controllers.Manager;
import main.supervisor.model.Supervisor;

import javax.swing.*;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Principal extends JFrame {
    private Supervisor supervisor;
    private Operario operario;
    private Manager m= Manager.getInstanced();
    private JButton consultar;

    public Principal(Operario operario){
            this.operario = operario;
            JPanel o = new OperarioPanel(operario);
            this.add(o);
        }
    public Principal(Supervisor supervisor){
        this.supervisor = supervisor;
        JPanel o = new SupervisorPanel(supervisor);
        this.add(o);
    }



}