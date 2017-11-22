package main.supervisor.model;


import main.operario.model.Operario;

import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Paso {
    private MateriaPrima materiaPrima;
    private Operario operario;
    private Calendar fecha;

    public Paso() {
    }

    public Paso(MateriaPrima materiaPrima, Operario operario) {
        this.materiaPrima = materiaPrima;
        this.operario = operario;
    }

    public Paso(MateriaPrima materiaPrima, Operario operario, Calendar fecha) {
        this.materiaPrima = materiaPrima;
        this.operario = operario;
        this.fecha = fecha;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }
}
