<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.administrator.Administrator"%>

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
                    <div class="logo col col-sm-2"><a href="home_adm.jsp"><img src="img/logo.png" class="img-fluid" alt="NomeLoja"></a></div>
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

                            if (session.getAttribute("user") instanceof Administrator) {
                                Administrator administrator = (Administrator) session.getAttribute("user");
                                id = administrator.getId();
                                fullname = administrator.getName();
                                email = administrator.getEmail();
                                login = administrator.getLogin();
                                password = administrator.getPassword();

                                String username[] = fullname.split(" ");

                                out.print("<span class='col col-sm-auto welcome-msg'>Bem vindo " + username[0] + "</span>");
                                out.print("<div class='col col-sm-1'><a href='perfil_admin.jsp'><i class='fas fa-user'></i></a></div>");
                                out.print("<div class='col col-sm-1'><a href='logoutadm'><i class='fas fa-sign-out-alt'></i></a></div>");

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
        <div class="container-fluid">
            <div class="">
                <div class="left-menu">
                    <div class="container">
                        <ul class="opcoes_menu_cliente">
                            <li><a href="perfil_cliente.html">Meu Cadastro</a></li>
                            <li><a href="compras_efetivadas_cliente.html">Meus pedidos</a></li>
                            <li>
                                <form action="remover-admin">
                                    <%
                                        out.print("<input type='text' class='form-control' name='id' value='" + id + "' hidden>");
                                    %>
                                    <button type="submit" class="alert-link">Remover conta</button>
                                </form>
                            </li>
                        </ul>  
                    </div>  
                </div>
                <div class="register-page">
                    <div class="container">
                        <div class="form-register">
                            <span class="register-title">Faça seu cadastro</span>
                            <span class="required-field">* Preenchimento obrigatório</span>
                            <form action="atualizar-admin" method="post">

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