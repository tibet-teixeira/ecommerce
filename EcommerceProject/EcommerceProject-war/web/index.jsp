<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.product.Product"%>
<%@page import="model.product.ProductModel"%>
<%@page import="model.customer.Customer"%>
<%@page import="model.bag.ShoppingBag"%>

<% final int NUMBER_PRODUCTS_INDEX = 12;%>

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
        <%            if (ShoppingBag.getCookie(request) == null) {
                Cookie cookie = new Cookie(ShoppingBag.COOKIE_KEY, "");
                response.addCookie(cookie);
            }
        %>

        <div class="container-fluid main-body">
            <div id="carouselExampleControls" class="carousel slide banner" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100 img-fluid" src="img/banner.png" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100 img-fluid" src="img/banner.png" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100 img-fluid" src="img/banner.png" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <div class="main-products container">
                <%
                    ProductModel productModel = new ProductModel();
                    List<Product> products = productModel.getAllIndex();

                    if (products.size() > NUMBER_PRODUCTS_INDEX) {
                        for (int i = NUMBER_PRODUCTS_INDEX; i <= products.size(); i++) {
                            products.remove(NUMBER_PRODUCTS_INDEX);
                        }
                    }
                %>
                <span class="title-home-page">Alguns dos nossos produtos</span>

                <div class="row">
                    <%
                        for (Product product : products) {
                            out.print("<div class='col col-sm-3 main-product'>");
                            out.print("<a href='produto.jsp?desc=" + product.getProductLink() + "'>");

                            if (product.getPicture().equals("")) {
                                out.print("<img src='img/produto.png' alt='" + product.getDescription() + "'>");
                            } else {
                                out.print("<img src='" + product.getPicture() + "' alt='" + product.getDescription() + "'>");
                            }

                            out.print("<div class='main-product-info'>");
                            out.print("<span class='main-product-name'>" + product.getDescription() + "</span>");
                            out.print("<span class='main-product-price'>R$ " + product.getPrice() + "</span>");
                            out.print("</div>");
                            out.print("</a>");
                            out.print("</div>");
                        }
                    %>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
