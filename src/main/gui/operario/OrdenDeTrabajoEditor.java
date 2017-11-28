package main.gui.operario;

import main.Manager;
import main.modelsAndControllers.common.Paso;
import main.modelsAndControllers.operario.model.OrdenDeTrabajo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by juanmariamarsicovetere on 28/11/2017.
 */
public class OrdenDeTrabajoEditor extends JFrame {
    OrdenDeTrabajo ordenDeTrabajo;
    Manager manager;
    JPanel panel;

    private ArrayList<Paso> pasos = new ArrayList<Paso>();

    private JTextField fechaAltaField,estimacion,cantidad;
    private JTextField id;
    private JTable pasosOperarios;
    private JTextArea comentario;
    private JCheckBox isUrgente;
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MMM/yyyy");
    DefaultTableModel tableModel;
    java.util.List<TableCellEditor> editorList = new ArrayList<TableCellEditor>();
    java.util.List<TableCellEditor> editorList2 = new ArrayList<TableCellEditor>();


    JButton guardar,cancelar;


    public OrdenDeTrabajoEditor(OrdenDeTrabajo ordenDeTrabajo, Manager m) {
        this.ordenDeTrabajo=ordenDeTrabajo;
        this.manager= m;
        add(buildFrame());
        setMinimumSize(new Dimension(200,300));
        setVisible(true);
    }

    private JPanel buildFrame() {
        panel = new JPanel();

        id = new JTextField(ordenDeTrabajo.getId());
        id.setEditable(false);
        panel.add(id);

        fechaAltaField= new JTextField();
        fechaAltaField.setText(sdf.format(ordenDeTrabajo.getFechaDeAlta()));
        fechaAltaField.setEditable(false);
        panel.add(fechaAltaField);

        estimacion= new JTextField(sdf.format(ordenDeTrabajo.getEstimacion()));
        estimacion.setEditable(false);
        panel.add(estimacion);

        cantidad = new JTextField(ordenDeTrabajo.getCantidad());
        cantidad.setEditable(false);
        panel.add(cantidad);

        comentario = new JTextArea(ordenDeTrabajo.getComentario());
        comentario.setEditable(false);
        panel.add(comentario);

        isUrgente = new JCheckBox("Es urgente",ordenDeTrabajo.isUrgente());
        panel.add(isUrgente);

        panel.add(buildTable());

        guardar = new JButton("guardar");
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.getOperariosController().getOperario().modificarOrdenDeTrabajo(ordenDeTrabajo);
            }
        });
        panel.add(guardar);
        cancelar = new JButton("cancelar");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(cantidad);

        return panel;
    }

    private JPanel buildTable() {
    JPanel tablePanel = new JPanel();
    tableModel = new DefaultTableModel();
    Object[][] pasos = new Object[ordenDeTrabajo.getPasos().size()][5];
    for (int i =0; i<ordenDeTrabajo.getPasos().size();i++){
        pasos [i][0] = ordenDeTrabajo.getPasos().get(i).getDetalle();
        pasos [i][1] = ordenDeTrabajo.getPasos().get(i).getMateriaPrima().getCodigo();
        pasos [i][2] = ordenDeTrabajo.getPasos().get(i).getMateriaPrima().getCantidadRestante();

        JComboBox<Integer> cbx= new JComboBox<Integer>(new Integer[]{-1,0,1});
        if (ordenDeTrabajo.getPasos().get(i).getEmpezada()==-1){
            cbx.setSelectedIndex(0);
        }else{
            if (ordenDeTrabajo.getPasos().get(i).getEmpezada()==0){
                cbx.setSelectedIndex(1);

            }else{
                cbx.setSelectedIndex(2);
                cbx.setEditable(false);
            }
        }
        cbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = editorList.indexOf(cbx);
                ordenDeTrabajo.getPasos().get(row).setEmpezada((Integer)cbx.getSelectedItem());
            }
        });
        JTextField textField;
        if (ordenDeTrabajo.getPasos().get(i).getFechaFin().equals("")||ordenDeTrabajo.getPasos().get(i).getFechaFin()== null){
           textField = new JTextField();
        }else {
            textField = new JTextField(ordenDeTrabajo.getPasos().get(i).getFechaFin().toString());
        }
        pasos [i][3] = textField;
        pasos [i][4] = cbx;

        DefaultCellEditor dfc = new DefaultCellEditor(textField);
        DefaultCellEditor dfc2= new DefaultCellEditor(cbx);

        editorList.add(dfc);
        editorList2.add(dfc2);
    }

    tableModel.setDataVector(pasos,new Object[]{"detalle","materiaPrimaCodig","MateriaPrimaRestante","FechaFin","Empezado=0,terminada=1"});

    return tablePanel;
    }
}
