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
@WebServlet(name = "RegisterCustomerServlet", urlPatterns = {"/cadastro"})
public class RegisterCustomerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            customerModel.insert(customer);
            success = true;
        } catch (Exception ex) {
            success = false;
        }

        if (success) {
            request.setAttribute("message", "Cliente cadastrado com sucesso");
            System.out.println("Cliente cadastrado com sucesso");
        } else {
            System.out.println("Não foi possível cadastrar este cliente");
            request.setAttribute("message", "Não foi possível cadastrar este cliente");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
