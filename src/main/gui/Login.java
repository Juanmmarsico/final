package main.gui;


import main.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by juanmariamarsicovetere on 14/11/2017.
 */
public class Login extends JFrame {
    private String sessionThread;
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
    public static Map<String,Thread> threads = new TreeMap<String ,Thread>();

    public static Map<String, Thread> getThreads() {
        return threads;
    }

    public static void endThread(String threadName) {
        threads.get(threadName).interrupt();
    }

    public static void startNewApp() {
        Sessions s = new Sessions(new Login());
        Thread thread = new Thread(s);
        Login l= s.getLog();
        l.setSessionThread(thread.getName());
        threads.put(thread.getName(),thread);
    }

    public void setSessionThread(String sessionThread) {
        this.sessionThread = sessionThread;
    }

    public String getSessionThread() {
        return sessionThread;
    }

    public Login(){
        sessionThread = Thread.currentThread().getName();
        this.add(buildPanel());
        this.setMinimumSize(new Dimension(300, 300));
        this.setVisible(true);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
    }
    public Login(String sessionThread){

        this.sessionThread = sessionThread;
        this.add(buildPanel());
        this.setMinimumSize(new Dimension(300, 300));
        this.setVisible(true);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
    }

    public JPanel buildPanel(){
        userA = new JTextArea("User");
        PassA = new JTextArea("Pass");
        user = new JTextField();
        user.setColumns(5);

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
        Login l = this;
        ok = new JButton("ok");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager m =Manager.getInstanced();
                if(selector.getSelectedItem().equals(supervisor)){
                   if( m.validarSupervisor(user.getText(),pass.getText())){
                       new Principal(m.getSupervisorController().getSupervisor());
                   }
                    System.out.println(user.getText()+"        "+pass.getText());
                }else{
                    if(m.buscarOperario(user.getText())){
                        new Principal(m.getOperariosController().getOperario());
                    }
                    l.hide();
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

        JButton prueba = new JButton("Prueba");
        prueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewApp();
            }
        });

        JButton finalizarPrueba = new JButton("Prueba3");
        finalizarPrueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent s) {
                endThread(sessionThread);
            }
        });
        buttonPanel.add(prueba);
        buttonPanel.add(finalizarPrueba);

        return buttonPanel;
    }

}
