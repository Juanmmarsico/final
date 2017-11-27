package main.gui.supervisor.models;

import main.Manager;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajoDetalle;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by juanmariamarsicovetere on 26/11/2017.
 */
public class OrdenesDeTrabajoTableModel extends AbstractTableModel {
    private List<OrdenDeTrabajoDetalle> list;
    Manager manager;
    String [] columnNames = {"Operario","Detalle","Estado","FechaFin","CodigoMateriaPrima","RestanteMateriaPrima"};

    public OrdenesDeTrabajoTableModel(List<OrdenDeTrabajoDetalle> list, Manager manager) {
        this.list = list;
        this.manager = manager;
    }
    public OrdenesDeTrabajoTableModel( Manager manager, List<OrdenDeTrabajoDetalle> list) {
        this.list = list;
        this.manager = manager;
    }

    @Override
    public int getRowCount() {
        try {
            return list.size();

        } catch (NullPointerException e) {
            // TODO: handle exception
            return 0;
        }    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        Object value = null;
        OrdenDeTrabajoDetalle detalle = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = detalle.getOperario();
                break;
            case 1:
                value= detalle.getPaso().getDetalle();
                break;
            case 2:
                value =  ((detalle.getPaso().getEmpezada()>0)?("Terminada"):(detalle.getPaso().getEmpezada()<0?"No Empezada":"Empezada"));
                break;
            case 3:
                value = sdf.format(detalle.getPaso().getFechaFin().getTime());
                break;
            case 4:
                value = detalle.getPaso().getMateriaPrima().getCodigo();
                break;
            case 5:
                value = detalle.getPaso().getMateriaPrima().getCantidadRestante();
                break;

        }
        return value;    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public OrdenDeTrabajoDetalle getOrdenDeTrabajoDetalleAt(int firstRow) {
        return list.get(firstRow);
    }
}
