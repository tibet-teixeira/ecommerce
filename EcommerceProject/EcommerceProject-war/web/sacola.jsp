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
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="../third_party/bootstrap-4.5.1/css/bootstrap.css">
        <link rel="stylesheet" href="../third_party/fontawesome-5.14.0/css/fontawesome.css">
        <link rel="stylesheet" href="../third_party/fontawesome-5.14.0/css/regular.css">
        <link rel="stylesheet" href="../third_party/fontawesome-5.14.0/css/all.css">

        <script type="text/javascript" src="../third_party/bootstrap-4.5.1/js/bootstrap.js"></script>

        <title> NomeLoja | Sua loja favorita </title>
    </head>
    <body>
        <header>
            <div class="container-fluid">
                <div class="main-header row justify-content-sm-center">
                    <div class="logo col col-sm-2"><a href="index.jsp"><img src="img/logo.png" class="img-fluid" alt="NomeLoja"></a></div>
                    <div class="search-input col col-sm-7">
                        <form action="" class="">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control mr-sm-2" placeholder="Buscar" aria-label="Buscar" aria-describedby="input-search">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="submit"><i class="fas fa-search"></i></button>
                                </div>
                            </div> 
                        </form>
                    </div>
                    <div class="user-links col col-sm-3 row">
                        <span class="col col-sm-auto welcome-msg"></span>
                        <div class="col col-sm-1"><a href="login.html"><i class="fas fa-user"></i></a></div>
                        <div class="col col-sm-1"><a href="sacola.html"><i class="fas fa-shopping-bag"></i></a></div>
                    </div>
                </div>
                <div class="category-menu container">
                    <div class="row">
                        <div class="col col-sm"><a href="http://">Novidades</a></div>
                        <div class="col col-sm"><a href="http://">Conforto</a></div>
                        <div class="col col-sm"><a href="http://">Feminino</a></div>
                        <div class="col col-sm"><a href="http://">Masculino</a></div>
                        <div class="col col-sm"><a href="http://">Infantil</a></div>
                        <div class="col col-sm"><a href="http://">Bolsas e AcessÃ³rios</a></div>
                        <div class="col col-sm"><a href="http://">CalÃ§ados</a></div>
                        <div class="col col-sm"><a href="http://">Esportivo</a></div>
                        <div class="col col-sm"><a href="http://">Moda Ãntima</a></div>
                        <div class="col col-sm"><a href="http://">Ofertas</a></div>
                    </div>   
                </div>
            </div>
        </header>
        <div class="shopping-bag">
            <%
                Cookie shoppingBagCookie = ShoppingBag.getCookie(request);
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



        <footer>
            <div class="container">
                <div class="row justify-content-sm-center">
                    <div class="col col-sm help">
                        <div class="row help-intern">
                            <div class="col col-sm">
                                <a href="http://">
                                    <i class="fas fa-store"></i>
                                    <span>Retire na Loja</span>
                                </a>
                            </div>
                            <div class="col col-sm">
                                <a href="http://">
                                    <i class="fas fa-credit-card"></i>
                                    <span>Pague Online</span>
                                </a>
                            </div>

                            <div class="w-100"></div>

                            <div class="col col-sm">
                                <a href="http://">
                                    <i class="fas fa-boxes"></i>
                                    <span>Entrega Expressa</span>
                                </a>
                            </div>
                            <div class="col col-sm">
                                <a href="http://">
                                    <i class="fas fa-comments"></i>
                                    <span>Fale Conosco</span>
                                </a>
                            </div>  
                        </div>


                    </div>
                    <div class="col col-sm about-us">
                        <span>Sobre NÃ³s</span>
                        <ul>
                            <li><a href="">Link 1</a></li>
                            <li><a href="">Link 2</a></li>
                            <li><a href="">Link 3</a></li>
                            <li><a href="">Link 4</a></li>
                            <li><a href="">Link 5</a></li>
                            <li><a href="">Link 6</a></li>

                        </ul>
                    </div>
                    <div class="col col-sm frequently-questions">
                        <span>Perguntas Frequentes</span>
                        <ul>
                            <li><a href="">Link 1</a></li>
                            <li><a href="">Link 2</a></li>
                            <li><a href="">Link 3</a></li>
                            <li><a href="">Link 4</a></li>
                            <li><a href="">Link 5</a></li>
                            <li><a href="">Link 6</a></li>

                        </ul>
                    </div>
                    <div class="col col-sm social-networks">
                        <span>Nos siga nas redes sociais</span>
                        <div class="row">
                            <div class="col col-sm"><a href=""><i class="fab fa-facebook-square"></i></a></div>
                            <div class="col col-sm"><a href=""><i class="fab fa-instagram"></i></a></div>
                            <div class="col col-sm"><a href=""><i class="fab fa-twitter"></i></a></div>
                            <div class="col col-sm"><a href=""><i class="fab fa-youtube"></i></a></div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>