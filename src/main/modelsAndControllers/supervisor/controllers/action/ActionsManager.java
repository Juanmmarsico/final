package main.modelsAndControllers.supervisor.controllers.action;

import main.Manager;

import javax.swing.*;

/**
 * Created by juanmariamarsicovetere on 26/11/2017.
 */
public abstract class ActionsManager extends AbstractAction {

    Manager manager = Manager.getInstanced();

    public ActionsManager(Manager manager){
        this.manager= manager;
    }
}
