<%@page import="mx.com.oga.comercializadora.modelo.Clientes"%>
<%@page import="java.text.SimpleDateFormat"%>


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
        String tipoForm = (String) request.getAttribute("tipoForm");

        Clientes cli = (Clientes) request.getAttribute("cliente");

    %>
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <jsp:include page="../../WEB-INF/layouts/header.jsp"></jsp:include>

            <div class="page-content">
                <div class="row ">
                <jsp:include page="../../WEB-INF/layouts/sidebar.jsp"></jsp:include>

                    <div class="col-md-10 ">
                        <div class="row justify-content-center ">
                            <div class="col-md-6">

                                <div class="content-box-large">
                                    <div class="panel-heading">
                                        <div class="panel-title">ACTUALIZAR CLIENTE</div>

                                    </div>
                                    <div class="panel-body">

                                        <form class="form-horizontal" role="form" action="clientes" method="post">

                                            <div class="form-group"> 
                                                <input type="hidden" name="accion" value="<%=tipoForm%>">
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idCliente" 
                                                       value="<%= cli.getClienteId()%>" 
                                                       placeholder="Clave del cliente...">                                                          
                                            </div>
                                        </div>

                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" value="<%= cli.getCedulaRuc() %>" name="cedula" placeholder="Cedula ruc">                                                          
                                            </div>
                                        </div>

                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" value="<%= cli.getNombrecia() %>" name="nombrecia" placeholder="Nombrecia...">                                                          
                                            </div>
                                        </div>

                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" value="<%= cli.getNombreContacto() %>" name="contacto" placeholder="Nombre...">                                                          
                                            </div>
                                        </div>
                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" value="<%= cli.getDireccionCliente() %>" name="direccion" placeholder="Direccion...">                                                          
                                            </div>
                                        </div>
                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" value="<%= cli.getFax() %>" name="fax" placeholder="Fax...">                                                          
                                            </div>
                                        </div>
                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" value="<%= cli.getEmail() %>" name="email" placeholder="Email...">                                                          
                                            </div>
                                        </div>
                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" value="<%= cli.getCelulara() %>" name="cel" placeholder="Celular...">                                                          
                                            </div>
                                        </div>
                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="fijo" value="<%= cli.getFijo() %>" placeholder="Tel. Fijo...">                                                          
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-10">
                                                <button type="submit" class="btn btn-primary"><%= tipoForm%></button>
                                            </div>
                                        </div>
                                    </form>
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
