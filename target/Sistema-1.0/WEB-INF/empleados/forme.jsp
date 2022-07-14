<%@page import="mx.com.oga.comercializadora.modelo.Empleados"%>

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

        Empleados emp = null;
        if (tipoForm.equals("actualizar")) {
            emp = (Empleados) request.getAttribute("empleado");
        }
    %>
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <jsp:include page="../../WEB-INF/layouts/header.jsp"></jsp:include>

            <div class="page-content">
                <div class="row">
                <jsp:include page="../../WEB-INF/layouts/sidebar.jsp"></jsp:include>

                    <div class="col-md-10">
                        <div class="row justify-content-center">
                            <div class="col-md-6">

                                <div class="content-box-large">
                                    <div class="panel-heading">
                                        <div class="panel-title">CREAR EMPLEADO</div>

                                    </div>
                                    <div class="panel-body">

                                        <form class="form-horizontal" role="form" action="empleados" method="post">

                                            <div class="form-group"> 
                                                <input type="hidden" name="accion" value="<%=tipoForm%>">
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="idEmp" placeholder="Clave de empleado...">                                                          
                                            </div>
                                        </div>

                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="nombre" placeholder="Nombre...">                                                          
                                            </div>
                                        </div>

                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="apellido" placeholder="Apellido...">                                                          
                                            </div>
                                        </div>
                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="date" class="form-control" name="fecha" >                                                          
                                            </div>
                                        </div>
                                        <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="reportaA" placeholder="Reporta a...">                                                          
                                            </div>
                                        </div>
                                            <div class="form-group">                                               
                                            <div class="col-sm-12">
                                                <input type="number" class="form-control" name="extension" placeholder="Extension">                                                          
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
