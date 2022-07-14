
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
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <jsp:include page="../../WEB-INF/layouts/header.jsp"></jsp:include>

            <div class="page-content">
                <div class="row">
                <jsp:include page="../../WEB-INF/layouts/sidebar.jsp"></jsp:include>

                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="content-box-large" >
                                    <div class="panel-heading" >
                                        <div class="panel-title">
                                            DASHBOARD PEDIDOS
                                        </div>                                        
                                    </div>
                                    <div class="panel-body">
                                        <div class="col-sm-6 mb-4">
                                            <a href="pedidos?accion=verPedidos" class="my-3 btn btn-large btn-primary">VER PEDIDOS</a>
                                        </div>
                                        <div class="col-sm-6  mt-4">
                                             <a href="pedidos?accion=hacerPedido" class="my-3 btn btn-large btn-primary">HACER PEDIDOS</a>
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
