/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.oga.comercializadora.servlet;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.oga.comercializadora.dao.EmpleadoJDBCDAO;
import mx.com.oga.comercializadora.modelo.Empleados;

/**
 *
 * @author oga
 */
@WebServlet("/empleados")
public class EmpleadoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {
                case "nuevo":
                    formNuevo(request, response);
                    break;
                case "editar":
                    formEditar(request, response);
                    break;

            }

        } else {

            List<Empleados> listempleados = new EmpleadoJDBCDAO().listAll();

            System.out.println("listaEmpleados = " + listempleados);
//            HttpSession sesion = request.getSession();

            request.setAttribute("empleado", listempleados);
            String url = "/WEB-INF/empleados/index.jsp";
            request.getRequestDispatcher(url).forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {
                case "crear":
                    insertarEmpleado(request, response);
                    break;
                case "borrar":
                    borrarEmpleado(request, response);
                    break;
                case "actualizar":
                    actualizarEmpleado(request, response);
                    break;

            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/empleados/forme.jsp";
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher(url).forward(request, response);
        
//        EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();
//
//        List<Empleados> empleados = daoEmpleado.listAll();
//
//        request.setAttribute("empleados", empleados);

    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      

        int id = Integer.parseInt(request.getParameter("idEmp"));
         EmpleadoJDBCDAO daoEmpleado = new EmpleadoJDBCDAO();
        Empleados empleado = daoEmpleado.findById(id);

        if (empleado != null) {

            List<Empleados> empleados = daoEmpleado.listAll();
            request.setAttribute("empleo", empleados);
            request.setAttribute("tipoForm", "actualizar");

            request.setAttribute("empleado", empleado);
            request.getRequestDispatcher("/WEB-INF/empleados/formeEdi.jsp").forward(request, response);
        }
    }

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idEm = Integer.parseInt(request.getParameter("idEmp"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String fecha = request.getParameter("fecha");
            int reporta = Integer.parseInt(request.getParameter("reportaA"));
            int extension = Integer.parseInt(request.getParameter("extension"));

            java.util.Date fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            java.sql.Date fechaSQL = new java.sql.Date(fechaNac.getTime());

            EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();
            String mensaje = empleadoDAO.Insertat(new Empleados(idEm, nombre, apellido, fechaSQL, reporta, extension));

            request.getSession().setAttribute("OpEmp", mensaje);
            response.sendRedirect("/Sistema/empleados");

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

    private void borrarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idEmp"));

        EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();

        String mensaje = empleadoDAO.delete(new Empleados(id));
        request.getSession().setAttribute("OpEmp", mensaje);
        response.sendRedirect("/Sistema/empleados");
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idEm = Integer.parseInt(request.getParameter("idEmp"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String fecha = request.getParameter("fecha");
            int reporta = Integer.parseInt(request.getParameter("reportaA"));
            int extension = Integer.parseInt(request.getParameter("extension"));

            java.util.Date fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            java.sql.Date fechaSQL = new java.sql.Date(fechaNac.getTime());

            EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();
            String mensaje = empleadoDAO.update(new Empleados(idEm, nombre, apellido, fechaSQL, reporta, extension));

            request.getSession().setAttribute("OpEmp", mensaje);
            response.sendRedirect("/Sistema/empleados");

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

}
