package main.gui;


import main.Manager;
import main.modelsAndControllers.common.Paso;
import main.modelsAndControllers.supervisor.controllers.action.SaveActionListener;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajo;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajoDetalle;
import main.modelsAndControllers.supervisor.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    boolean isNew=false;

    ArrayList<Paso> pasoss = new ArrayList<>();
    ArrayList<Integer> operariosString=new ArrayList<>();
    ArrayList<Integer> operarioPasos=new ArrayList<>();


    JButton guardar,cancelar;


    public AMOrdenDeTrabajo(Manager m,Producto producto) {
        this.m = m;
        fechaAltaField = new JTextField(sdf.format(fechaDeAlta.getTime()));
        fechaAltaField.setEditable(false);

        this.id = new JTextField("0");
        this.id.setEditable(false);
        setEstimaciones();

        for (Paso o: producto.getPasos()){
            pasoss.add(o);
            operariosString.add(-1);
        }
        this.cantidad = new JTextField("0");
        cantidad.setColumns(4);
        this.comentario = new JTextArea("");
        this.isUrgente = new JCheckBox("esUrgente",false);

        add(buildTable(producto));


        add(buildAllComponentsPanel());
        isNew = true;

        guardar = new JButton("Guardar");
        guardar.addActionListener(new SaveActionListener(m,this));

        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setMinimumSize(new Dimension(800, 800));
        setVisible(true);
    }

    public AMOrdenDeTrabajo(Manager m, OrdenDeTrabajo o) {
        this.m = m;
        setParameters(o.getFechaDeAlta(),o.getId(),o.getPasos(),o.getCantidad(),o.getEstimacion(),o.getComentario(),o.isUrgente());

        add(buildTable(o.getPasos()));
        add(buildAllComponentsPanel());


        setMinimumSize(new Dimension(800, 800));
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
    operarioPasos= new ArrayList<>();
    for(int i=0;i<pasoss.size();i++){
        operarioPasos.add(-1);
    }

    for (int i= 0; i<producto.getPasos().size();i++){
        pasosOperarios[i][0] =  producto.getPasos().get(i);
        JComboBox<String> op = new JComboBox<String>(o);
        op.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=getPasosOperarios().getSelectedRow();
                int coloumn = 1;
                pasoss.set(row,(Paso)getPasosOperarios().getValueAt(row,coloumn));
                operarioPasos.set(row,(Integer)op.getSelectedItem());

                System.out.println(operarioPasos.get(row));
                System.out.println(pasoss.get(row));


                System.out.println( getPasosOperarios().getSelectedRow());
                System.out.println(op.getSelectedIndex());
            }
        });
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
                if (column==1) {
                    return editorList.get(row);
                }else
                    return super.getCellEditor(row,column);
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
        setEstimaciones();
        anioEstimacion.setText(""+estimacion.get(Calendar.YEAR));
        mesEstimacion.setText(""+estimacion.get(Calendar.MONTH));
        diaEstimacion.setText(""+estimacion.get(Calendar.DATE));
        anioEstimacion.setColumns(4);
        mesEstimacion.setColumns(2);
        diaEstimacion.setColumns(2);



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

    private void setEstimaciones(){
        anioEstimacion = new JTextField(""+Calendar.getInstance().get(Calendar.YEAR));
        mesEstimacion = new JTextField(""+Calendar.getInstance().get(Calendar.MONTH));
        diaEstimacion= new JTextField(""+Calendar.getInstance().get(Calendar.DATE));
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
                String e3 = mesEstimacion.getText();
                if (!mesEstimacion.getText().equals("")){
                if (Integer.parseInt(mesEstimacion.getText())>0){
                    diaEstimacion.setEditable(true);
                }else{
                    diaEstimacion.setEditable(false);
                }
                }else{
                    diaEstimacion.setEditable(false);
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

            @Override
            public void keyPressed(KeyEvent e) {
                if (!(e.getKeyChar() =='0' || e.getKeyChar() =='1' || e.getKeyChar() =='2' || e.getKeyChar() =='3' || e.getKeyChar() =='4' || e.getKeyChar() =='5' || e.getKeyChar() =='6' || e.getKeyChar() =='7' || e.getKeyChar() =='8' || e.getKeyChar() =='9' || e.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
                    e.consume();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                boolean mesTreintaYUno=mesEstimacion.getText().equals("1") || mesEstimacion.getText().equals("3") || mesEstimacion.getText().equals("5") || mesEstimacion.getText().equals("7") || mesEstimacion.getText().equals("8") || mesEstimacion.getText().equals("10") || mesEstimacion.getText().equals("12");
                boolean mesTreinta=mesEstimacion.getText().equals("4") || mesEstimacion.getText().equals("6") || mesEstimacion.getText().equals("9") || mesEstimacion.getText().equals("11");
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

    }

    public static Calendar getFechaDeAlta() {
        return fechaDeAlta;
    }

    public static void setFechaDeAlta(Calendar fechaDeAlta) {
        AMOrdenDeTrabajo.fechaDeAlta = fechaDeAlta;
    }

    public JTextField getFechaAltaField() {
        return fechaAltaField;
    }

    public void setFechaAltaField(JTextField fechaAltaField) {
        this.fechaAltaField = fechaAltaField;
    }

    public JTextField getDiaEstimacion() {
        return diaEstimacion;
    }

    public void setDiaEstimacion(JTextField diaEstimacion) {
        this.diaEstimacion = diaEstimacion;
    }

    public JTextField getMesEstimacion() {
        return mesEstimacion;
    }

    public void setMesEstimacion(JTextField mesEstimacion) {
        this.mesEstimacion = mesEstimacion;
    }

    public JTextField getAnioEstimacion() {
        return anioEstimacion;
    }

    public void setAnioEstimacion(JTextField anioEstimacion) {
        this.anioEstimacion = anioEstimacion;
    }

    public JTextField getCantidad() {
        return cantidad;
    }

    public void setCantidad(JTextField cantidad) {
        this.cantidad = cantidad;
    }

    public JTextField getId() {
        return id;
    }

    public void setId(JTextField id) {
        this.id = id;
    }

    public JTable getPasosOperarios() {
        return pasosOperarios;
    }

    public void setPasosOperarios(JTable pasosOperarios) {
        this.pasosOperarios = pasosOperarios;
    }

    public JTextArea getComentario() {
        return comentario;
    }

    public void setComentario(JTextArea comentario) {
        this.comentario = comentario;
    }

    public JCheckBox getIsUrgente() {
        return isUrgente;
    }

    public void setIsUrgente(JCheckBox isUrgente) {
        this.isUrgente = isUrgente;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public boolean isNew() {
        return isNew;
    }

    public ArrayList<Integer> getOperarioPasos() {
        return operarioPasos;
    }

    public void setOperarioPasos(ArrayList<Integer> operarioPasos) {
        this.operarioPasos = operarioPasos;
    }
}
