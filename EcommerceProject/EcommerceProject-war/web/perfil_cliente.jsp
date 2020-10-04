<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.customer.Customer"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="includes.jsp" %>

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
                            int id = 0;
                            String fullname = "";
                            String email = "";
                            String login = "";
                            String password = "";
                            String street = "";
                            String houseNumber = "";
                            String city = "";
                            String state = "";
                            String cep = "";

                            if (session.getAttribute("user") instanceof Customer) {
                                Customer customer = (Customer) session.getAttribute("user");
                                id = customer.getId();
                                fullname = customer.getName();
                                email = customer.getEmail();
                                login = customer.getLogin();
                                password = customer.getPassword();
                                street = customer.getStreet();
                                houseNumber = customer.getHouseNumber();
                                city = customer.getCity();
                                state = customer.getState();
                                cep = customer.getCep();

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
            </div>
        </header>
        <div class="perfil-cliente">
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
                        <div class="container">
                        <span class="register-title">Faça seu cadastro</span>
                        <span class="required-field">* Preenchimento obrigatório</span>
                        <form action="atualizar-cliente" method="post">

                            <%
                                out.print("<input type='text' class='form-control' name='id' value='" + id + "' hidden>");
                            %>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-user"></i></span>
                                </div>
                                <%
                                    out.print("<input type='text' class='form-control' name='name' id='name' placeholder='*Nome' aria-label='*Nome' aria-describedby='*Nome' value='" + fullname + "' required>");
                                %>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-envelope"></i></span>
                                </div>
                                <%
                                    out.print("<input type='email' class='form-control' name='email' id='email' placeholder='*Email' aria-label='*' aria-describedby='*' value='" + email + "' required>");
                                %>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-road"></i></span>
                                </div>
                                <%
                                    out.print("<input type='text' class='form-control' name='street' id='street' placeholder='*Rua' aria-label='*Rua' aria-describedby='*Rua' value='" + street + "' required>");
                                %>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-map-marker-alt"></i></span>
                                </div>
                                <%
                                    out.print("<input type='text' class='form-control' name='code' id='code' placeholder='*CEP' aria-label='*CEP' aria-describedby='*CEP' value='" + cep + "' required>");
                                %>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-house-user"></i></span>
                                </div>
                                <%
                                    out.print("<input type='text' class='form-control' name='number' id='number' placeholder='*Número' aria-label='*Número' aria-describedby='*Número' value='" + houseNumber + "' required>");
                                %>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-city"></i></span>
                                </div>
                                <%
                                    out.print("<input type='text' class='form-control' name='city' id='city' placeholder='*Cidade' aria-label='*Cidade' aria-describedby='*Cidade' value='" + city + "' required>");
                                %>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-flag-usa"></i></span>
                                </div>
                                <%
                                    out.print("<input type='text' class='form-control' name='state' id='state' placeholder='*Estado' aria-label='*Estado' aria-describedby='*Estado' value='" + state + "' required>");
                                %>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-fingerprint"></i></span>
                                </div>
                                <%
                                    out.print("<input type='text' class='form-control' name='login' id='login' placeholder='*Login' aria-label='*Login' aria-describedby='*Login' value='" + login + "' required>");
                                %>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-key"></i></span>
                                </div>
                                <%
                                    out.print("<input type='password' class='form-control' name='password' id='password' placeholder='*Senha' aria-label='*Senha' aria-describedby='*Senha' value='" + password + "' required>");
                                %>
                            </div>

                            <button type="submit" class="btn btn-dark btn-block">Alterar cadastro</button>
                        </form>
                    </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>