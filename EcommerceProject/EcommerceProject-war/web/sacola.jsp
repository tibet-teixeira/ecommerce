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
                out.print("<h3>" + shoppingBagCookie.getValue() + "</h3>");

            %>
            <div class="">
                <div class="form-shopping">
                    <span class="shopping-bag-title">Minha Sacola</span>

                    <div class="shopping-bag-empty">
                        <span>Sua sacola estÃ¡ vazia :(</span>
                    </div>

                    <div class="shopping-bag-products">
                        <form action="">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col col-sm-8 section">
                                        <div class="col-sm-12 header-table-product">
                                            <div class="row">
                                                <div class="col-sm-4 border">Produto</div>
                                                <div class="col-sm-4 border">Quantidade</div>
                                                <div class="col-sm-2 border">Valor unitÃ¡rio</div>
                                                <div class="col-sm-2 border">Total</div>
                                            </div>
                                        </div>

                                        <div class="col col-sm-12 shopping-bag-product border">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <div class="row">
                                                        <div class="col-sm-4"><a href=""><img src="img/produto.png" alt=""></a></div>
                                                        <div class="col-sm-8"><a href=""><span class="shopping-bag-product-name">Produto 1</span></a></div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="row">
                                                        <div class="col-sm-9">
                                                            <input type="number" name="product-quantity" min="1" class="form-control" id="product-quantity" required>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <span id="remove-product" class="remove-product"><i class="fas fa-trash-alt"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2"><span class="shopping-bag-product-price">R$ 25,80</span></div>
                                                <div class="col-sm-2"><span class="shopping-bag-product-price-total">R$ 25,80</span></div>
                                            </div>
                                        </div>

                                        <div class="col col-sm-12 shopping-bag-product border">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <div class="row">
                                                        <div class="col-sm-4"><a href=""><img src="img/produto.png" alt=""></a></div>
                                                        <div class="col-sm-8"><a href=""><span class="shopping-bag-product-name">Produto 1</span></a></div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="row">
                                                        <div class="col-sm-9">
                                                            <input type="number" name="product-quantity" min="1" class="form-control" id="product-quantity" required>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <span id="remove-product" class="remove-product"><i class="fas fa-trash-alt"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2"><span class="shopping-bag-product-price">R$ 25,80</span></div>
                                                <div class="col-sm-2"><span class="shopping-bag-product-price-total">R$ 25,80</span></div>
                                            </div>
                                        </div>

                                        <div class="col col-sm-12 shopping-bag-product border">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <div class="row">
                                                        <div class="col-sm-4"><a href=""><img src="img/produto.png" alt=""></a></div>
                                                        <div class="col-sm-8"><a href=""><span class="shopping-bag-product-name">Produto 1</span></a></div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="row">
                                                        <div class="col-sm-9">
                                                            <input type="number" name="product-quantity" min="1" class="form-control" id="product-quantity" required>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <span id="remove-product" class="remove-product"><i class="fas fa-trash-alt"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2"><span class="shopping-bag-product-price">R$ 25,80</span></div>
                                                <div class="col-sm-2"><span class="shopping-bag-product-price-total">R$ 25,80</span></div>
                                            </div>
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
                                                    <span id="shipping-price" class="shipping-price">PreÃ§o: R$ 12,61</span>
                                                    <span id="shipping-date" class="shipping-date">Previsto atÃ©: 28/11/2020</span>
                                                </div>
                                            </div>      
                                        </div>

                                        <div class="total-price">
                                            <span id="total-price">Total: R$ 90,01</span>
                                        </div>

                                        <div class="">
                                            <a href="index.html" class="keep-buying btn btn-outline-dark btn-block">Continuar comprando</a>
                                            <button type="submit" class="btn btn-dark btn-block">Fechar pedido</button>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </form>   
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>