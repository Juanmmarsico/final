package main.supervisor.model;

import java.util.ArrayList;

public class Producto {
    private String name;
    private ArrayList <Paso> pasos;

    public Producto() {
    }

    public Producto(String name, ArrayList<Paso> pasos) {
        this.name = name;
        this.pasos = pasos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(ArrayList<Paso> pasos) {
        this.pasos = pasos;
    }
}
