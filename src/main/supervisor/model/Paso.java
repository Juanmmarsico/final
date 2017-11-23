package main.supervisor.model;


/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Paso {
    private MateriaPrima materiaPrima;
    private int requerido;
    private String detalle;

    public Paso() {
    }

    public Paso(MateriaPrima materiaPrima, int requerido, String detalle) {
        this.materiaPrima = materiaPrima;
        this.requerido = requerido;
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public int getRequerido() {
        return requerido;
    }

    public void setRequerido(int requerido) {
        this.requerido = requerido;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
