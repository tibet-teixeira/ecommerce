<%@page import="model.administrator.Administrator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.customer.Customer"%>
<%@page import="model.category.CategoryModel"%>
<%@page import="model.category.Category"%>
<%@page import="java.util.List"%>

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
                        <form action="produto" method="post">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="productName" id="productName" placeholder="Nome do Produto" aria-label="Nome do Produto" aria-describedby="Nome do Produto" required>
                            </div>
                            <div class="input-group mb-3">
                                <input type="number" class="form-control" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" name="price" id="price" placeholder="Preço" aria-label="Preço" aria-describedby="Preço" required>
                            </div>
                            <div class="input-group mb-3">
                                <input type="number" min=1 class="form-control" name="quantity" id="quantity" placeholder="Quantidade" aria-label="Quantidade" aria-describedby="Quantidade" required>
                            </div>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" name="photo" id="photo">
                                <label class="custom-file-label" for="customFile">Escolha o arquivo</label>
                            </div>
                            <select class="custom-select input-group mb-3" name="category" multiple required>
                                <option disabled>Selecione a(s) categoria(s)</option>
                                <%                                    List<Category> categories = new CategoryModel().getAll();
                                    for (Category category : categories) {
                                        out.print("<option value='" + category.getId() + "'>" + category.getDescription() + "</option>");
                                    }
                                %>
                            </select>
                            <button type="submit" class="btn btn-dark btn-login btn-block">Cadastrar</button>
                        </form>
                    </div>
                </div>   
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
