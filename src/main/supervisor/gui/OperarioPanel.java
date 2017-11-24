package main.supervisor.gui;


import main.operario.model.Operario;

import javax.swing.*;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class OperarioPanel extends JPanel {

    Operario operario;

    public OperarioPanel(Operario operario) {
    this.operario= operario;
    }
}
