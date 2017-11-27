package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class test {

    public static void main(String[] args) {
//       Login login = new Login();
//        Login.startNewApp();
//        Formulario formulario = new Formulario(12);
//        new ExcepcionPropia(15234);

        DefaultTableModel dm = new DefaultTableModel();
        String [] strings = {"uno","dos","tres","cuatro"};
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i=0; i<20; i++){
            ints.add(i);
        }

        JComboBox<String> j= new JComboBox<String>(strings);
        Object o [][]= new Object[ints.size()][2];

        for (int i=0; i<20; i++) {
            o[i][0]= ints.get(i);
            o[i][1]= j.getItemAt(0);
        }
        dm.setDataVector(o,new Object[]{"num","combo"});
        JTable table = new JTable(dm);
        TableColumn column = table.getColumnModel().getColumn(1);
        column.setCellEditor(new DefaultCellEditor(j));
        JFrame frame = new JFrame();
        frame.add(table);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
