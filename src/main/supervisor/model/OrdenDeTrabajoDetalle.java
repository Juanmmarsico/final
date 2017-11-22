package main.supervisor.model;

/**
 * Created by juanmariamarsicovetere on 16/11/2017.
 */
public class OrdenDeTrabajoDetalle {
    private Paso paso;
    private int operario;

    public OrdenDeTrabajoDetalle(int operario,Paso paso) {
        this.paso = paso;
        this.operario= operario;
    }


    public OrdenDeTrabajoDetalle(int operario) {
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

}
