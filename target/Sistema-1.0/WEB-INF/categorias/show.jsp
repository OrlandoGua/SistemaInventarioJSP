<%@page import="mx.com.oga.comercializadora.modelo.Productos"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.oga.comercializadora.modelo.Categoria"%>
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
    Categoria cat =(Categoria)request.getAttribute("categoria");
 
    %>
    
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <jsp:include page="../../WEB-INF/layouts/header.jsp"></jsp:include>

            <div class="page-content">
                <div class="row">
                <jsp:include page="../../WEB-INF/layouts/sidebar.jsp"></jsp:include>

                    <div class="col-md-10">
                                              
                        <div class="row">
                            <div class="col-md-12">
                                <div class="content-box-large">
                                    <div class="panel-heading">
                                        <div class="panel-title">CATEGORIAS<%=cat.getNombreCat()%></div>						

                                    </div>
                                    <div class="panel-body">
                                        
                                        <table class="table table-hover">
                                            <thead>

                                                <tr>
                                                    <th>CLAVE</th>
                                                    <th>NOMBRE</th>
                                                    <th>EXISTENCIA</th> 
                                                     <th>PROVEEDOR</th> 

                                                </tr>
                                            </thead>
                                            <tbody>
                                            <% for(Productos prod: cat.getProductos()) {%>
                                                <tr>
                                                    <td><%= prod.getProductoId() %></td>
                                                    <td><%= prod.getDescripcion() %></td> 
                                                     <td><%= prod.getExistencia() %></td> 
                                                    <td><%= prod.getProveedor().getNombreProv() %></td>
                                                   
                                                </tr>
                                            <%}%>   				            
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
