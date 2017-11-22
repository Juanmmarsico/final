package main.supervisor.gui;


import main.supervisor.controllers.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Login extends JFrame {
    private String supervisor = "Supervisor";
    private String operario = "Operario";
    private JComboBox selector;
    private JPanel operarioPanel;
    private JPanel supervisorPanel;
    private JTextArea userA;
    private JTextArea PassA;
    private JTextField user;
    private JTextField pass;
    private JButton cancel;
    private JButton ok;

    public Login(){
        this.add(buildPanel());

        this.setVisible(true);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
    }

    public JPanel buildPanel(){
        userA = new JTextArea("User");
        PassA = new JTextArea("Pass");
        user = new JTextField();

        JPanel principal = new JPanel(new BorderLayout());
        principal.add(buildSupervisorPanel(), BorderLayout.CENTER);
        principal.add(buildOperarioPanel(),BorderLayout.WEST);
        principal.add(buildSelectTypePanel(),BorderLayout.NORTH);
        principal.add(buildButtonsPanel(),BorderLayout.SOUTH);

        return principal;
    }

    public JPanel buildSupervisorPanel(){
        supervisorPanel = new JPanel(new BorderLayout());
        supervisorPanel.add(userA);
        supervisorPanel.add(user);
        supervisorPanel.add(PassA);
        pass = new JTextField();
        supervisorPanel.add(pass);

        return supervisorPanel;
    }

    public JPanel buildOperarioPanel(){
        operarioPanel = new JPanel();
        operarioPanel.add(userA);
        operarioPanel.add(user);

        return operarioPanel;
    }

    public  JPanel buildSelectTypePanel(){
        String [] opciones = {supervisor,operario};
       selector = new JComboBox(opciones);
       selector.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (selector.getSelectedItem().equals(supervisor)){
                   operarioPanel.setVisible(false);
                   supervisorPanel.setVisible(true);
               }else{
                   operarioPanel.setVisible(true);
                   supervisorPanel.setVisible(false);
               }

           }
       });
       JPanel selectionPanel = new JPanel();
       selectionPanel.add(selector);
       return selectionPanel;
    }

    public JPanel buildButtonsPanel(){
        JPanel buttonPanel = new JPanel();
        ok = new JButton("ok");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager m =Manager.getInstanced();
                if(selector.getSelectedItem().equals(supervisor)){
//                    if (m.validarSupervisor(user.getText(),pass.getText()));
                    System.out.println(user.getText()+"        "+pass.getText());
                }else{
//                    m.buscarOperario(user.getText());
                    System.out.println(user.getText());
                }
            }
        });

        buttonPanel.add(ok);

        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                dispose();
            }
        });

        buttonPanel.add(cancel);

        return buttonPanel;
    }

}
