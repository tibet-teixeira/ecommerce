package controller.customer;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Tibet
 */

@WebServlet(name = "LogoutCustomerServlet", urlPatterns = {"/logout"})
public class LogoutCustomerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.print("Sua sessão foi encerrada");
        request.setAttribute("message", "Sua sessão foi encerrada");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
        System.out.print("Sua sessão foi encerrada");
    }
}
