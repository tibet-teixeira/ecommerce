<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.product.Product"%>
<%@page import="model.bag.ShoppingBag"%>
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
            <%                Cookie shoppingBagCookie = ShoppingBag.getCookie(request);
                String shoppingBagList = shoppingBagCookie.getValue();
                List<ShoppingBagItem> shoppingBagItems = ShoppingBag.getShoppingBag(shoppingBagList);

            %>
            <div class="">
                <div class="form-shopping">
                    <span class="shopping-bag-title">Minha Sacola</span>

                    <%                        if (shoppingBagItems.isEmpty()) {
                            out.print("<div class='shopping-bag-empty'>");
                            out.print("<span>Sua sacola está vazia</span>");
                            out.print("</div>");
                        }
                    %>

                    <div class="shopping-bag-products">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col col-sm-8 section">
                                    <div class="col-sm-12 header-table-product">
                                        <div class="row">
                                            <div class="col-sm-4 border">Produto</div>
                                            <div class="col-sm-4 border">Quantidade</div>
                                            <div class="col-sm-2 border">Valor Unitário</div>
                                            <div class="col-sm-2 border">Total</div>
                                        </div>
                                    </div>
                                    <div class="col col-sm-12 shopping-bag-product border">

                                        <%
                                            Product product;
                                            int quantity;
                                            double totalPurchase = 0;

                                            for (ShoppingBagItem shoppingBagItem : shoppingBagItems) {
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
                                                out.print("<div class='col-sm-8'><a href=''><span class='shopping-bag-product-name'>" + product.getDescription() + "</span></a></div>");
                                                out.print("</div>");
                                                out.print("</div>");

                                                out.print("<div class='col-sm-4'>");
                                                out.print("<div class='row'>");

                                                out.print("<form action='atualizar_produto_sacola' class='col-sm-9' method='post'>");
                                                out.print("<div class='row'>");
                                                out.print("<div class='col-sm-9'>");
                                                out.print("<input type='text' name='id' value='" + product.getId() + "' hidden>");
                                                out.print("<input type='number' name='quantity' min='1' value='" + quantity + "' class='form-control' id='product-quantity' required>");
                                                out.print("</div>");
                                                out.print("<div class='col-sm-3'>");
                                                out.print("<span id='remove-product' class='remove-product'><button type='submit'><i class='fas fa-sync-alt'></i></button></span>");
                                                out.print("</div>");
                                                out.print("</form>");
                                                out.print("</div>");

                                                out.print("<div class='col-sm-3'>");
                                                out.print("<form action='remover_produto_sacola' method='post'>");
                                                out.print("<input type='text' name='id' value='" + product.getId() + "' hidden>");
                                                out.print("<span id='remove-product' class='remove-product'><button type='submit'><i class='fas fa-trash-alt'></i></button></span>");
                                                out.print("</form>");
                                                out.print("</div>");

                                                out.print("</div>");
                                                out.print("</div>");

                                                out.print("<div class='col-sm-2'><span class='shopping-bag-product-price'>R$ " + product.getPrice() + "</span></div>");
                                                out.print("<div class='col-sm-2'><span class='shopping-bag-product-price-total'>R$ " + product.getPrice() * quantity + "</span></div>");

                                                out.print("</div>");

                                            }


                                        %>

                                    </div>
                                </div>

                                <div class="col col-sm-4 aside">
                                    <span class="border-bottom border-dark">Resumo da Compra</span>

                                    <div class="shipping">
                                        <div class="container">
                                            <span>Consulte o frete</span>
                                            <div class="form-group shipping-calculate">
                                                <div class="row">
                                                    <label for="CEP" class="col-sm-2 col-form-label">CEP</label>
                                                    <div class="col-sm">
                                                        <input type="text" class="shipping-code form-control" id="shipping-code">
                                                    </div>
                                                </div>
                                                <button id="btn-shipping-calculate" class="btn btn-outline-dark btn-block btn-shipping-calculate">Calcular Frete</button>
                                            </div>

                                            <div class="shipping-information">
                                                <span>Normal</span>
                                                <span id="shipping-price" class="shipping-price">Preço: R$ 12,61</span>
                                                <span id="shipping-date" class="shipping-date">Previsto até: 28/11/2020</span>
                                            </div>
                                        </div>      
                                    </div>

                                    <div class="total-price">
                                        <span id="total-price">Total: R$ <% out.print(totalPurchase);%></span>
                                    </div>

                                    <div class="">
                                        <form action="comprar" method="post">
                                            <a href="index.jsp" class="keep-buying btn btn-outline-dark btn-block">Continuar comprando</a>
                                            <%
                                                out.print("<input type='text' name='purchaseList' id='purchaseList' value='" + shoppingBagList + "' hidden>");
                                                if (session.getAttribute("user") instanceof Customer) {
                                                    Customer customer = (Customer) session.getAttribute("user");
                                                    out.print("<input type='text' name='id_customer' id='id_customer' value='" + customer.getId() + "' hidden>");
                                                    out.print("<button type='submit' class='btn btn-dark btn-block'>Fechar pedido</button>");
                                                } else {
                                                    out.print("<button type='submit' class='btn btn-dark btn-block' disabled>Fechar pedido</button>");
                                                }
                                            %>

                                        </form>

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