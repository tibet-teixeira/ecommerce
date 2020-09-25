<%@page import="model.administrator.Administrator"%>

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
                    if (session.getAttribute("user") instanceof Administrator) {
                        String fullname = "";
                        Administrator administrator = (Administrator) session.getAttribute("user");
                        fullname = administrator.getName();
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