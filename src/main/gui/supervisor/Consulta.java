package main.gui.supervisor;

import main.Manager;
import main.gui.supervisor.models.*;
import main.modelsAndControllers.operario.controller.ExcepcionPropia;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajoDetalle;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class Consulta extends JFrame {

        private JTextField consulta;
        private JButton consultar;
        private  OrdenesDeTrabajoTableModel ordenesDeTrabajoTableModel;
        private JTable ordenesTable;
        private ArrayList<OrdenDeTrabajoDetalle> ordenesDeTrabajo = new ArrayList<OrdenDeTrabajoDetalle>();


        private boolean hasChange = false;

        private Manager manager;

        public Consulta(Manager manager) {
            // TODO Auto-generated constructor stub
            this.manager = manager;
            buildFrame();
            this.setVisible(true);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        private void buildFrame() {
            // TODO Auto-generated method stub

            consulta = new JTextField();

            JPanel tablas = new JPanel(new GridLayout());

            ordenesTable = new JTable(ordenesDeTrabajoTableModel);
            ordenesTable.setFillsViewportHeight(true);
            ordenesTable.setShowHorizontalLines(true);
            ordenesTable.setShowVerticalLines(false);

            JScrollPane ordenesScroll = new JScrollPane(ordenesTable);


            consultar = new JButton("Consultar");
            consultar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    try {
                        java.util.List<OrdenDeTrabajoDetalle> orden=manager.getSupervisorDAO().searchOrdenesDeTrabajo(manager.getSupervisorController().getSupervisor(),consulta.getText());
                        ordenesDeTrabajoTableModel = new OrdenesDeTrabajoTableModel(manager,orden);
                        ordenesDeTrabajoTableModel.addTableModelListener(new TableModelListener() {
                            @Override
                            public void tableChanged(TableModelEvent e) {
//						    changes.add(((Expense)(e.getSource())).getId());
                                OrdenDeTrabajoDetalle a =((OrdenesDeTrabajoTableModel)e.getSource()).getOrdenDeTrabajoDetalleAt(e.getFirstRow());
                                 ordenesDeTrabajo.add(a);

                            }
                        });
                        ordenesTable.setModel(ordenesDeTrabajoTableModel);
                        ordenesTable.repaint();

                        if (orden.isEmpty()){
                            throw new ExcepcionPropia();
                        }

                    } catch (NullPointerException e1) {
                        // TODO Auto-generated catch block

                    }catch (ExcepcionPropia e2) {
                        // TODO: handle exception
                        e2.sinResultado();
                    }
                }
            });

            tablas.add(ordenesScroll);
            this.add(consulta,BorderLayout.NORTH);
            this.add(consultar,BorderLayout.SOUTH);
            this.add(tablas,BorderLayout.CENTER);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);

        }

}
