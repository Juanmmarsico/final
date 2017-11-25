package main.DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MateriaPrimaDAO extends DAO{

    public ArrayList<String> buscarSupervisor(int documento, String contrasena){
        ArrayList<String> a= new ArrayList<>();

        try {
            Statement call = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
    }
}
