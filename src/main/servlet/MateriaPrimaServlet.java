package main.servlet;


import main.supervisor.controllers.Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by juanmariamarsicovetere on 13/10/2017.
 */
@WebServlet(name = "MateriaPrimaServlet",urlPatterns = {"/MateriaPrimaServlet"})
public class MateriaPrimaServlet extends HttpServlet {
    Manager manager = Manager.getInstanced();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consultado = request.getParameter("materiaPrimaN");
        manager.setConsultado(consultado);
        response.sendRedirect("consulta.jsp");
    }
    }

