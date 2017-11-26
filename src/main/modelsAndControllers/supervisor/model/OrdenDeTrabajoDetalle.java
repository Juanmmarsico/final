package main.modelsAndControllers.supervisor.model;

import main.modelsAndControllers.common.Paso;

/**
 * Created by juanmariamarsicovetere on 16/11/2017.
 */
public class OrdenDeTrabajoDetalle {
    private Paso paso;
    private int operario;
    private int estado = -1;

    public OrdenDeTrabajoDetalle(int estado, int operario, Paso paso) {
        this.paso = paso;
        this.operario= operario;
        this.estado= estado;
    }
    public OrdenDeTrabajoDetalle(int operario, Paso paso) {
        this.paso = paso;
        this.operario= operario;
    }

    public OrdenDeTrabajoDetalle(Paso paso) {
        this.paso= paso;
    }
    public OrdenDeTrabajoDetalle() {
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public int getOperario() {
        return operario;
    }

    public void setOperario(int operario) {
        this.operario = operario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
