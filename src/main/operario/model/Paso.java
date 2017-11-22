package main.operario.model;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Paso {
    private MateriaPrima materiaPrima;
    private String detalle;

    public Paso() {
    }

    public Paso(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Paso(MateriaPrima materiaPrima, String fecha) {
        this.materiaPrima = materiaPrima;
        this.detalle = fecha;
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
