package main.modelsAndControllers.operario.controller;


import main.Manager;
import main.gui.Formulario;

/**
 * Created by juanmariamarsicovetere on 17/11/2017.
 */
public class ExcepcionPropia extends Exception {
    private Manager manager = Manager.getInstanced();

    public ExcepcionPropia(int codigo){
        new Formulario(codigo);
    }

    public ExcepcionPropia(String motivo, int codigo){
//        JOptionPane.showMessageDialog(Login.get);

        System.out.println(motivo+" " +codigo);
    }
    public ExcepcionPropia(String motivo, String codigo){
//        JOptionPane.showMessageDialog(Login.get);

        System.out.println(motivo+" " +codigo);
    }
}
