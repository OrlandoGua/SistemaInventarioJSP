<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Comercializadora</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">
          <!-- FONT AWESOME -->
       
    </head>
    <%
        String result = "";
    %>
    <body>
        <jsp:useBean class="mx.com.oga.comercializadora.modelo.Ordenes" id="orden" scope="request"></jsp:useBean>
        <jsp:include page="../layouts/header.jsp"></jsp:include>
        <div class="page-content">
            <div class="row">
                <jsp:include page="../layouts/sidebar.jsp" ></jsp:include>
                
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12 panel-primary">
                            <div class="content-box-header panel-heading">
                                <div class="panel-title ">Pedido ${orden.ordenId}</div>
                            </div>
                            <div class="content-box-large box-with-header">
                                <a href="pedidos?accion=verPedidos" class="btn btn-primary"><- Volver a la lista</a>
                                    <br>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="col-sm-3">
                                            Fecha del pedido:
                                            <h4><%= new SimpleDateFormat("dd/MM/yyy").format(orden.getFechaOrden())  %></h4>
                                        </div>
                                        <div class="col-sm-3" >
                                            Vendedor: 
                                            <h4>${orden.empleado.nombre} ${orden.empleado.apellido}</h4>
                                        </div>
                                        <div class="col-sm-3" >
                                            cliente: 
                                            <h4>${orden.getCliente().nombrecia}</h4>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <table class="table">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>producto</th>
                                                        <th>cantidad</th>
                                                        <th>precio unitario</th>
                                                        <th>importe</th>
                                                    
                                                    </tr>
                                                </thead>
                                                <tbody style="height:10px; overflow:auto;">
                                                    <c:forEach items="${orden.detalles}" var="detalle">
                                                        <tr>
                                                            <td>${detalle.producto.descripcion}</td>
                                                            <td>${detalle.cantidad}</td>
                                                            <td>${detalle.producto.precioUnit}</td>
                                                            <td>${detalle.importe}</td>
                                                            
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </table>
                                    </div>
                                </div>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../layouts/footer.jsp"></jsp:include>
    </body>
</html>

