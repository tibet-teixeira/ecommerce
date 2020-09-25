<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.product.Product"%>
<%@page import="model.product.ProductModel"%>
<%@page import="model.customer.Customer"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt_br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="includes.jsp" %>

        <title> NomeLoja | Sua loja favorita </title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="container-fluid main-body">
            <%                String description = (String) request.getParameter("desc");
                description = description.replace("-", " ");
                Product product = new ProductModel().get(description);
            %>

            <div class="main-products container">
                <span class="title-product-page"><% out.print(product.getDescription()); %></span>

                <div class="main-product">
                    <%
                        if (product.getPicture().equals("")) {
                            out.print("<img src='img/produto.png' alt='" + product.getDescription() + "'>");
                        } else {
                            out.print("<img src='" + product.getPicture() + "' alt='" + product.getDescription() + "'>");
                        }
                    %>

                    <div class='main-product-info'>
                        <span class="main-product-price">R$ <% out.print(product.getPrice());%> </span>
                    </div>
                    <form action="adicionar_produto_sacola">
                        <% out.print("<input name='id' type='text' value='" + product.getId() + "' hidden>");%>
                        <button type="submit" class="btn btn-dark btn-login btn-block">Adicionar a sacola</button>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
