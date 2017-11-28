package main.modelsAndControllers.supervisor.controllers.action;

import main.Manager;
import main.gui.AMOrdenDeTrabajo;
import main.modelsAndControllers.common.Paso;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajo;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajoDetalle;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 26/11/2017.
 */
public class SaveActionListener extends ActionsManager {
AMOrdenDeTrabajo amOrdenDeTrabajo;

    public SaveActionListener(Manager manager) {
        super(manager);
    }

    public SaveActionListener(Manager manager, AMOrdenDeTrabajo amOrdenDeTrabajo) {
        super(manager);
        this.amOrdenDeTrabajo= amOrdenDeTrabajo;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String fechaAlta =amOrdenDeTrabajo.getFechaAltaField().getText();
        String diaEstimacion= amOrdenDeTrabajo.getDiaEstimacion().getText();
        String mesEstimacion = amOrdenDeTrabajo.getMesEstimacion().getText();
       String anioEstimacion = amOrdenDeTrabajo.getAnioEstimacion().getText();
        String id = amOrdenDeTrabajo.getId().getText();
        String comentario =amOrdenDeTrabajo.getComentario().getText();
        String cantidad = amOrdenDeTrabajo.getCantidad().getText();
       boolean isUrgente = amOrdenDeTrabajo.getIsUrgente().isEnabled();
    OrdenDeTrabajo o = null;

        ArrayList<Paso> ordenDeTrabajoDetalles = new ArrayList<Paso>();
        int [] operarios = new int[ordenDeTrabajoDetalles.size()];

        int a = amOrdenDeTrabajo.getPasosOperarios().getRowCount();
        for (int i = 0;i<a ; i++){
            Paso paso= (Paso)amOrdenDeTrabajo.getPasosOperarios().getValueAt(i,0);
            int operario= amOrdenDeTrabajo.getOperarioPasos().get(i);
            ordenDeTrabajoDetalles.add(paso);
            operarios[i] = operario;
        }
        if (amOrdenDeTrabajo.isNew()){
            Calendar estimacion = Calendar.getInstance();
            estimacion.set(Integer.parseInt(anioEstimacion),Integer.parseInt(mesEstimacion),Integer.parseInt(diaEstimacion));
          o =manager.getSupervisorController().crearOrdenDeTrabajo(
                    operarios,
                    ordenDeTrabajoDetalles,
                    Integer.parseInt(cantidad),
                    estimacion,
                    comentario,
                    isUrgente
                    );
           manager.getSupervisorDAO().cargarOrdenDeTrabajo(o);
        }else {
            ArrayList <OrdenDeTrabajoDetalle> or = new ArrayList<>();
            for (int i = 0;i<ordenDeTrabajoDetalles.size();i++){
                or.add(new OrdenDeTrabajoDetalle(-1,operarios[i],ordenDeTrabajoDetalles.get(i)));
            }
            manager.getSupervisorController().modificarPasosDeOrdenDeTrabajo(id,or);

        }
    }
}
