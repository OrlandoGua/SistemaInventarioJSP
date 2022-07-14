<%@page import="mx.com.oga.comercializadora.modelo.Ordenes"%>
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
    <body>
        
        <% 
            Ordenes orden = null;
            if (request.getSession().getAttribute("orden")!= null) {
                 orden = (Ordenes)request.getSession().getAttribute("orden");
            }
            
        %>

        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <jsp:include page="../../WEB-INF/layouts/header.jsp"></jsp:include>

            <div class="page-content">
                <div class="row">
                <jsp:include page="../../WEB-INF/layouts/sidebar.jsp"></jsp:include>

                    <div class="col-md-10"> 
                        <%
                        if (orden !=null) {%>
                    <div class="alert alert-success alert-dismissable fade in">
                        <a href="#" class="close" data-dismiss="alert" arial-label="close">&times;</a>
                        Se ha creado el pedido con folio <%= orden.getOrdenId() %>

                    </div>
                    <% request.getSession().removeAttribute("orden"); %>
                    <% }%>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="content-box-large">
                                    <div class="panel-heading" >
                                        <div class="panel-title"> 
                                            TABLA  DE PEDIDOS
                                        </div>
                                    </div> 
                                    <div class="panel-body">
                                        <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>FECHA DE PEDIDO</th>
                                                <th>CLIENTE</th>
                                                <th>IMPORTE</th>
                                                <th>ACCIONES</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="ordenes" items="${ordenes}" >
                                            <tr> 
                                                <td>${ordenes.getOrdenId()}</td>
                                                <td>${ordenes.getFechaOrden()}</td>
                                                <td>${ordenes.getCliente().getNombrecia()}</td>
                                                <td>${ordenes.getImporte()}</td>
                                                <td><a href="pedidos?accion=verPedido&idPedido=${ordenes.getOrdenId()}" class="btn btn-info btn-xs" >Ver detalles</a></td>
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
            <jsp:include page="../../WEB-INF/layouts/footer.jsp"></jsp:include>
    </body>
</html>
