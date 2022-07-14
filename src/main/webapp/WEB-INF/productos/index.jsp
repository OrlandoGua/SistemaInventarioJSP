



<%@page import="mx.com.oga.comercializadora.modelo.Productos"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="java.util.List"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>
<!DOCTYPE html> 
<html>
    <head>
        <title>Bootstrap Admin Theme v3</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>

    <%

        List<Productos> listProductos = null;

        if (request.getAttribute("listaProducto") != null) {
            listProductos = (List<Productos>) request.getAttribute("listaProducto");
        }

        String resultado = "";

        if (request.getSession().getAttribute("opProd") != null) {
            resultado = (String) request.getSession().getAttribute("opProd");
        }
    %>

    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <jsp:include page="../../WEB-INF/layouts/header.jsp"></jsp:include>

            <div class="page-content">
                <div class="row">
                <jsp:include page="../../WEB-INF/layouts/sidebar.jsp"></jsp:include>

                    <div class="col-md-10">
                    <%
                        if (!resultado.isEmpty()) {%>
                    <div class="alert alert-success alert-dismissable fade in">
                        <a href="#" class="close" data-dismiss="alert" arial-label="close">&times;</a>
                        <%= resultado %>

                    </div>
                    <% request.getSession().removeAttribute("opProd"); %>
                    <% }%>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">TABLA DE PRODUCTOS</div>						

                                </div>
                                <div class="panel-body">
                                    <a href="productos?accion=nuevo" class="btn  btn-success "><i class="glyphicon glyphicon-plus"></i> NUEVO PRODUCTO</a>
                                    <table class="table table-hover">
                                        <thead>

                                            <tr>
                                                <th>ID PRODUCTO</th>
                                                <th>PROVEEDOR</th> 
                                                <th>CATEGORIA</th>  
                                                <th>DESCRIPCION</th>  
                                                <th>PRECIO</th>                                                
                                                <th>EXISTENCIA</th>
                                                <th></th>
                                                <th></th>  


                                            </tr>
                                        </thead>
                                        <tbody>
                                              <c:forEach var="productos" items="${listaProducto}" varStatus="status">
                                          
                                            <tr>
                                                <td>${ productos.getProductoId()}</td>
                                                <td>${ productos.getProveedor().getNombreProv()}</td>
                                                <td>${ productos.getCategoria().getNombreCat()}</td> 
                                                <td>${ productos.getDescripcion() }</td> 
                                                <td><fmt:formatNumber value="${productos.getPrecioUnit() }" type="currency" /></td> 
                                                <td>${ productos.getExistencia() }</td> 
                                                <td><a href="productos?accion=editar&idProd=${productos.getProductoId() }" class="btn btn-primary"><i class="glyphicon glyphicon-pencil"></i> Editar</a></td>
                                                <td>
                                                    <form action="productos" method="post">
                                                        <input type="hidden" name="accion" value="borrar">
                                                        <input type="hidden" name="idProd" value="${ productos.getProductoId() }">                                                            
                                                        <button type="submit" value="borrar" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Borrar</button>
                                                    </form>
                                                </td>
                                            </tr>
                                            </c:forEach>  				            
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <jsp:include page="../../WEB-INF/layouts/footer.jsp"></jsp:include>
    </body>
</html>
