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

        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="third_party/bootstrap-4.5.1/css/bootstrap.css">
        <link rel="stylesheet" href="third_party/fontawesome-5.14.0/css/fontawesome.css">
        <link rel="stylesheet" href="third_party/fontawesome-5.14.0/css/regular.css">
        <link rel="stylesheet" href="third_party/fontawesome-5.14.0/css/all.css">

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
                        <%
                            String fullname = "";
                            if (session.getAttribute("user") instanceof Customer) {
                                Customer customer = (Customer) session.getAttribute("user");
                                fullname = customer.getName();
                                String username[] = fullname.split(" ");

                                out.print("<span class='col col-sm-auto welcome-msg'>Bem vindo " + username[0] + "</span>");
                                out.print("<div class='col col-sm-1'><a href='perfil_cliente.jsp'><i class='fas fa-user'></i></a></div>");
                                out.print("<div class='col col-sm-1'><a href='sacola.jsp'><i class='fas fa-shopping-bag'></i></a></div>");
                                out.print("<div class='col col-sm-1'><a href='logout'><i class='fas fa-sign-out-alt'></i></a></div>");

                            } else {
                                out.print("<span class='col col-sm-9 welcome-msg'></span>");
                                out.print("<div class='col col-sm-1'><a href='login.jsp'><i class='fas fa-user'></i></a></div>");
                                out.print("<div class='col col-sm-1'><a href='sacola.jsp'><i class='fas fa-shopping-bag'></i></a></div>");
                            }
                        %>
                    </div>
                </div>
                <div class="category-menu container">
                    <div class="row">
                        <div class="col col-sm"><a href="">Novidades</a></div>
                        <div class="col col-sm"><a href="">Conforto</a></div>
                        <div class="col col-sm"><a href="">Feminino</a></div>
                        <div class="col col-sm"><a href="">Masculino</a></div>
                        <div class="col col-sm"><a href="">Infantil</a></div>
                        <div class="col col-sm"><a href="">Bolsas e Acessórios</a></div>
                        <div class="col col-sm"><a href="">Calçados</a></div>
                        <div class="col col-sm"><a href="">Esportivo</a></div>
                        <div class="col col-sm"><a href="">Moda Íntima</a></div>
                        <div class="col col-sm"><a href="">Ofertas</a></div>
                    </div>   
                </div>
            </div>
        </header>

        <div class="container-fluid main-body">
            <%
                String description = (String) request.getParameter("desc");
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
                        <% out.print("<input name='id' type='text' value='" + product.getId() + "' hidden>"); %>
                        <button type="submit" class="btn btn-dark btn-login btn-block">Adicionar a sacola</button>
                    </form>
                </div>
            </div>
        </div>

        <footer>
            <div class="container">
                <div class="row justify-content-sm-center">
                    <div class="col col-sm help">
                        <div class="row help-intern">
                            <div class="col col-sm">
                                <a href="">
                                    <i class="fas fa-store"></i>
                                    <span>Retire na Loja</span>
                                </a>
                            </div>
                            <div class="col col-sm">
                                <a href="">
                                    <i class="fas fa-credit-card"></i>
                                    <span>Pague Online</span>
                                </a>
                            </div>

                            <div class="w-100"></div>

                            <div class="col col-sm">
                                <a href="">
                                    <i class="fas fa-boxes"></i>
                                    <span>Entrega Expressa</span>
                                </a>
                            </div>
                            <div class="col col-sm">
                                <a href="">
                                    <i class="fas fa-comments"></i>
                                    <span>Fale Conosco</span>
                                </a>
                            </div>  
                        </div>


                    </div>
                    <div class="col col-sm about-us">
                        <span>Sobre Nós</span>
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
