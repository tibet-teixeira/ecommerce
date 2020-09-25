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
                <span class="add-category-title">Adicionar nova categoria</span>
                <div class="row">
                    <div class="form-login">
                        <form action="categoria" method="post">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="categoryName" id="categoryName" placeholder="Nome da Categoria" aria-label="Nome da Categoria" aria-describedby="Nome da Categoria" required>
                            </div>
                            <button type="submit" class="btn btn-dark btn-login btn-block">Cadastrar</button>
                        </form>
                    </div>
                </div>   
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
