package main.gui.operario;


import main.Manager;
import main.gui.Principal;
import main.modelsAndControllers.operario.model.Operario;

import javax.swing.*;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class OperarioPanel extends JPanel {

    Principal principal;
    Operario operario;
    private Manager m= Manager.getInstanced();


    public OperarioPanel(Operario operario, Principal principal) {
    this.operario= operario;
    this.principal = principal;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }
}
