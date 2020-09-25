<%@page import="model.administrator.Administrator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.customer.Customer"%>

<!DOCTYPE html>
<html lang="pt_br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="includes.jsp" %>

        <title> NomeLoja | Sua loja favorita </title>
    </head>
    <body>
        <%@include file="header_admin.jsp" %>

        <div class="container-fluid main-body">
            <div class="container">
                <span class="adm-page-title">Página do Administrador</span>
                <div class="row">
                    <div class="col-sm-3">
                        <a href="add_produto.jsp">
                            <div class="card w-100">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <i class="fab fa-youtube"></i>
                                    </h5>
                                    <span class="content-card">Adicionar Produto</span>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-3">
                        <a href="add_categoria.jsp">
                            <div class="card w-100">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <i class="fab fa-youtube"></i>
                                    </h5>
                                    <span class="content-card">Adicionar Categoria</span>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-3">
                        <a href="visualizar_compras_adm.jsp">
                            <div class="card w-100">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <i class="fab fa-youtube"></i>
                                    </h5>
                                    <span class="content-card">Visualizar Compras Realizadas</span>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-3">
                        <a href="relatorio.jsp">
                            <div class="card w-100">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <i class="fab fa-youtube"></i>
                                    </h5>
                                    <span class="content-card">Gerar Relatório</span>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>   
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
