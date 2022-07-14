package mx.com.oga.comercializadora.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.oga.comercializadora.dao.ProveedorJDBCDAO;
import mx.com.oga.comercializadora.modelo.Proveedor;

@WebServlet("/proveedor")
public class ProveedorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {
                case "nuevo":
                    crearformNuevo(request, response);
                    break;
                case "editar":
                    formEditar(request, response);
                    break;

            }
        } else {

            List<Proveedor> listProveedor = new ProveedorJDBCDAO().listAll();
            System.out.println("listaProveedor " + listProveedor);
//            HttpSession sesion = request.getSession();
            request.setAttribute("proveedor", listProveedor);
            request.getRequestDispatcher("/WEB-INF/proveedor/index.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {
                case "crear":
                    insertarProveedor(request, response);
                    break;
                case "borrar":
                    borrarProveedor(request, response);
                    break;
                case "actualizar":
                    actualizarProveedor(request, response);
                    break;
            }

        }
    }

    private void crearformNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/proveedor/forme.jsp";
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idProv = Integer.parseInt(request.getParameter("idProv"));
        
         ProveedorJDBCDAO proveedorDAO = new ProveedorJDBCDAO();
        Proveedor pro = proveedorDAO.findById(idProv);
        
        if(pro !=null){
         request.setAttribute("tipoForm","actualizar");
         request.setAttribute("proveedor", pro);
         request.getRequestDispatcher("/WEB-INF/proveedor/formeEdi.jsp").forward(request, response);
        }
    }

    private void insertarProveedor(HttpServletRequest request, HttpServletResponse response) 
     throws ServletException, IOException{
        
        int clave = Integer.parseInt(request.getParameter("idProv"));
        String nombreProv = request.getParameter("nombre");
        String contacto = request.getParameter("contacto");
        String cel = request.getParameter("cel");
        String telfi = request.getParameter("telfijo");
        
        
       
        ProveedorJDBCDAO proveedorDAO = new ProveedorJDBCDAO();
          
        String mensaje = proveedorDAO.Insertat(new Proveedor(clave,nombreProv,contacto,cel,telfi ));
        
        request.getSession().setAttribute("mensajeprov", mensaje);
        
        response.sendRedirect("/Sistema/proveedor");
    }

    private void borrarProveedor(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException{
        
        int clave = Integer.parseInt(request.getParameter("idProv"));
         ProveedorJDBCDAO proveedorDAO = new ProveedorJDBCDAO();
        String mensaje = proveedorDAO.delete(new Proveedor(clave));
        request.getSession().setAttribute("mensajeprov",mensaje);
        response.sendRedirect("/Sistema/proveedor");
        
    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) 
     throws ServletException, IOException{
        
        int clave = Integer.parseInt(request.getParameter("idProv"));
        String nombreProv = request.getParameter("nombre");
        String contacto = request.getParameter("contacto");
        String cel = request.getParameter("cel");
        String telfi = request.getParameter("telfijo");
        
       
       ProveedorJDBCDAO  proDAO = new  ProveedorJDBCDAO();
       
       String mensaje = proDAO.update(new Proveedor(clave, nombreProv,contacto,cel,telfi));
       
       request.getSession().setAttribute("mensajeprov",mensaje);
       response.sendRedirect("/Sistema/proveedor");
    }

}
