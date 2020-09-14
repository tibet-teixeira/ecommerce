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
@WebServlet(name = "DeleteCustomerServlet", urlPatterns = {"/remover-cliente"})
public class DeleteCustomerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));    

        CustomerModel customerModel = new CustomerModel();

        boolean success = false;

        try {
            customerModel.delete(id);
            success = true;
        } catch (Exception ex) {
            success = false;
        }

        if (success) {
            request.setAttribute("message", "Conta removida com sucesso");
            System.out.println("Conta removida com sucesso");
        } else {
            System.out.println("Não foi possível remover esta conta");
            request.setAttribute("message", "Não foi possível remover esta conta");
        }

        HttpSession session = request.getSession();
        session.invalidate();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
