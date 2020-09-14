package controller.administrator;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.administrator.Administrator;
import model.administrator.AdministratorModel;

/**
 *
 * @author Tibet
 */
@WebServlet(name = "UpdateAdministratorServlet", urlPatterns = {"/atualizar-admin"})
public class UpdateAdministratorServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Administrator administrator = new Administrator();
        administrator.setId(id);
        administrator.setName(name);
        administrator.setEmail(email);
        administrator.setLogin(login);
        administrator.setPassword(password);


        AdministratorModel administratorModel = new AdministratorModel();

        boolean success = false;

        try {
            administratorModel.update(administrator);
            success = true;
        } catch (Exception ex) {
            success = false;
        }

        if (success) {
            request.setAttribute("message", "Cadastro atualizado com sucesso");
            System.out.println("Cadastro atualizado com sucesso");
        } else {
            System.out.println("Não foi possível atualizar este cadastro");
            request.setAttribute("message", "Não foi possível atualizar este cadastro");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("home_adm.jsp");
        dispatcher.forward(request, response);
    }
}
