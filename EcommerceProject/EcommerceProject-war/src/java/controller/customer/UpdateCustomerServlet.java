package controller.customer;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.customer.Customer;
import model.customer.CustomerModel;

/**
 *
 * @author Tibet
 */
@WebServlet(name = "UpdateCustomerServlet", urlPatterns = {"/atualizar-cliente"})
public class UpdateCustomerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String street = request.getParameter("street");
        String houseNumber = request.getParameter("number");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String cep = request.getParameter("code");

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setLogin(login);
        customer.setPassword(password);
        customer.setStreet(street);
        customer.setHouseNumber(houseNumber);
        customer.setCity(city);
        customer.setState(state);
        customer.setCep(cep);

        CustomerModel customerModel = new CustomerModel();

        boolean success = false;

        try {
            customerModel.update(customer);
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
