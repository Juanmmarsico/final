package main.DAO;

import main.common.MateriaPrima;
import main.common.Paso;
import main.operario.controller.ExcepcionPropia;
import main.supervisor.model.OrdenDeTrabajo;
import main.supervisor.model.OrdenDeTrabajoDetalle;
import main.supervisor.model.Supervisor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

public class SupervisorDAO extends DAO {

    public static int getLastId() {
        int lastId = 0;
        try {
            Statement statement= getConnection().createStatement();
            ResultSet r = statement.executeQuery("SELECT IDOrden FROM ordenesDeTrabajo");
            while (r.next()){
                int aux = Integer.parseInt(r.getString("IDOrden").substring(0,5));
                lastId = (lastId>aux?lastId:aux);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public Supervisor buscarSupervisor(int documento, String contrasena){
        Supervisor s = null;

        try {
            PreparedStatement call = getConnection().prepareCall("SELECT * FROM supervisor WHERE DniSupervisor=? AND pass=?");
            call.setInt(1,documento);
            call.setString(2,contrasena);
            ResultSet r = call.executeQuery();
            if (!r.next()){
                throw new ExcepcionPropia("no se encontro el supervisor",documento);
            }else{

                Statement statementOrdenes = getConnection().createStatement();
                ResultSet resultSetOrdenes = statementOrdenes.executeQuery("SELECT * FROM  (join magico para sacar las ordenes con dni supervisor y que me devuelve el id de la orden de trabajo) WHERE dni="+documento);
                Map<String,ArrayList<OrdenDeTrabajoDetalle>> pasos= new TreeMap<String ,ArrayList<OrdenDeTrabajoDetalle>>();
                while (resultSetOrdenes.next()){
                   String idOrden= r.getString("IDOrden");
                   Calendar fechaFin = Calendar.getInstance();
                   fechaFin.setTime(r.getDate("FechaFin"));
                    Paso paso = new Paso(new MateriaPrima(r.getInt("codigoMateriaPrima")),r.getInt("requerido"),r.getString("detalle"),fechaFin);
                    OrdenDeTrabajoDetalle ordenDeTrabajoDetalle = new OrdenDeTrabajoDetalle(r.getInt("estado"),r.getInt("operario"),paso);

                    if (pasos.containsKey(idOrden)){
                        pasos.get(idOrden).add(ordenDeTrabajoDetalle);
                    }else{
                        ArrayList<OrdenDeTrabajoDetalle> pasosArray = new ArrayList<OrdenDeTrabajoDetalle>();
                        pasosArray.add(ordenDeTrabajoDetalle);
                        pasos.put(idOrden,pasosArray);
                    }
                }


                PreparedStatement preparedStatementOrdenes = getConnection().prepareStatement("SELECT * FROM  (join magico para sacar las ordenes con dni supervisor) WHERE dni=?");
                preparedStatementOrdenes.setInt(1,documento);
                ResultSet resultSetOrdenesPrepared = preparedStatementOrdenes.executeQuery();
                ArrayList<OrdenDeTrabajo> ordenesDeTrabajoSup= new ArrayList<OrdenDeTrabajo>();
                while (resultSetOrdenes.next()){
                    String id = r.getString("IDOrden");
                    Calendar fechaDeAlta=Calendar.getInstance();
                    fechaDeAlta.setTime(r.getDate("fechaDeAlta"));
                    ArrayList< OrdenDeTrabajoDetalle > ordenDeTrabajoDetalles = pasos.get(r.getString(id));
                    int cantidad = r.getInt("cantidad");
                    Calendar estimacion = Calendar.getInstance();
                    estimacion.setTime(r.getDate("Estimacion"));
                    String comentario = r.getString("Comentario");
                    boolean isUrgente= r.getBoolean("Urgente");
                    OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo(id,fechaDeAlta,ordenDeTrabajoDetalles,cantidad,estimacion,comentario,isUrgente);
                    ordenesDeTrabajoSup.add(ordenDeTrabajo);
                }


                CallableStatement call1 = getConnection().prepareCall("//devolverSupervisor ?");
                call1.setInt(1,documento);
                ResultSet r1 = call.executeQuery();
                String nombre;
                String dni;
                String apellido;

                        nombre = r1.getString("nombre");
                        apellido = r1.getString("apellido");
                        dni = r1.getString("dni");//por algun motivo que no recuerdo dije que iba a ser un string
                    if (ordenesDeTrabajoSup.isEmpty()){
                        s = new Supervisor(nombre,apellido,dni);

                    }   else {
                        s = new Supervisor(nombre, apellido, dni, ordenesDeTrabajoSup);
                    }


                }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExcepcionPropia excepcionPropia) {
            excepcionPropia.printStackTrace();
        }

        return s;
    }

    public void cargarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo){
         String id=ordenDeTrabajo.getId();
         Calendar fechaDeAlta = ordenDeTrabajo.getFechaDeAlta();
         ArrayList<OrdenDeTrabajoDetalle> pasos = ordenDeTrabajo.getPasos();
        int cantidad = ordenDeTrabajo.getCantidad();
         Calendar estimacion = ordenDeTrabajo.getEstimacion();
        String comentario = ordenDeTrabajo.getComentario();
         boolean isUrgente = ordenDeTrabajo.isUrgente();
         boolean isDone=ordenDeTrabajo.isDone();



         try {
            CallableStatement callableStatement = getConnection().prepareCall("crearOrdenDeTrabajo(?,?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
