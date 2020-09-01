package controller.administrator;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.administrator.Administrator;
import model.administrator.AdministratorModel;

/**
 *
 * @author Tibet
 */
@WebServlet(name = "LoginAdministratorServlet", urlPatterns = {"/loginadm"})
public class LoginAdministratorServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AdministratorModel administratorModel = new AdministratorModel();
        Administrator administrator = null;

        boolean success = false;

        try {

            success = administratorModel.identifier(login, password);

            if (success) {
                administrator = administratorModel.get(login);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", administrator);
            }
        } catch (Exception ex) {
            success = false;
        }

        if (success) {
            System.out.println("Login realizado com sucesso");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("message", "Login ou senha inválida");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login_adm.jsp");
            dispatcher.forward(request, response);
            System.out.println("Login ou senha inválida");
        }
    }

}
