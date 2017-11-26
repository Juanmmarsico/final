package main.modelsAndControllers.operario.controller;

import main.modelsAndControllers.operario.model.Operario;

import java.util.Calendar;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class  OperariosController {
    //TODO subir los cambios realizados por el operario a un Store procedure de dos tablas (Ordenes de trabajo, Ordenes de trabajo detalle)

    private Operario operario;

    public OperariosController(Operario operario) {
        this.operario = operario;
    }

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }
    public boolean finalizarPasoEnOrdenDeTrabajo(String id,String p,Calendar fechaFin) {
    return operario.finalizarPasoEnOrdenDeTrabajo(id,p,fechaFin);
    }
    public boolean empezarPasoEnOrdeDeTrabajo(String id, String p) {
    return operario.empezarPasoEnOrdeDeTrabajo(id,p);
    }
    }
