package main.modelsAndControllers.supervisor.controllers.controllers;

import main.modelsAndControllers.common.Paso;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajo;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajoDetalle;
import main.modelsAndControllers.supervisor.model.Supervisor;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class SupervisorController {
    private Supervisor supervisor;
    private PasosController pasosController;

    public SupervisorController(Supervisor supervisor) {
        this.supervisor = supervisor;
        pasosController = new PasosController();
    }

    public SupervisorController(Supervisor supervisor, PasosController pasosController) {
        this.supervisor = supervisor;
        this.pasosController = pasosController;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public PasosController getPasosController() {
        return pasosController;
    }

    public void setPasosController(PasosController pasosController) {
        this.pasosController = pasosController;
    }

    public OrdenDeTrabajo crearOrdenDeTrabajo(int [] operarios, ArrayList<Paso> pasos, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
    return supervisor.crearOrdenDeTrabajo(operarios,pasos,cantidad,estimacion,comentario,isUrgente);
    }
    public void eliminarOrdenDeTrabajo(String id) {
    supervisor.eliminarOrdenDeTrabajo(id);
    }

    public void modificarPasosDeOrdenDeTrabajo(String id,ArrayList<OrdenDeTrabajoDetalle> ordenDeTrabajoDetalles) {
    supervisor.modificarPasosDeOrdenDeTrabajo(id,ordenDeTrabajoDetalles);
    }

    }
