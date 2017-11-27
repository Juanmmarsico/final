package main.gui.supervisor;


import main.Manager;
import main.gui.AMOrdenDeTrabajo;
import main.gui.Principal;
import main.modelsAndControllers.supervisor.controllers.action.DetalleAction;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajo;
import main.modelsAndControllers.supervisor.model.Producto;
import main.modelsAndControllers.supervisor.model.Supervisor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class SupervisorPanel extends JPanel {
    Principal principal;
   Supervisor supervisor;
   private JButton ingreso,cerrar,detalles;
   private JList<OrdenDeTrabajo> ordenDeTrabajo;
   private JList<Producto> productoJList;
   private JMenuItem consultar;
   private JPanel botones,lista;
   private DefaultListModel ordenDeTrabajoModel;
   private JScrollPane scrollPane;

    private Manager m = Manager.getInstanced();

    public SupervisorPanel(Supervisor supervisor, Principal principal) {
        this.supervisor = supervisor;
        this.principal= principal;
        buildPanel();
        updateList();
    }
    public void buildPanel(){
        add(buildListaPanel());
        add(buildBotonesPanel());

        consultar= new JMenuItem("Consultar");
        consultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Consulta(m);
            }
        });

        principal.buildMenu();

    }

    private JPanel buildListaPanel() {
        lista = new JPanel();
        ordenDeTrabajoModel = new DefaultListModel<OrdenDeTrabajo>();
        ordenDeTrabajo = new JList<OrdenDeTrabajo>();
        scrollPane = new JScrollPane(ordenDeTrabajo);

        lista.add(scrollPane);

        return lista;
    }

    public void updateList(){
        ordenDeTrabajoModel.removeAllElements();
        for (OrdenDeTrabajo o : supervisor.getOrdenDeTrabajos()){
            ordenDeTrabajoModel.addElement(o);
        }

    }

    private JPanel buildBotonesPanel() {
        botones = new JPanel();

        ingreso = new JButton("IngresarOT");
        detalles= new JButton("MostrarOT");
        cerrar= new JButton("Cerrar");

        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principal.closeApp();
            }
        });
        detalles.addActionListener(new DetalleAction(m));
        ingreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AMOrdenDeTrabajo(m,productoJList.getSelectedValue());
            }
        });

        return botones;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public JList<OrdenDeTrabajo> getOrdenDeTrabajo() {
        return ordenDeTrabajo;
    }

    public void setOrdenDeTrabajo(JList<OrdenDeTrabajo> ordenDeTrabajo) {
        this.ordenDeTrabajo = ordenDeTrabajo;
    }

    public JList<Producto> getProductoJList() {
        return productoJList;
    }

    public void setProductoJList(JList<Producto> productoJList) {
        this.productoJList = productoJList;
    }
}
