package main.supervisor.gui;

import main.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

/**
 * Created by juanmariamarsicovetere on 17/11/2017.
 */
public class Formulario extends JFrame{
    Manager manager = Manager.getInstanced();

    private int codigo;
    JPanel formularioPanel,buttonPanel, textsPanel;

    JLabel cantidadLabel, codigoLabel, codigoPedidoLable;
    JTextField codigoField,cantidadField;

    JButton confirmarPedido, cancelar;

    public Formulario(int codigo){
        this.codigo=codigo;
        this.add(buildFrame());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 300));

        this.setVisible(true);
    }

    private JPanel buildFrame() {
        formularioPanel = new JPanel();
        formularioPanel.add(buildTextPanel());
        formularioPanel.add(buildButtonPanel());
        return formularioPanel;
    }

    private JPanel buildTextPanel() {
        textsPanel= new JPanel();

        codigoLabel = new JLabel("Codigo materia prima: ");
        codigoField = new JTextField();
        codigoField.setText(""+codigo);
        codigoField.setEditable(false);
        textsPanel.add(codigoLabel);
        textsPanel.add(codigoField);

        cantidadLabel = new JLabel("Cantidad a ordenar");
        cantidadField = new JTextField();
        cantidadField.setColumns(10);
        cantidadField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!(e.getKeyChar() =='0' || e.getKeyChar() =='1' || e.getKeyChar() =='2' || e.getKeyChar() =='3' || e.getKeyChar() =='4' || e.getKeyChar() =='5' || e.getKeyChar() =='6' || e.getKeyChar() =='7' || e.getKeyChar() =='8' || e.getKeyChar() =='9' )){
                    e.consume();
                }
            }
        });
        textsPanel.add(cantidadLabel);
        textsPanel.add(cantidadField);

        return textsPanel;
    }

    private JPanel buildButtonPanel() {
        JFrame f = this;
        buttonPanel = new JPanel();

        cancelar= new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean cerrar = JOptionPane.showConfirmDialog(f,"seguro que quiere cancelar","Cancelar?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
                if (cerrar) f.dispose();
            }
        });

        confirmarPedido = new JButton("Confirmar Pedido");
        confirmarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.setConsultado("21312");
                String s = "El codigo de pedido fue: "+manager.getConsultado();

                codigoPedidoLable = new JLabel(s);
                confirmarPedido.setEnabled(false);

            }
        });

        buttonPanel.add(confirmarPedido);
        buttonPanel.add(cancelar);
        return buttonPanel;
    }

}
