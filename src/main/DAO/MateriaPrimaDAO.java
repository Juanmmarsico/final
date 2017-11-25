package main.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MateriaPrimaDAO extends DAO{

    public int getCantidadRestante(int codigoMateriaPrima){
        try {
            Statement call = getConnection().createStatement();
            ResultSet r = call.executeQuery("SELECT * FROM materiaPrima WHERE codigo="+codigoMateriaPrima);

            while (r.next()){
                return r.getInt("cantidadRestante");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public int setCantidadRestante(int codigoMateriaPrima,int nuevaCantidad){
        try {
            Statement call = getConnection().createStatement();
            ResultSet r = call.executeQuery("UPDATE  materiaPrima SET cantidadRestante="+nuevaCantidad  +"  WHERE codigo="+codigoMateriaPrima);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
