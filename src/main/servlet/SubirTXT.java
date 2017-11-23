package main.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@WebServlet(name = "SubirTXT", urlPatterns = {"./SubirTXT"})
public class SubirTXT extends HttpServlet {
    File file;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        file = new File(request.getParameter("file"));
        PrintWriter p = new PrintWriter("productos.txt");
        Scanner s = new Scanner(file);
        while (s.hasNextLine()){
            p.write(s.nextLine());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
