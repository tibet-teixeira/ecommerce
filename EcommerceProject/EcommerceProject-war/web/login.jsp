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
        <div class="login-page">
            <div class="container">
                <div class="form-login">
                    <span class="login-title">Entre na sua conta</span>
                    <form action="login" method="post">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-user"></i></span>
                            </div>
                            <input type="text" class="form-control" name="login" id="login" placeholder="Login" aria-label="Login" aria-describedby="Login" required>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-key"></i></span>
                            </div>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Senha" aria-label="Senha" aria-describedby="Senha" required>
                            <div class="input-group-append">
                                <span class="input-group-text" id="basic-addon1"><i id="hide-password" class="fas fa-eye"></i></span>
                            </div>
                        </div>
                        <div class="row justify-content-between">
                            <%                            if (request.getAttribute("message") != null) {
                                    out.print("<span class='col col-sm login_error'>" + request.getAttribute("message") + "</span>");
                                }
                            %>
                            <span class="forgot-password col col-sm "><a href="">Esqueci minha senha</a></span>
                        </div>
                        <button type="submit" class="btn btn-dark btn-login btn-block">Entrar</button>
                    </form>
                </div>

                <div class="registration-area">
                    <span>Ainda não é cadastrado?</span>
                    <a href="cadastro_cliente.jsp" class="btn btn-outline-dark btn-block">Quero me cadastrar</a>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>