package mx.com.oga.comercializadora.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.oga.comercializadora.dao.CategoriaJDBCDAO;
import mx.com.oga.comercializadora.dao.IProducto;
import mx.com.oga.comercializadora.dao.ProductoJDBCDAO;

import mx.com.oga.comercializadora.modelo.Categoria;
import mx.com.oga.comercializadora.modelo.Productos;

@WebServlet("/categorias")
public class CategoriaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {

                case "mostrar":
                    mostrarCategoria(request, response);
                    break;
                case "nuevo":
                    formCrearNuevo(request, response);
                    break;
                case "editar":
                    formEditar(request, response);
                    break;
                case "buscar":
                    buscarCategoria(request, response);
                    break;
            }

        } else {
            List<Categoria> categorias = new CategoriaJDBCDAO().listAll();
            System.out.println("categorias =" + categorias);
            HttpSession sesion = request.getSession();

            sesion.setAttribute("categoria", categorias);

            String url = "/WEB-INF/categorias/index.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");

            switch (accion) {
                case "CREAR":
                    insertarCategoria(request, response);
                    break;
                case "borrar":
                    borrarCategoria(request, response);
                    break;
                case "ACTUALIZAR":
                    actualizarCategoria(request, response);
                    break;
            }

        }
    }

    private void formCrearNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/categorias/categorianuevo.jsp";
        request.setAttribute("tipoForm", "CREAR");
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void insertarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clave = Integer.parseInt(request.getParameter("idCat"));
        String nombreCat = request.getParameter("nombreCat");

        CategoriaJDBCDAO categoriDAO = new CategoriaJDBCDAO();

        String mensaje = categoriDAO.Insertat(new Categoria(clave, nombreCat));

        request.getSession().setAttribute("mensajeop", mensaje);

        response.sendRedirect("/Sistema/categorias");

    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCat = Integer.parseInt(request.getParameter("idCat"));

        CategoriaJDBCDAO categoraJDBC = new CategoriaJDBCDAO();
        Categoria cat = categoraJDBC.findById(idCat);

        if (cat != null) {
            request.setAttribute("tipoForm", "ACTUALIZAR");
            request.setAttribute("categoria", cat);
            request.getRequestDispatcher("/WEB-INF/categorias/categorianuevo.jsp").forward(request, response);
        }

    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clave = Integer.parseInt(request.getParameter("idCat"));
        String nombre = request.getParameter("nombreCat");

        CategoriaJDBCDAO catDAO = new CategoriaJDBCDAO();

        String mensaje = catDAO.update(new Categoria(clave, nombre));

        request.getSession().setAttribute("mensajeop", mensaje);
        response.sendRedirect("/Sistema/categorias");
    }

    private void borrarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clave = Integer.parseInt(request.getParameter("idCat"));
        CategoriaJDBCDAO catDAO = new CategoriaJDBCDAO();
        String mensaje = catDAO.delete(new Categoria(clave));
        request.getSession().setAttribute("mensajeop", mensaje);
        response.sendRedirect("/Sistema/categorias");
    }

    private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriaJDBCDAO daoCat = new CategoriaJDBCDAO();
        ProductoJDBCDAO daoProd = new ProductoJDBCDAO();

        int clave = Integer.parseInt(request.getParameter("idCat"));

        Categoria cat = daoCat.findById(clave);
        List<Productos> producto = daoProd.getProductosByCategoria(cat);

        cat.setProductos(producto);

        request.setAttribute("categoria", cat);
        request.getRequestDispatcher("/WEB-INF/categorias/show.jsp").forward(request, response);

    }

    private void buscarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dato = request.getParameter("txtbuscar");

        if (dato != null) {
            List<Categoria> listaca = new CategoriaJDBCDAO().buscar(dato);
           
            request.setAttribute("categoria", listaca);

            String url = "/WEB-INF/categorias/index.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            
            
        }

    }

}
