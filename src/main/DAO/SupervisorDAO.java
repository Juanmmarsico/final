package main.DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupervisorDAO extends DAO {

    public ArrayList<String> buscarSupervisor(int documento, String contrasena){
        ArrayList<String> a= new ArrayList<>();

        try {
            CallableStatement call = getConnection().prepareCall("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
    }
}
