<!DOCTYPE html>
<html lang="pt_br">
    <head>
        <meta charset="UTF-8">
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
                    <div class="logo col col-sm-2"><a href="index.html"><img src="img/logo.png" class="img-fluid" alt="NomeLoja"></a></div>
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
                        <div class="col col-sm-1"><a href="login.jsp"><i class="fas fa-user"></i></a></div>
                        <div class="col col-sm-1"><a href="sacola.jsp"><i class="fas fa-shopping-bag"></i></a></div>
                    </div>
                </div>
            </div>
        </header>
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