package main.operario.model;


import main.operario.controller.ExcepcionPropia;

/**
 * Created by juanmariamarsicovetere on 12/11/2017.
 */
public class MateriaPrima {
    private int codigo;
    private int cantidadRestante;

    public MateriaPrima() {

    }

    public MateriaPrima(int codigo, int cantidadRestante) {

        this.codigo = codigo;
        this.cantidadRestante = cantidadRestante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidadRestante() {
        return cantidadRestante;
    }

    public void setCantidadRestante(int cantidadRestante) {
        this.cantidadRestante = cantidadRestante;
    }

    private void usarMateriaPrima(int cantidad){
        cantidadRestante=-cantidad;
    }
    public boolean hayCantidad(int cantidad) throws ExcepcionPropia {
        if (cantidadRestante>cantidad){
            usarMateriaPrima(cantidad);
            return true;
        }
        throw new ExcepcionPropia(codigo);
    }
}
