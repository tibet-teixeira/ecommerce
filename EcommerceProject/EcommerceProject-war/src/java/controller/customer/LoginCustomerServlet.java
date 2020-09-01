package controller.customer;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customer.Customer;
import model.customer.CustomerModel;

/**
 *
 * @author Tibet
 */
@WebServlet(name = "LoginCustomerServlet", urlPatterns = {"/login"})
public class LoginCustomerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        CustomerModel customerModel = new CustomerModel();
        Customer customer = null;

        boolean success = false;

        try {

            success = customerModel.identifier(login, password);

            if (success) {
                customer = customerModel.get(login);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", customer);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            System.out.println("Login ou senha inválida");
        }
    }

}
