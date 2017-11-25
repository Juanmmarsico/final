package main.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* Columnas de las tablas
* Ordenes de trabajo
* IDOrden | FechaAlta | Cantidad | Estimacion | Comentario | Urgente | FechaFin | Terminada
*
* Orden de trabajo detalle
* ID | IDOrden | Pasos | OperarioLegajo | Estado
*
*Paso
*ID | MateriaPrima | Detalle
*
* Materia prima
* Codigo | CantidadRestante
*
* Operario
* OperarioLegajo | Nombre | Apellido | DNI
*
* Supervisor
* Nombre | Apellido | DNISupervisor | Password
*
* Orden De Trabajo por supervisor
* IDOrden | DNISupervisor
*
* */

public class DAO {
    private static Connection connection;
    private String driver= "driver de la base de datos";
    private String url = "jdbc:urlBaseDeDatos.com";

    public DAO (){
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }

}
