package controller.bag;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bag.ShoppingBag;

@WebServlet(name = "AddProductShoppingBagServlet", urlPatterns = {"/adicionar_produto_sacola"})
public class AddProductShoppingBagServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Cookie cookie = ShoppingBag.getCookie(request);
        try {

            String newCookieProductList = ShoppingBag.addItem(Integer.parseInt(id), 1, cookie.getValue());
            cookie.setValue(newCookieProductList);
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("sacola.jsp");
        dispatcher.forward(request, response);
    }
}
