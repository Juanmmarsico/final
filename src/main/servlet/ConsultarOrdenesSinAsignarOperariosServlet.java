package main.servlet;

import main.Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by juanmariamarsicovetere on 28/11/2017.
 */
@WebServlet(name = "ConsultaServlet",urlPatterns = {"./ConsultaServlet"})
public class ConsultarOrdenesSinAsignarOperariosServlet extends HttpServlet {

    Manager manager = Manager.getInstanced();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consultado = request.getParameter("materiaPrimaN");
        manager.setConsultado(consultado);
        for (int i=0; i<10; i++) {
            request.setAttribute("OrdenDeTrabajo"+i, i);
        }
        request.getRequestDispatcher("OrdenesDeTrabajoSinAsignar.jsp").forward(request,response);
        response.sendRedirect("ORdenesDeTrabajoSinAsignar.jsp");
    }
}
