package main.operario.model;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Paso {
    private MateriaPrima materiaPrima;
    private String detalle;
    private int empezada;// <0 NO EMPEZADA, =0 EMPEZADA, >0 Finalizada


    public Paso() {
    }

    public Paso(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
        this.empezada = -1;
    }

    public Paso(MateriaPrima materiaPrima, String fecha) {
        this.materiaPrima = materiaPrima;
        this.detalle = fecha;
        this.empezada = -1;
    }

    public Paso(MateriaPrima materiaPrima, String detalle, int empezada) {
        this.materiaPrima = materiaPrima;
        this.detalle = detalle;
        this.empezada = empezada;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getEmpezada() {
        return empezada;
    }

    public void setEmpezada(int empezada) {
        this.empezada = empezada;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setFecha(String  fecha) {
        this.detalle = detalle;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

}
