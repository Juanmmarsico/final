package main.gui;


import main.Manager;
import main.modelsAndControllers.common.Paso;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajo;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajoDetalle;
import main.modelsAndControllers.supervisor.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class AMOrdenDeTrabajo extends JFrame{
    private static Calendar fechaDeAlta = Calendar.getInstance();
    private JTextField fechaAltaField;
    private Manager m = Manager.getInstanced();
    private JTextArea id;
    private JTable pasosOperarios;
    private int cantidad;
    private JComboBox diaEstimacion,mesEstimacion,anioEstimacion,operarios;
    private JTextArea comentario;
    private JCheckBox isUrgente;
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MMM/yyyy");
    private JPanel components;
    DefaultTableModel tableModel;
    List<TableCellEditor> editorList = new ArrayList<TableCellEditor>();

    ArrayList<Paso> pasoss = new ArrayList<>();
    ArrayList<String> operariosString=new ArrayList<>();


    public AMOrdenDeTrabajo(Manager m,Producto producto) {
        this.m = m;
        fechaAltaField = new JTextField(sdf.format(fechaDeAlta.getTime()));

        this.add(buildAllComponentsPanel());
        this.add(buildTable(producto));

        this.setVisible(true);
    }

    public AMOrdenDeTrabajo(Manager m, OrdenDeTrabajo o) {
        this.m = m;
        setParameters(o.getFechaDeAlta(),o.getId(),o.getPasos(),o.getCantidad(),o.getEstimacion(),o.getComentario(),o.isUrgente());

        this.add(buildAllComponentsPanel());
        this.add(buildTable(o.getPasos()));

        this.setVisible(true);
    }

    private JPanel buildAllComponentsPanel() {
        components = new JPanel();

        components.add(fechaAltaField);
        components.add(id);

        return null;
    }
public DefaultTableModel tableModelGenerator(Producto producto){
    ArrayList<String> operarioss = m.getSupervisorDAO().getOperariosParaAsignacion();
    String [] o= new String[operarioss.size()];
    for (int i= 0 ; i<operarioss.size();i++){
        o[i] = operarioss.get(i);
    }
    tableModel = new DefaultTableModel();

    Object [][] pasosOperarios = new Object[producto.getPasos().size()][2];

    for (int i= 0; i<producto.getPasos().size();i++){
        pasosOperarios[i][0] =  producto.getPasos().get(i);
        JComboBox<String> op = new JComboBox<String>(o);
        DefaultCellEditor dfc = new DefaultCellEditor(op);
        editorList.add(dfc);
        pasosOperarios[i][1]= op.getItemAt(0);
    }

    tableModel.setDataVector(pasosOperarios,new Object[]{"paso","Operario"});
    return tableModel;
}

public DefaultTableModel tableModelGenerator(ArrayList<OrdenDeTrabajoDetalle> detalles){
    ArrayList<String> operarioss = m.getSupervisorDAO().getOperariosParaAsignacion();
    String [] o= new String[operarioss.size()];
    for (int i= 0 ; i<operarioss.size();i++){
        o[i] = operarioss.get(i);
    }

    tableModel = new DefaultTableModel();


    Object[][] pasosOperarios = new Object[detalles.size()][2];

    for (int i= 0; i<detalles.size();i++){
        pasosOperarios [i][0] = detalles.get(i).getPaso();
        int posOperario=0;
        if (detalles.get(i).getOperario()>=0){
            for (int j=0;j<operarioss.size();j++){
                if (o.equals(operarioss.get(j))){
                    posOperario=j;
                }
            }
        }
        JComboBox op = new JComboBox(o);
        op.setSelectedIndex(posOperario);
        DefaultCellEditor dce= new DefaultCellEditor(op);
        editorList.add(dce);
        pasosOperarios[i][1]= op.getItemAt(posOperario);
    }
    tableModel.setDataVector(pasosOperarios,new Object[]{"paso","Operario"});

    return tableModel;
}

    public JTable buildTable(){
        pasosOperarios = new JTable(tableModel){
            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                return editorList.get(row);
            }
        };

        JScrollPane pane = new JScrollPane(pasosOperarios);

        return pasosOperarios;
    }

    public JTable buildTable(ArrayList<OrdenDeTrabajoDetalle> detalles){

        tableModelGenerator(detalles);
        buildTable();

        return pasosOperarios;
    }
    public JTable buildTable(Producto p){
        tableModelGenerator(p);
        buildTable();

        return pasosOperarios;
    }

    private void setParameters(Calendar fechaDeAlta, String id, ArrayList<OrdenDeTrabajoDetalle> detalles, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        int diaFechaAlta = fechaDeAlta.get(Calendar.DATE);
        int mesFechaAlta = fechaDeAlta.get(Calendar.MONTH);
        int anioFechaAlta = fechaDeAlta.get(Calendar.YEAR);

        this.id = new JTextArea(id);
        this.id.setEditable(false);
    }
}
