<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.customer.Customer"%>
<%@page import="model.customer.CustomerModel"%>
<%@page import="model.purchase.Purchase"%>
<%@page import="model.purchase.PurchaseModel"%>
<%@page import="model.product.Product"%>
<%@page import="model.bag.ShoppingBagItem"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt_br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="includes.jsp" %>
        <title> NomeLoja | Sua loja favorita </title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="shopping-bag">
            <%                String id = (String) request.getParameter("id");
                Purchase purchase = new PurchaseModel().get(Integer.parseInt(id));
            %>
            <div class="">
                <div class="form-shopping">
                    <% out.print("<span class='shopping-bag-title'>Compra Num: " + id + " </span>"); %>

                    <div class="shopping-bag-products">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col col-sm-2 aside-cliente">
                                    <div><a href="perfil_cliente.jsp">Meu Cadastro</a></div>
                                    <div><a href="compras_efetivadas_cliente.jsp">Meus pedidos</a></div>
                                    <div>
                                        <form action="remover-cliente">
                                            <%
                                                out.print("<input type='text' class='form-control' name='id' value='" + id + "' hidden>");
                                            %>
                                            <button type="submit" class="alert-link">Remover conta</button>
                                        </form>
                                    </div>
                                </div>
                                <div class="col col-sm-10 section">
                                    <div class="col-sm-12 header-table-product">
                                        <div class="row">
                                            <div class="col-sm-4 border">Produto</div>
                                            <div class="col-sm-2 border">Quantidade</div>
                                            <div class="col-sm-3 border">Valor Unitário</div>
                                            <div class="col-sm-3 border">Total</div>
                                        </div>
                                    </div>
                                    <div class="col col-sm-12 shopping-bag-product border">

                                        <%
                                            Product product;
                                            int quantity;
                                            double totalPurchase = 0;

                                            for (ShoppingBagItem shoppingBagItem : purchase.getShoppingBagItems()) {
                                                product = shoppingBagItem.getProduct();
                                                quantity = shoppingBagItem.getQuantity();
                                                totalPurchase += (product.getPrice() * quantity);

                                                out.print("<div class='row'>");

                                                out.print("<div class='col-sm-4'>");
                                                out.print("<div class='row'>");
                                                out.print("<div class='col-sm-4'><a href='produto.jsp?desc=" + product.getProductLink() + "'>");

                                                if (product.getPicture().equals("")) {
                                                    out.print("<img src='img/produto.png' alt='" + product.getDescription() + "'>");
                                                } else {
                                                    out.print("<img src='" + product.getPicture() + "' alt='" + product.getDescription() + "'>");
                                                }
                                                out.print("</a></div>");
                                                out.print("<div class='col-sm-8'><a href='produto.jsp?desc=" + product.getProductLink() + "'><span class='shopping-bag-product-name'>" + product.getDescription() + "</span></a></div>");
                                                out.print("</div>");
                                                out.print("</div>");

                                                out.print("<div class='col-sm-2'>");
                                                out.print("<span>" + quantity + "</span>");
                                                out.print("</div>");

                                                out.print("<div class='col-sm-3'><span class='shopping-bag-product-price'>R$ " + product.getPrice() + "</span></div>");
                                                out.print("<div class='col-sm-3'><span class='shopping-bag-product-price-total'>R$ " + product.getPrice() * quantity + "</span></div>");

                                                out.print("</div>");

                                            }


                                        %>

                                    </div>
                                </div>
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>