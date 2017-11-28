package main.servlet;


import main.Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by juanmariamarsicovetere on 13/10/2017.
 */
@WebServlet(name = "MateriaPrimaServlet",urlPatterns = {"./MateriaPrimaServlet"})
public class MateriaPrimaServlet extends HttpServlet {
    Manager manager = Manager.getInstanced();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int numeroDeOrden= Integer.parseInt(request.getParameter("numeroDeOrden"));
            String materiaPrima= request.getParameter("materiaPrimaNombre");
            boolean isLocal= Boolean.parseBoolean(request.getParameter("localOExterior"));
            String [] lugares = request.getParameterValues("lugares");

        PrintWriter pw= response.getWriter();
        if(manager.searchNumeroOrdenCompra(numeroDeOrden)){
            pw.write("<h1>la materia prima solicitada fue: " +
                    materiaPrima +
                    "\n la compra fue: " +
                    (isLocal?"local":"exterior")
                    +
                    " y los lugares seleccionados para recibir la mercaderia fueron " +
                    lugares.toString() +
                    "</h1>");
        }else{
            pw.write("<h1>NO EXISTE ESA ORDEN DE COMPRAS </h1>");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consultado = request.getParameter("materiaPrimaN");
        manager.setConsultado(consultado);
        response.sendRedirect("consulta.jsp");
    }
    }

