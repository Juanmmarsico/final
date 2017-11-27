package main.gui;


import main.Manager;
import main.modelsAndControllers.common.Paso;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajo;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajoDetalle;
import main.modelsAndControllers.supervisor.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class AMOrdenDeTrabajo extends JFrame{
    private static Calendar fechaDeAlta = Calendar.getInstance();
    private JTextField fechaAltaField,diaEstimacion,mesEstimacion,anioEstimacion,cantidad;
    private Manager m = Manager.getInstanced();
    private JTextField id;
    private JTable pasosOperarios;
    private JTextArea comentario;
    private JCheckBox isUrgente;
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MMM/yyyy");
    private JPanel components;
    DefaultTableModel tableModel;
    List<TableCellEditor> editorList = new ArrayList<TableCellEditor>();

    ArrayList<Paso> pasoss = new ArrayList<>();
    ArrayList<Integer> operariosString=new ArrayList<>();


    public AMOrdenDeTrabajo(Manager m,Producto producto) {
        this.m = m;
        fechaAltaField = new JTextField(sdf.format(fechaDeAlta.getTime()));

        add(buildTable(producto));
        add(buildAllComponentsPanel());

        setVisible(true);
    }

    public AMOrdenDeTrabajo(Manager m, OrdenDeTrabajo o) {
        this.m = m;
        setParameters(o.getFechaDeAlta(),o.getId(),o.getPasos(),o.getCantidad(),o.getEstimacion(),o.getComentario(),o.isUrgente());

        add(buildTable(o.getPasos()));
        add(buildAllComponentsPanel());

        setVisible(true);
    }

    private JPanel buildAllComponentsPanel() {
        components = new JPanel();

        components.add(fechaAltaField);
        components.add(id);
        components.add(diaEstimacion);
        components.add(mesEstimacion);
        components.add(anioEstimacion);
        components.add(comentario);
        components.add(isUrgente);
        components.add(pasosOperarios);

        return components;
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

    public JScrollPane buildTable(){
        pasosOperarios = new JTable(tableModel){
            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                return editorList.get(row);
            }
        };

        JScrollPane pane = new JScrollPane(pasosOperarios);

        return pane;
    }

    public JScrollPane buildTable(ArrayList<OrdenDeTrabajoDetalle> detalles){

        tableModelGenerator(detalles);
      JScrollPane pane =  buildTable();

        return pane;
    }
    public JScrollPane buildTable(Producto p){
        tableModelGenerator(p);
        JScrollPane pane =buildTable();

        return pane;
    }

    private void setParameters(Calendar fechaDeAlta, String id, ArrayList<OrdenDeTrabajoDetalle> detalles, int cantidad, Calendar estimacion, String comentario, boolean isUrgente) {
        fechaAltaField = new JTextField(sdf.format(fechaDeAlta));
        fechaAltaField.setEditable(false);
        fechaAltaField.setColumns(10);

        anioEstimacion = new JTextField(estimacion.get(Calendar.YEAR));
        mesEstimacion = new JTextField(estimacion.get(Calendar.MONTH));
        diaEstimacion= new JTextField(estimacion.get(Calendar.DATE));
        anioEstimacion.setColumns(4);
        mesEstimacion.setColumns(2);
        diaEstimacion.setColumns(2);

        mesEstimacion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!(e.getKeyChar() =='0' || e.getKeyChar() =='1' || e.getKeyChar() =='2' || e.getKeyChar() =='3' || e.getKeyChar() =='4' || e.getKeyChar() =='5' || e.getKeyChar() =='6' || e.getKeyChar() =='7' || e.getKeyChar() =='8' || e.getKeyChar() =='9' || e.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
                    e.consume();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                int numero;
                if (mesEstimacion.getText().equals("")){
                    numero=0;
                }else {
                    numero = (Integer.parseInt(mesEstimacion.getText()) * 10 + Character.getNumericValue(e.getKeyChar()));
                }
                if (numero>12){
                    e.consume();
                }
            }
        });

        anioEstimacion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!(e.getKeyChar() =='0' || e.getKeyChar() =='1' || e.getKeyChar() =='2' || e.getKeyChar() =='3' || e.getKeyChar() =='4' || e.getKeyChar() =='5' || e.getKeyChar() =='6' || e.getKeyChar() =='7' || e.getKeyChar() =='8' || e.getKeyChar() =='9' || e.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
                    e.consume();
                }
                if (mesEstimacion.getText().equals("")){
                    diaEstimacion.setEditable(false);
                }else{
                    diaEstimacion.setEditable(true);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                int numero;
                if (anioEstimacion.getText().equals("")){
                    numero=0;
                }else {
                    numero = (Integer.parseInt(anioEstimacion.getText()) * 10 + Character.getNumericValue(e.getKeyChar()));
                }
                if (numero>(Calendar.getInstance().get(Calendar.YEAR)+40)){
                    e.consume();
                }
            }
        });

        diaEstimacion.setEditable(false);

        diaEstimacion.addKeyListener(new KeyAdapter() {
            boolean mesTreintaYUno=mesEstimacion.getText()=="1"&&mesEstimacion.getText()=="3"&&mesEstimacion.getText()=="5"&&mesEstimacion.getText()=="7"&&mesEstimacion.getText()=="8"&&mesEstimacion.getText()=="10"&&mesEstimacion.getText()=="12";
            boolean mesTreinta=mesEstimacion.getText()=="4"&&mesEstimacion.getText()=="6"&&mesEstimacion.getText()=="9"&&mesEstimacion.getText()=="11";

            @Override
            public void keyPressed(KeyEvent e) {
                if (!(e.getKeyChar() =='0' || e.getKeyChar() =='1' || e.getKeyChar() =='2' || e.getKeyChar() =='3' || e.getKeyChar() =='4' || e.getKeyChar() =='5' || e.getKeyChar() =='6' || e.getKeyChar() =='7' || e.getKeyChar() =='8' || e.getKeyChar() =='9' || e.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
                    e.consume();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                int numero;
                if (diaEstimacion.getText().equals("")){
                    numero=0;
                }else {
                    numero = (Integer.parseInt(diaEstimacion.getText()) * 10 + Character.getNumericValue(e.getKeyChar()));
                }
                if (mesTreintaYUno){
                    if (numero>31){
                        e.consume();
                    }
                }else{
                    if (mesTreinta){
                        if (numero>30){
                            e.consume();
                        }
                    }else{
                        if (numero>28){
                            e.consume();
                        }
                    }
                }
            }

        });


        this.id = new JTextField(id);
        this.id.setEditable(false);

        for (OrdenDeTrabajoDetalle o: detalles){
            pasoss.add(o.getPaso());
            operariosString.add(o.getOperario());
        }
        this.cantidad = new JTextField(cantidad);
        this.comentario = new JTextArea(comentario);
        this.isUrgente = new JCheckBox("esUrgente",isUrgente);

    }
}
