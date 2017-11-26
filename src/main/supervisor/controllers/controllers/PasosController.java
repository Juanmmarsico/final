package main.supervisor.controllers.controllers;

import main.Manager;
import main.common.MateriaPrima;
import main.common.Paso;
import main.supervisor.model.Producto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by juanmariamarsicovetere on 19/11/2017.
 */
public class PasosController extends Manager{
    ArrayList<Producto> productos = new ArrayList<Producto>();

    public PasosController(){
        File file = new File("productos.txt");
        try {
            Scanner scanner= new Scanner(file);
            while (scanner.hasNextLine()){
                String prod = scanner.nextLine();
                String name = prod.substring(0,3);
                ArrayList<Paso> pasos = new ArrayList<>();

                for(int i=4;i<20;i+=4){
                    MateriaPrima materiaPrima = new MateriaPrima(Integer.parseInt(prod.substring(i,(i+1))));
                    int requerido = Integer.parseInt(prod.substring(i+1,(i+2)));
                    String  detalle = prod.substring(i+2,(i+3));
                    pasos.add(new Paso(materiaPrima,detalle,requerido));
                }
                productos.add(new Producto(name,pasos));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
