package main.DAO;

import main.operario.controller.ExcepcionPropia;
import main.operario.model.MateriaPrima;
import main.operario.model.Operario;
import main.operario.model.Paso;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class OperarioDAO extends DAO {

    public Operario buscarOperario(int legajo){
        String nombre ="";
        String dni="";
        String apellido="";
        int legajoOperario = 0;
        Operario o = null;


        try {
            CallableStatement call = getConnection().prepareCall("");//el Stored procedure deberia hacer un join entre
            //la tabla de los operarios, la de Ordenes de trabajo y la de ordenes de trabajo detalle.
            ResultSet r = call.executeQuery();
            for (int i = 0;r.next();i++){
                if (r.wasNull()){
                    System.out.println("no hay un operario");
               throw new ExcepcionPropia("no existe el operario",legajo);
                }

                //si estoy en lo correcto el Join me devolveria las 3 tablas combinadas repitiendo los datos comunes
                if (i>0){
                    nombre = r.getString("nombre");
                    apellido = r.getString("apellido");
                    dni = r.getString("dni");//por algun motivo que no recuerdo dije que iba a ser un string
                    legajoOperario = r.getInt("legajo");
                    o = new Operario(nombre,apellido,dni,legajoOperario);
                }


                    String idOrdenDeTrabajo = r.getString("IDOrden");
                    Calendar fechaDeAlta= Calendar.getInstance();
                    fechaDeAlta.setTime((r.getDate("FechaDeAlta")));
                    int cantidad= r.getInt("cantidad");
                    Calendar estimacion = Calendar.getInstance();
                    estimacion.setTime((r.getDate("estimacion")));
                    String comentario = r.getString("comentario");
                    boolean isUrgente = r.getBoolean("urgente");
                    Calendar fechaFin = Calendar.getInstance();
                    fechaFin.setTime(r.getDate("fechaFin"));
                    int empezada= r.getInt("empezada");
                    if (r.wasNull()){
                        empezada = -1;
                    }
                Paso paso = new Paso(new MateriaPrima(r.getInt("Codigo"),r.getInt("cantidadRestante")),r.getString("detalle"),empezada);


                o.agregarOrdenDeTrabajoUnSoloPaso(idOrdenDeTrabajo,fechaDeAlta,paso,cantidad,estimacion,comentario,isUrgente,fechaFin);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExcepcionPropia excepcionPropia) {
            excepcionPropia.printStackTrace();
        }
        if (o==null){
            try {
                throw new ExcepcionPropia("no existe el operario",legajo);
            } catch (ExcepcionPropia excepcionPropia) {
                excepcionPropia.printStackTrace();
            }
        }
        return o;
    }
}
