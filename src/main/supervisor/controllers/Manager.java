package main.supervisor.controllers;


import main.supervisor.gui.Principal;

/**
 * Created by juanmariamarsicovetere on 17/11/2017.
 */
public class Manager {
    private Principal principal;
    public static Manager getInstanced(){
        return new Manager();
    }
    public static String consultado = "";

    public Principal getPrincipal() {
        return principal;
    }

    public void setConsultado(String consultado) {
        this.consultado = consultado;
    }

    public String getConsultado() {
        String resultado= consultado;
        System.out.println(resultado);
        consultado="";
    return resultado;
    }
}
