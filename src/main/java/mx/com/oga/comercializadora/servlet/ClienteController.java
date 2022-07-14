package mx.com.oga.comercializadora.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.oga.comercializadora.dao.ClienteJDBCDAO;
import mx.com.oga.comercializadora.modelo.Clientes;

/**
 *
 * @author oga
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/clientes"})
public class ClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {
                case "nuevo":
                    formCrearNuevo(request, response);
                    break;
                case "editar":
                    formEditar(request, response);
                    break;
            }

        } else {
            List<Clientes> listClient = new ClienteJDBCDAO().listAll();
            System.out.println("categorias =" + listClient);
//            HttpSession sesion = request.getSession();

            request.setAttribute("clientes", listClient);

            String url = "/WEB-INF/clientes/index.jsp";
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
                    insertarCliente(request, response);
                    break;
                case "borrar":
                    borrarCliente(request, response);
                    break;
                case "actualizar":
                    actualizarCliente(request, response);
                    break;
            }

        }
    }

    private void formCrearNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/clientes/forme.jsp";
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        int id = Integer.parseInt(request.getParameter("idCli"));

        Clientes cli = new ClienteJDBCDAO().findById(id);

        if (cli != null) {
            request.setAttribute("tipoForm", "actualizar");
            request.setAttribute("cliente", cli);
            request.getRequestDispatcher("/WEB-INF/clientes/formeEdi.jsp").forward(request, response);
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clave = Integer.parseInt(request.getParameter("idCliente"));
        String ced = request.getParameter("cedula");
        String nomCia = request.getParameter("nombrecia");
        String nombre = request.getParameter("contacto");
        String dir = request.getParameter("direccion");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String cel = request.getParameter("cel");
        String fijo = request.getParameter("fijo");

        String mensaje = new ClienteJDBCDAO().Insertat(new Clientes(clave, ced, nomCia, nombre, dir, fax, email, cel, fijo));

        request.getSession().setAttribute("mensajeCliet", mensaje);

        response.sendRedirect("/Sistema/clientes");
    }

    private void borrarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     int clave = Integer.parseInt(request.getParameter("idCli"));
     
        String mensaje = new ClienteJDBCDAO().delete(new Clientes(clave));
        
        request.getSession().setAttribute("mensajeCliet",mensaje);
        response.sendRedirect("/Sistema/clientes");
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clave = Integer.parseInt(request.getParameter("idCliente"));
        String ced = request.getParameter("cedula");
        String nomCia = request.getParameter("nombrecia");
        String nombre = request.getParameter("contacto");
        String dir = request.getParameter("direccion");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String cel = request.getParameter("cel");
        String fijo = request.getParameter("fijo");

        String mensaje = new ClienteJDBCDAO().update(new Clientes(clave, ced, nomCia, nombre, dir, fax, email, cel, fijo));

        request.getSession().setAttribute("mensajeCliet", mensaje);

        response.sendRedirect("/Sistema/clientes");
    }

}
