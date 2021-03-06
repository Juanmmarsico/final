package main.DAO;

import main.modelsAndControllers.common.MateriaPrima;
import main.modelsAndControllers.common.Paso;
import main.modelsAndControllers.operario.controller.ExcepcionPropia;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajo;
import main.modelsAndControllers.supervisor.model.OrdenDeTrabajoDetalle;
import main.modelsAndControllers.supervisor.model.Supervisor;

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

    public Supervisor buscarSupervisor(String documento, String contrasena){
        Supervisor s = null;

        try {
            ArrayList<OrdenDeTrabajo> ordenesDeTrabajoSup = searchOrdenesDeTrabajo(documento,contrasena);

                CallableStatement call = getConnection().prepareCall("//devolverSupervisor ?");
                call.setString(1,documento);
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



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExcepcionPropia excepcionPropia) {
            excepcionPropia.printStackTrace();
        }

        return s;
    }
    public ArrayList<OrdenDeTrabajo> searchOrdenesDeTrabajo(String documento, String contrasena) throws SQLException, ExcepcionPropia {
        ArrayList<OrdenDeTrabajo> ordenesDeTrabajoSup = new ArrayList<OrdenDeTrabajo>();

        PreparedStatement call = getConnection().prepareCall("SELECT * FROM supervisor WHERE DniSupervisor=? AND pass=?");
        call.setString(1,documento);
        call.setString(2,contrasena);
        ResultSet r = call.executeQuery();
        if (!r.next()){
            throw new ExcepcionPropia("no se encontro el supervisor",documento);
        }else {

            Statement statementOrdenes = getConnection().createStatement();
            ResultSet resultSetOrdenes = statementOrdenes.executeQuery("SELECT * FROM  (join magico para sacar las ordenes con dni supervisor y que me devuelve el id de la orden de trabajo) WHERE dni=" + documento);
            Map<String, ArrayList<OrdenDeTrabajoDetalle>> pasos = new TreeMap<String, ArrayList<OrdenDeTrabajoDetalle>>();
            while (resultSetOrdenes.next()) {
                String idOrden = r.getString("IDOrden");
                Calendar fechaFin = Calendar.getInstance();
                fechaFin.setTime(r.getDate("FechaFin"));
                Paso paso = new Paso(new MateriaPrima(r.getInt("codigoMateriaPrima")), r.getInt("requerido"), r.getString("detalle"), fechaFin);
                OrdenDeTrabajoDetalle ordenDeTrabajoDetalle = new OrdenDeTrabajoDetalle(r.getInt("estado"), r.getInt("operario"), paso);

                if (pasos.containsKey(idOrden)) {
                    pasos.get(idOrden).add(ordenDeTrabajoDetalle);
                } else {
                    ArrayList<OrdenDeTrabajoDetalle> pasosArray = new ArrayList<OrdenDeTrabajoDetalle>();
                    pasosArray.add(ordenDeTrabajoDetalle);
                    pasos.put(idOrden, pasosArray);
                }
            }


            PreparedStatement preparedStatementOrdenes = getConnection().prepareStatement("SELECT * FROM  (join magico para sacar las ordenes con dni supervisor) WHERE dni=?");
            preparedStatementOrdenes.setString(1, documento);
            ResultSet resultSetOrdenesPrepared = preparedStatementOrdenes.executeQuery();
            while (resultSetOrdenes.next()) {
                String id = r.getString("IDOrden");
                Calendar fechaDeAlta = Calendar.getInstance();
                fechaDeAlta.setTime(r.getDate("fechaDeAlta"));
                ArrayList<OrdenDeTrabajoDetalle> ordenDeTrabajoDetalles = pasos.get(r.getString(id));
                int cantidad = r.getInt("cantidad");
                Calendar estimacion = Calendar.getInstance();
                estimacion.setTime(r.getDate("Estimacion"));
                String comentario = r.getString("Comentario");
                boolean isUrgente = r.getBoolean("Urgente");
                OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo(id, fechaDeAlta, ordenDeTrabajoDetalles, cantidad, estimacion, comentario, isUrgente);
                ordenesDeTrabajoSup.add(ordenDeTrabajo);
            }

        }
            return ordenesDeTrabajoSup;
        }
    public ArrayList<OrdenDeTrabajoDetalle> searchOrdenesDeTrabajo(Supervisor supervisor,String consulta) {
        String documento = supervisor.getDni();
        String contrasena = supervisor.getPass();//habia pensado en llamar a la base de datos, para que me traiga la ultima informacion, pero como no lo implemente no lo puedo testear
        ArrayList<OrdenDeTrabajoDetalle> sup = new ArrayList<OrdenDeTrabajoDetalle>();

            ArrayList<OrdenDeTrabajo>  aux= new ArrayList<OrdenDeTrabajo>(supervisor.getOrdenDeTrabajos());
            for(OrdenDeTrabajo o: aux){
                if(o.getId().equals(consulta)){
                    sup = o.getPasos();
                }
            }
        return sup;
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
            CallableStatement callableStatement = getConnection().prepareCall("crearOrdenDeTrabajo(?,?,?,?,?,?,?)");
            callableStatement.setString(1,id);
            callableStatement.setDate(2,(Date) fechaDeAlta.getTime());
            callableStatement.setInt(3,cantidad);
            callableStatement.setDate(4,(Date)estimacion.getTime());
            callableStatement.setString(5,comentario);
            callableStatement.setBoolean(6,isUrgente);
            callableStatement.setBoolean(7,isDone);
            callableStatement.executeQuery();

            //TODO recorrer pasos y subirlos a la base de datos
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getOperariosParaAsignacion()  {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("");
        try {
            Statement statement = getConnection().createStatement();
            ResultSet r = statement.executeQuery("SELECT * FROM operarios");
            while (r.next()){
                String nombre =r.getString("Nombre");
                String apellido=r.getString("Apellido");
                String dni = r.getString("dni");
                int legajo = r.getInt("legajo");

                String operario = ""+ legajo+"\t"+nombre+"\t"+apellido+"\t"+dni;
                arrayList.add(operario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
