package main.supervisor.model;


import main.operario.controller.ExcepcionPropia;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class MateriaPrima {
    private int codigo;

    public MateriaPrima() {

    }

    public MateriaPrima(int codigo) {

        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
