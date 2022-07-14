
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
        String resultado = "";
        
        String mensaje ="";
         
        if (request.getSession().getAttribute("mensajeop") != null) {
            resultado = (String) request.getSession().getAttribute("mensajeop");
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
                        <%=resultado%>
                    </div>
                    <% request.getSession().removeAttribute("insercionCategoria"); %>
                    <% }%>
                    
                     
                    <div class="row">

                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">CATEGORIAS</div>
                                </div>

                                <div class="col-md-5 aling-center">
                                    <div class="row">
                                        <form action="categorias" method="GET">
                                            <div class="col-lg-12">
                                                <div class="input-group form">
                                                    <input type="text" class="form-control" name="txtbuscar" placeholder="Buscar...">
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-info" name="accion" value="buscar" type="submit">Buscar</button>
                                                    </span>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="panel-body ">                                    
                                    <a href="categorias?accion=nuevo" class="btn btn-success "><i class="glyphicon glyphicon-plus"></i> Nuevo Categoria</a>                                
                                    <table class="table table-hover responsive nowrap">
                                        <thead>

                                            <tr>
                                                <th>ID CATEGORIAS</th>
                                                <th>NOMBRE CATEGORIA</th>
                                                <th></th>
                                                <th></th>
                                                <th></th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="categorias" items="${categoria}" varStatus="status">
                                                <tr>
                                                    <td>${categorias.categoriaid}</td>
                                                    <td>${categorias.nombreCat}</td> 
                                                    <td><a href="categorias?accion=mostrar&idCat=${categorias.categoriaid}" class="btn btn-warning"><i class="glyphicon glyphicon-eye-open"></i> Mostrar </a></td>
                                                    <td><a href="categorias?accion=editar&idCat=${categorias.categoriaid}" class="btn btn-primary"><i class="glyphicon glyphicon-pencil"></i> Editar</a></td>

                                                    <td>
                                                        <form action="categorias" method="post">
                                                            <input type="hidden" name="accion" value="borrar">
                                                            <input type="hidden" name="idCat" value="${categorias.categoriaid}">                                                            
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
