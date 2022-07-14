package mx.com.oga.comercializadora.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oga
 */
@WebServlet(name = "MenuController", urlPatterns = {"/menu"})
public class MenuController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {
                case "categoria":
                    response.sendRedirect("/Sistema/categorias");                   
                    break;
                case "productos":
                     response.sendRedirect("/Sistema/productos");     
                    break;
                case "cliente":
                    response.sendRedirect("/Sistema/clientes");                    
                    break;
                case "empleado":
                    response.sendRedirect("/Sistema/empleados");                    
                    break;
                case "proveedor":
                     response.sendRedirect("/Sistema/proveedor");     
                    break;
                case "pedidos":
                     response.sendRedirect("/Sistema/pedidos");     
                    break;
                case "inicio":
                     response.sendRedirect("/Sistema/categorias");     
                default:
                     response.sendRedirect("/Sistema/categorias");     
                    break;
                    

            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
