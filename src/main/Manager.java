package main;


import main.DAO.MateriaPrimaDAO;
import main.DAO.OperarioDAO;
import main.DAO.SupervisorDAO;
import main.gui.Principal;
import main.modelsAndControllers.operario.controller.OperariosController;
import main.modelsAndControllers.supervisor.controllers.controllers.SupervisorController;

/**
 * Created by juanmariamarsicovetere on 17/11/2017.
 */
public class Manager {
    private Principal principal;
    private SupervisorController supervisorController;
    private OperariosController operariosController;
    private SupervisorDAO supervisorDAO;
    private OperarioDAO operarioDAO;
    private MateriaPrimaDAO materiaPrimaDAO;
    private boolean isSupervisor = false;


    private static Manager manager = new Manager();

    public Manager() {
        supervisorDAO = new SupervisorDAO();
        operarioDAO = new OperarioDAO();
        materiaPrimaDAO = new MateriaPrimaDAO();
    }

    public static Manager getInstanced(){
        return manager;
    }
    public static String consultado = "";

    public Principal getPrincipal() {
        return principal;
    }

    public void setConsultado(String consultado) {
        this.consultado = consultado;
    }


    public String getConsultado() {
        String resultado= consultado;
        consultado="";
    return resultado;
    }

    public boolean searchNumeroOrdenCompra(int numeroDeOrden) {
        //TODO buscar en base de datos orden de compra asociada al numero
        materiaPrimaDAO.verifyIfExistOrdenDeCompra(numeroDeOrden);

        return true;
    }

    public int getMateriaPrimaEnBD(String s) {
        //TODO buscar en base de datos lo consultado y devolver el la cantidad que hay de ese elemento
        int n  = Integer.parseInt(s);
        materiaPrimaDAO.getCantidadRestante(n);
        return 0;
    }

    public boolean validarSupervisor(String text, String text1) {
        supervisorController= new SupervisorController(supervisorDAO.buscarSupervisor(text,text1));
        if (supervisorController.getSupervisor()==null){
            return false;
        }
        isSupervisor=true;
        return true;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public SupervisorController getSupervisorController() {
        return supervisorController;
    }

    public void setSupervisorController(SupervisorController supervisorController) {
        this.supervisorController = supervisorController;
    }

    public OperariosController getOperariosController() {
        return operariosController;
    }

    public void setOperariosController(OperariosController operariosController) {
        this.operariosController = operariosController;
    }

    public SupervisorDAO getSupervisorDAO() {
        return supervisorDAO;
    }

    public void setSupervisorDAO(SupervisorDAO supervisorDAO) {
        this.supervisorDAO = supervisorDAO;
    }

    public OperarioDAO getOperarioDAO() {
        return operarioDAO;
    }

    public void setOperarioDAO(OperarioDAO operarioDAO) {
        this.operarioDAO = operarioDAO;
    }

    public MateriaPrimaDAO getMateriaPrimaDAO() {
        return materiaPrimaDAO;
    }

    public void setMateriaPrimaDAO(MateriaPrimaDAO materiaPrimaDAO) {
        this.materiaPrimaDAO = materiaPrimaDAO;
    }

    public void buscarOperario(String text) {
        int legajo = Integer.parseInt(text);
        operariosController= new OperariosController(operarioDAO.buscarOperario(legajo));
    }
}
