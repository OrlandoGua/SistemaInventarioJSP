package mx.com.oga.comercializadora.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.oga.comercializadora.dao.CategoriaJDBCDAO;
import mx.com.oga.comercializadora.dao.ProductoJDBCDAO;
import mx.com.oga.comercializadora.dao.ProveedorJDBCDAO;
import mx.com.oga.comercializadora.modelo.Categoria;
import mx.com.oga.comercializadora.modelo.Productos;
import mx.com.oga.comercializadora.modelo.Proveedor;

@WebServlet(name = "ProductoController", urlPatterns = {"/productos"})
public class ProductoController extends HttpServlet {

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

            List<Productos> listaProd = new ProductoJDBCDAO().listAll();
            System.out.println("listaProducto " + listaProd);
//            HttpSession sesion = request.getSession();
            request.setAttribute("listaProducto", listaProd);
            request.getRequestDispatcher("/WEB-INF/productos/index.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {
                case "crear":
                    insertarProducto(request, response);
                    break;
                case "borrar":
                    borrarProducto(request, response);
                    break;
                case "actualizar":
                    actualizarProducto(request, response);
                    break;
            }

        }
    }

    private void crearformNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Proveedor> listproveedores = new ProveedorJDBCDAO().listAll();
        List<Categoria> listcategorias = new CategoriaJDBCDAO().listAll();
        request.setAttribute("proveedores", listproveedores);
        request.setAttribute("categorias", listcategorias);
        request.setAttribute("tipoForm", "crear");

        String url = "/WEB-INF/productos/forme.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idProd"));
        Productos producto = new ProductoJDBCDAO().findById(id);

        if (producto != null) {
            List<Proveedor> listproveedores = new ProveedorJDBCDAO().listAll();
            List<Categoria> listcategorias = new CategoriaJDBCDAO().listAll();
            request.setAttribute("proveedores", listproveedores);
            request.setAttribute("categorias", listcategorias);
            request.setAttribute("producto", producto);
            request.setAttribute( "tipoForm","actualizar");
            
            request.getRequestDispatcher("/WEB-INF/productos/formeEdi.jsp").forward(request, response);
        }

    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int clavepro = Integer.parseInt(request.getParameter("idPro"));
        int claP= Integer.parseInt(request.getParameter("idProv"));
        int claveC = Integer.parseInt(request.getParameter("idCat"));
        String descripcion = request.getParameter("desc");
        double precio = Double.parseDouble(request.getParameter("prec"));
        int existencia = Integer.parseInt(request.getParameter("exi"));
        
        ProductoJDBCDAO prodao = new ProductoJDBCDAO();
        Proveedor pro = new ProveedorJDBCDAO().findById(claP);
        Categoria cat = new CategoriaJDBCDAO().findById(claveC);
        
        String mensaje = prodao.insert(new Productos(clavepro,pro,cat,descripcion,precio,existencia));
        request.getSession().setAttribute("opProd", mensaje);
        
        response.sendRedirect("/Sistema/productos");
      
    }

    private void borrarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clavePro = Integer.parseInt(request.getParameter("idProd"));
        String mensaje = new ProductoJDBCDAO().delete(new Productos(clavePro));
        
        request.getSession().setAttribute("opProd", mensaje);
        response.sendRedirect("/Sistema/productos");
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clavepro = Integer.parseInt(request.getParameter("idPro"));
        int claP= Integer.parseInt(request.getParameter("idProv"));
        int claveC = Integer.parseInt(request.getParameter("idCat"));
        String descripcion = request.getParameter("desc");
        double precio = Double.parseDouble(request.getParameter("prec"));
        int existencia = Integer.parseInt(request.getParameter("exi"));
        
        ProductoJDBCDAO prodao = new ProductoJDBCDAO();
        Proveedor pro = new ProveedorJDBCDAO().findById(claP);
        Categoria cat = new CategoriaJDBCDAO().findById(claveC);
        
        String mensaje = prodao.update(new Productos(clavepro,pro,cat,descripcion,precio,existencia));
        request.getSession().setAttribute("opProd", mensaje);
        
        response.sendRedirect("/Sistema/productos");
    }

}
