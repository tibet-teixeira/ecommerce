<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.customer.Customer"%>
<%@page import="model.customer.CustomerModel"%>
<%@page import="model.purchase.Purchase"%>
<%@page import="model.purchase.PurchaseModel"%>
<%@page import="model.product.Product"%>
<%@page import="model.bag.ShoppingBagItem"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="includes.jsp" %>

        <title> NomeLoja | Sua loja favorita </title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="shopping-bag">
            <div class="">
                <div class="form-shopping">
                    <span class="shopping-bag-title">Meus pedidos</span>

                    <%                        Customer customer = (Customer) session.getAttribute("user");
                        int id = customer.getId();
                        List<Purchase> purchases = new PurchaseModel().get(customer);

                        if (purchases == null) {
                            out.print("<div class='shopping-bag-empty'>");
                            out.print("<span>Você não realizou nenhuma compra</span>");
                            out.print("</div>");
                        } else {
                    %>

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
                                            <div class="col-sm-2 border">Número do pedido</div>
                                            <div class="col-sm-4 border">Data da compra</div>
                                            <div class="col-sm-2 border">Quantidade de produtos</div>
                                            <div class="col-sm-4 border">Valor da compra</div>
                                        </div>
                                    </div>
                                    <div class="col col-sm-12 shopping-bag-product border">

                                        <%  double totalPrice;
                                            int quantity;
                                            for (Purchase purchase : purchases) {
                                                totalPrice = 0;
                                                quantity = 0;
                                                out.print("<div class='row'>");

                                                out.print("<div class='col-sm-2'>");
                                                out.print("<a href='compra.jsp?id=" + purchase.getId() + "'>");
                                                out.print("<span>" + purchase.getId() + "</span>");
                                                out.print("</a>");
                                                out.print("</div>");

                                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                                out.print("<div class='col-sm-4'>");
                                                out.print("<span class='shopping-bag-product-name'>" + simpleDateFormat.format(purchase.getDate()) + "</span>");
                                                out.print("</div>");

                                                for (ShoppingBagItem shoppingBagItem : purchase.getShoppingBagItems()) {
                                                    totalPrice += (shoppingBagItem.getProduct().getPrice() * shoppingBagItem.getQuantity());
                                                    quantity += shoppingBagItem.getQuantity();
                                                }

                                                out.print("<div class='col-sm-2'><span class='shopping-bag-product-price'> " + quantity + "</span></div>");
                                                out.print("<div class='col-sm-4'><span class='shopping-bag-product-price-total'>R$ " + totalPrice + "</span></div>");

                                                out.print("</div>");

                                            }


                                        %>

                                    </div>
                                </div>
                            </div>
                        </div>  
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>