<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.product.Product"%>
<%@page import="model.purchase.Purchase"%>
<%@page import="model.purchase.PurchaseModel"%>
<%@page import="model.customer.Customer"%>
<%@page import="model.customer.CustomerModel"%>
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
        <%@include file="header_admin.jsp" %>
        <div class="shopping-bag">
            <div class="">
                <div class="form-shopping">
                    <span class="shopping-bag-title">Compras realizadas</span>
                    <div class="shopping-bag-products">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col col-sm-12">
                                    <div class="col-sm-12 header-table-product">
                                        <div class="row">
                                            <div class="col-sm-2 border">Número da compra</div>
                                            <div class="col-sm-4 border">Cliente</div>
                                            <div class="col-sm-3 border">Data da compra</div>
                                            <div class="col-sm-3 border">Valor total</div>
                                        </div>
                                    </div>
                                    <div class="col col-sm-12 shopping-bag-product border">
                                        <%  
                                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                            List<Purchase> purchases = new PurchaseModel().getAll();
                                            double totalPrice;
                                            int quantity;
                                            Customer customer;
                                            for (Purchase purchase : purchases) {
                                                customer = purchase.getCustomer();
                                                totalPrice = 0;
                                                quantity = 0;
                                                
                                                for (ShoppingBagItem shoppingBagItem : purchase.getShoppingBagItems()) {
                                                    totalPrice += (shoppingBagItem.getProduct().getPrice() * shoppingBagItem.getQuantity());
                                                    quantity += shoppingBagItem.getQuantity();
                                                }
                                                
                                                out.print("<div class='row'>");

                                                out.print("<div class='col-sm-2'>");
                                                out.print("<a href='visualizar_compra.jsp?id=" + purchase.getId() + "'>");
                                                out.print("<span>" + purchase.getId() + "</span>");
                                                out.print("</a>");
                                                out.print("</div>");

                                                
                                                out.print("<div class='col-sm-4'>");
                                                out.print("<span class='shopping-bag-product-name'>" + customer.getName() + "</span>");
                                                out.print("</div>");

                                                out.print("<div class='col-sm-3'><span class='shopping-bag-product-price'> " + simpleDateFormat.format(purchase.getDate()) + "</span></div>");
                                                
                                                out.print("<div class='col-sm-3'>");
                                                out.print("<div class='row'>");
                                                out.print("<div class='col col-sm-8'>");
                                                out.print("<span class='shopping-bag-product-price-total'>R$ " + totalPrice + "</span>");
                                                out.print("</div>");
                                                
                                                out.print("<div class='col col-sm-4'>");
                                                out.print("<form action='remover_compra' method='post'>");
                                                out.print("<input type='text' name='id' value='" + purchase.getId() + "' hidden>");
                                                out.print("<span id='remove-purchase' class='remove-purchase'><button type='submit'><i class='fas fa-trash-alt'></i></button></span>");
                                                out.print("</form>");
                                                out.print("</div>");
                                                
                                                        
                                                out.print("</div>");
                                                out.print("</div>");

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