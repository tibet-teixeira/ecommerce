<!DOCTYPE html>
<html lang="pt_br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="includes.jsp" %>

        <title> NomeLoja | Sua loja favorita </title>
    </head>
    <body>
        <%@include file="header_without_menu.jsp" %>
        <div class="register-page">
            <div class="container">
                <div class="form-register">
                    <span class="register-title">Faça seu cadastro</span>
                    <span class="required-field">* Preenchimento obrigatório</span>
                    <form action="cadastro" method="post">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-user"></i></span>
                            </div>
                            <input type="text" class="form-control" name="name" id="name" placeholder="*Nome" aria-label="*Nome" aria-describedby="*Nome" required>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-envelope"></i></span>
                            </div>
                            <input type="email" class="form-control" name="email" id="email" placeholder="*Email" aria-label="*" aria-describedby="*" required>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-road"></i></span>
                            </div>
                            <input type="text" class="form-control" name="street" id="street" placeholder="*Rua" aria-label="*Rua" aria-describedby="*Rua" required>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-map-marker-alt"></i></span>
                            </div>
                            <input type="text" class="form-control" name="code" id="code" placeholder="*CEP" aria-label="*CEP" aria-describedby="*CEP" required>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-house-user"></i></span>
                            </div>
                            <input type="text" class="form-control" name="number" id="number" placeholder="*Número" aria-label="*Número" aria-describedby="*Número" required>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-city"></i></span>
                            </div>
                            <input type="text" class="form-control" name="city" id="city" placeholder="*Cidade" aria-label="*Cidade" aria-describedby="*Cidade" required>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-flag-usa"></i></span>
                            </div>
                            <input type="text" class="form-control" name="state" id="state" placeholder="*Estado" aria-label="*Estado" aria-describedby="*Estado" required>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-fingerprint"></i></span>
                            </div>
                            <input type="text" class="form-control" name="login" id="login" placeholder="*Login" aria-label="*Login" aria-describedby="*Login" required>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fas fa-key"></i></span>
                            </div>
                            <input type="password" class="form-control" name="password" id="password" placeholder="*Senha" aria-label="*Senha" aria-describedby="*Senha" required>
                        </div>

                        <button type="submit" class="btn btn-dark btn-block">Finalizar cadastro</button>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>