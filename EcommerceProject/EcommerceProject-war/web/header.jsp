<%@page import="model.customer.Customer"%>

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