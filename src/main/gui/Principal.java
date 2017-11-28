package main.gui;


import main.Manager;
import main.gui.operario.OperarioPanel;
import main.gui.supervisor.SupervisorPanel;
import main.modelsAndControllers.operario.model.Operario;
import main.modelsAndControllers.supervisor.model.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Principal extends JFrame {

    private JMenu menu;
    private JMenuItem salir;
    private JMenuBar menuBar;
    private SupervisorPanel supervisorPanel;
    private OperarioPanel operarioPanel;

    Manager m = Manager.getInstanced();

    public Manager getM() {
        return m;
    }

    public Principal(Operario operario){
        this.setMinimumSize(new Dimension(800, 800));
            operarioPanel = new OperarioPanel(operario,this);
            this.add(supervisorPanel);

            setVisible(true);
        }
    public Principal(Supervisor supervisor){
        this.setMinimumSize(new Dimension(800, 800));
         supervisorPanel = new SupervisorPanel(supervisor,this);
        this.add(operarioPanel);


        setVisible(true);
    }

    public JMenuBar buildMenuBar(JMenu menues){
        menuBar = new JMenuBar();
        menuBar.add(menues);

        return menuBar;
    }
    public JMenu buildMenu(){
        menu = new JMenu("opciones");
        salir = new JMenuItem("salir");
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeApp();
            }
        });
        buildMenuBar(menu);
        return menu;
    }
    public JMenu buildMenu(JMenuItem menuItem){
        menu = new JMenu("opciones");
        salir = new JMenuItem("salir");
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeApp();
            }
        });
        menu.add(salir);
        menu.add(menuItem);

        buildMenuBar(menu);

        return menu;
    }

    public void closeApp(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

    }
    public Supervisor getSupervisor() {
        return supervisorPanel.getSupervisor();
    }

    public void setSupervisor(Supervisor supervisor) {
        supervisorPanel.setSupervisor(supervisor);
    }

    public Operario getOperario() {
        return operarioPanel.getOperario();
    }

    public void setOperario(Operario operario) {
        operarioPanel.setOperario(operario);
    }

    public JMenu getMenu() {
        return menu;
    }

    public void setMenu(JMenu menu) {
        this.menu = menu;
    }

    public JMenuItem getSalir() {
        return salir;
    }

    public void setSalir(JMenuItem salir) {
        this.salir = salir;
    }

    public OperarioPanel getOperarioPanel() {
        return operarioPanel;
    }

    public SupervisorPanel getSupervisorPanel() {
        return supervisorPanel;
    }
}
