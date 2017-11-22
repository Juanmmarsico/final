package main.operario.controller;


import main.supervisor.controllers.Manager;
import main.supervisor.gui.Formulario;

/**
 * Created by juanmariamarsicovetere on 17/11/2017.
 */
public class ExcepcionPropia extends Exception {
    private Manager manager = Manager.getInstanced();
    public ExcepcionPropia(int codigo){
        new Formulario();
    }
}
