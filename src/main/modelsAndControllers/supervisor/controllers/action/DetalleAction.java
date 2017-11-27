package main.modelsAndControllers.supervisor.controllers.action;

import main.Manager;
import main.gui.AMOrdenDeTrabajo;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajo;

import java.awt.event.ActionEvent;

/**
 * Created by juanmariamarsicovetere on 27/11/2017.
 */
public class DetalleAction extends ActionsManager {

    public DetalleAction(Manager manager) {
        super(manager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OrdenDeTrabajo o =manager.getPrincipal().getSupervisorPanel().getOrdenDeTrabajo().getSelectedValue();
        new AMOrdenDeTrabajo(manager,o);
    }
}
