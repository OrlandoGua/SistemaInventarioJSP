/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.oga.comercializadora.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.oga.comercializadora.conexiones.GenerarSerie;
import mx.com.oga.comercializadora.dao.ClienteJDBCDAO;
import mx.com.oga.comercializadora.dao.DetalleOrdenesJDBCDAO;
import mx.com.oga.comercializadora.dao.EmpleadoJDBCDAO;
import mx.com.oga.comercializadora.dao.OrdenesJDBCDAO;
import mx.com.oga.comercializadora.dao.ProductoJDBCDAO;
import mx.com.oga.comercializadora.modelo.Clientes;
import mx.com.oga.comercializadora.modelo.DetalleOrdenes;
import mx.com.oga.comercializadora.modelo.Empleados;
import mx.com.oga.comercializadora.modelo.Ordenes;
import mx.com.oga.comercializadora.modelo.Productos;

/**
 *
 * @author oga
 */
@WebServlet(name = "PedidosController", urlPatterns = {"/pedidos"})
public class PedidosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "verPedidos":
                    verPedidos(request, response);
                    break;
                case "hacerPedido":
                    hacerPedido(request, response);
                    break;
                case "verPedido":
                    verPedido(request, response);
                    break;

            }
        } else {
            request.getRequestDispatcher("/WEB-INF/ordenes-compra/dashbord.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "addProducto":
                    addProducto(request, response);
                    break;
                case "DeleProducto":
                    deleteProducto(request, response);
                    break;
                case "terminarPedidos":
                    terminarPedidos(request, response);
                    break;

            }
        }

    }

    private void verPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrdenesJDBCDAO ordenDAO = new OrdenesJDBCDAO();
        List<Ordenes> ordenes = ordenDAO.listAll();
        request.setAttribute("ordenes", ordenes);

        request.getRequestDispatcher("/WEB-INF/ordenes-compra/index.jsp").forward(request, response);

    }

    private void hacerPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroSerie;
        Ordenes orden = (Ordenes) request.getSession().getAttribute("orden");

        OrdenesJDBCDAO ordenserie = new OrdenesJDBCDAO();

        List<Empleados> listEmpleados = new EmpleadoJDBCDAO().listAll();
        List<Clientes> listClientes = new ClienteJDBCDAO().listAll();
        List<Productos> listProductos = new ProductoJDBCDAO().listAll();

        request.setAttribute("empleados", listEmpleados);
        request.setAttribute("clientes", listClientes);
        request.setAttribute("productos", listProductos);

        numeroSerie = ordenserie.GenerarSerie();
        if (numeroSerie == null) {
            numeroSerie = "000000001";
            request.setAttribute("numeroSerie", numeroSerie);
        } else {
            int incrementar = Integer.parseInt(numeroSerie);
            GenerarSerie gs = new GenerarSerie();
            numeroSerie = gs.NumeroSerie(incrementar);
            request.setAttribute("numeroSerie", numeroSerie);
        }

        if (orden == null) {
            orden = new Ordenes();
            orden.setImporte(0.0);
            orden.setFechaOrden(new java.sql.Date(new java.util.Date().getTime()));

        } else {

            double ImporteOrden = 0.0;

            for (DetalleOrdenes detalles : orden.getDetalles()) {
                ImporteOrden += detalles.getImporte();
            }
            orden.setImporte(ImporteOrden);
        }
        request.getSession().setAttribute("orden", orden);

        request.getRequestDispatcher("/WEB-INF/ordenes-compra/new.jsp").forward(request, response);
    }

    private void addProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Ordenes orden = (Ordenes) request.getSession().getAttribute("orden");

        int idProducto = Integer.parseInt(request.getParameter("idProd"));
        int cantidad = Integer.parseInt(request.getParameter("cantProd"));

        Productos producto = new ProductoJDBCDAO().findById(idProducto);

        double importe = producto.getPrecioUnit() * cantidad;

        DetalleOrdenes detalle = new DetalleOrdenes();

        detalle.setCantidad(cantidad);
        detalle.setProducto(producto);
        detalle.setOrden(orden);
        detalle.setImporte(importe);

        if (orden == null) {
            orden = new Ordenes();
            request.getSession().setAttribute("orden", orden);

        }

        orden.getDetalles().add(detalle);
        response.sendRedirect(request.getContextPath() + "/pedidos?accion=hacerPedido");
    }

    private void deleteProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Ordenes orden = (Ordenes) request.getSession().getAttribute("orden");

        int idp = Integer.parseInt(request.getParameter("idp"));

        for (int i = 0; i < orden.getDetalles().size(); i++) {

            if (orden.getDetalles().get(i).getDetalleId() == idp) {
                orden.getDetalles().remove(i);
            }
        }
        response.sendRedirect(request.getContextPath() + "/pedidos?accion=hacerPedido");

    }

    private void terminarPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Ordenes orden = (Ordenes) request.getSession().getAttribute("orden");
        
        for(int i=0; i<orden.getDetalles().size();i++){
        Productos pr = new Productos();
        int cantidad = orden.getDetalles().get(i).getCantidad();
        int idproducto =orden.getDetalles().get(i).getProducto().getProductoId();
        
        ProductoJDBCDAO prodDAO = new ProductoJDBCDAO();
        pr=prodDAO.findById(idproducto);
        int exiActual = pr.getExistencia()-cantidad;
        prodDAO.actualizarExistencia(idproducto, exiActual);
        }
        
        
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int ideEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        String serie = request.getParameter("serie");

        Clientes cliente = new ClienteJDBCDAO().findById(idCliente);
        Empleados emple = new EmpleadoJDBCDAO().findById(ideEmpleado);

        orden.setCliente(cliente);
        orden.setEmpleado(emple);
        orden.setNumeroSerie(serie);

        OrdenesJDBCDAO daoOrden = new OrdenesJDBCDAO();

        Ordenes ordenCreada = daoOrden.Insertat(orden);

        if (ordenCreada != null) {
            request.getSession().setAttribute("ordenCreada", ordenCreada);
            response.sendRedirect("/Sistema/pedidos?accion=verPedidos");
        }
    }

    private void verPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idPedido = Integer.parseInt(request.getParameter("idPedido"));

        Ordenes orden = new OrdenesJDBCDAO().findById(idPedido);
        List<DetalleOrdenes> detalles = new DetalleOrdenesJDBCDAO().getDetalles(orden);
        orden.setDetalles(detalles);
        request.setAttribute("orden", orden);

        request.getRequestDispatcher("/WEB-INF/ordenes-compra/show.jsp").forward(request, response);
    }

}
