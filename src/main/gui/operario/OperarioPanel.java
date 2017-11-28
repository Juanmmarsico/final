package main.gui.operario;


import main.Manager;
import main.gui.Principal;
import main.modelsAndControllers.operario.model.Operario;
import main.modelsAndControllers.operario.model.OrdenDeTrabajo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class OperarioPanel extends JPanel {

    Principal principal;
    Operario operario;
    JPanel panel;
    JComboBox<main.modelsAndControllers.operario.model.OrdenDeTrabajo> ordenDeTrabajoJComboBox;
    JButton seleccionar,cerrar;


    private Manager m= Manager.getInstanced();


    public OperarioPanel(Operario operario, Principal principal) {
    this.operario= operario;
    this.principal = principal;
    add(buildPanel());
    }

    private JPanel buildPanel() {
    panel = new JPanel();
        OrdenDeTrabajo [] ordenDeTrabajos = new OrdenDeTrabajo[operario.getOrdenes().size()];
        for (int i = 0;i<ordenDeTrabajos.length;i++){
            ordenDeTrabajos[i]= operario.getOrdenes().get(i);
        }
        ordenDeTrabajoJComboBox = new JComboBox<>(ordenDeTrabajos);

        panel.add(ordenDeTrabajoJComboBox);

        seleccionar = new JButton("Seleccionar");
        seleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new OrdenDeTrabajoEditor((OrdenDeTrabajo) ordenDeTrabajoJComboBox.getSelectedItem(), principal.getM());
                }catch (NullPointerException e1){
                }
            }
        });

    return panel;
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
