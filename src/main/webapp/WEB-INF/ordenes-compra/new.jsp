<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Bootstrap Admin Theme v3</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/91471c0d24.js" crossorigin="anonymous"></script>

        <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>             
       <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>      
        
        <script>
            $(document).ready(function () {
                $("tr #btnDelete").click(function () {
                    var idp = $(this).parent().find("#idp").val();
                    eliminar(idp);
                    swal({
                        title: "¿ Desea eliminar ?",
                        text: "Una vez eliminado, ¡no podrá recuperar esta informacion!",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    })
                            .then((willDelete) => {
                                if (willDelete) {

                                    swal("¡ Individuo ! ¡ Tu registro ha sido eliminado !", {
                                        icon: "success",
                                    }).then((willDelete) => {
                                        if (willDelete) {
                                            parent.location.href = "pedidos?accion=hacerPedido";
                                        }
                                    });
                                } else {
                                    swal("¡Tu registro no se ha eliminado!");
                                }
                            });

                });
            });

            function eliminar(idp) {
                var url = "pedidos?accion=DeleProducto";
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: "idp=" + idp,
                    success: function (data, textStatus, jqXHR) {
                        
                    }
                });
            }
        </script>
        -->
    </head> 
    <body>
        <jsp:useBean class="mx.com.oga.comercializadora.modelo.Ordenes" id="orden" scope="session"></jsp:useBean>
        <jsp:include page="../../WEB-INF/layouts/header.jsp"></jsp:include>      
            <div class="page-content">
                <div class="row">
                <jsp:include page="../../WEB-INF/layouts/sidebar.jsp"></jsp:include>

                    <div class="col-md-10">                    
                        <div class="row">
                            <div class="col-md-12 panel-primary">
                                <div class="content-box-header panel-heading">
                                    <div class="panel-title">Realizar Pedidos</div>
                                </div>
                                <div class="content-box-large box-with-header">
                                    <form action="pedidos" method="post" >
                                        <input type="hidden" name="accion" value="terminarPedidos">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        Fecha del pedido:
                                                        <input type="text" class="form-control" name="fechaPedido" placeholder="Fecha" value="<%= new SimpleDateFormat("dd/MM/yy").format(orden.getFechaOrden())%>" >                                                        
                                                </div> 
                                                <div class="col-sm-6">
                                                         Numero de compra:
                                            <input name="serie" type="text" class="form-control" value="${numeroSerie}" placeholder="Clave producto">                                             
                                                </div>
                                            </div>
                                            <div class="row" style="margin-top: 15px;">
                                                <div class="col-sm-6">
                                                    Elija un empleado:
                                                    <select name="idEmpleado" id="" class="form-control" >
                                                        <c:forEach var="empleado" items="${empleados}">
                                                            <option value="${empleado.empleadoId}" >${empleado.getNombreCompleto()}</option>
                                                        </c:forEach>    
                                                    </select>                                                        
                                                </div> 
                                                <div class="col-sm-6">
                                                    Elija un cliente:
                                                    <select name="idCliente" id="" class="form-control" >
                                                        <c:forEach var="cliente" items="${clientes}">
                                                            <option value="${cliente.clienteId}" >${cliente.nombrecia}</option>
                                                        </c:forEach>     
                                                    </select>                                                            
                                                </div> 
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div  class="col-md-12" style="text-align: center; margin-top: 15px;" >
                                                <input type="submit" class="btn btn-lg btn-default" value="TERMINAR PEDIDO">
                                            </div>
                                            <div  class="col-md-12" style="text-align: center; margin-top: 15px;" >
                                                <p style="font-size: 60px; color:blue; vertical-align: baseline">

                                                    <fmt:formatNumber value="${orden.importeRedondo}" type="currency" />
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <br>
                                <hr>
                                <div class="row">
                                    <form action="pedidos" method="post">
                                        <input type="hidden" name="accion" value="addProducto">                                        
                                        <div class="col-sm-4">
                                            Seleccione un producto:
                                            <select name="idProd" id="" class="form-control">
                                                <option value="" selected="true" disabled="true" >Seleccione un producto...</option>
                                                <c:forEach var="producto" items="${productos}">
                                                    <option value="${producto.productoId}" >${producto.descripcion}</option>
                                                </c:forEach> 
                                            </select>
                                        </div>
                                        <div class="col-sm-3">
                                            Cantidad:
                                            <input type="number" class="form-control" name="cantProd" placeholder="Cantidad">
                                        </div>
                                        <div class="col-sm-2">
                                            <br>                                                 
                                            <input type="submit" class=" btn btn-success" value="Agregar Producto">
                                        </div>
                                    </form>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-md-12">
                                        <table class="table table-hover responsive nowrap">
                                            <thead>
                                                <tr>
                                                    <th>NRO</th>
                                                    <th>PRODUCTO</th>
                                                    <th>CANTIDAD</th>
                                                    <th>PRECIO UNITARIO</th>
                                                    <th>IMPORTE</th>
                                                    <th></th>


                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${orden.detalles}" var="detalle" >
                                                    <tr>
                                                         <td>${detalle.detalleId }</td> 
                                                        <td>${detalle.producto.descripcion }</td> 
                                                        <td>${detalle.cantidad}</td>                                                     
                                                        <td><fmt:formatNumber value="${detalle.producto.precioUnit}" type="currency" /></td> 
                                                        <td><fmt:formatNumber value="${detalle.importeRedondeado}" type="currency" /></td>                                          
                                                        <td>
                                                            <input type="hidden" id="idp" value="${detalle.getDetalleId()}" >
                                                            <a href="#" id="btnDelete" class="btn btn-danger" ><i class="fas fa-trash-alt"></i></a>                                                               
                                                        </td> 
                                                        <td>
                                                        <form action="pedidos" method="post">
                                                            <input type="hidden" name="accion" value="DeleProducto">
                                                            <input type="hidden" name="idp" value="${detalle.getDetalleId()}">                                                            
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
