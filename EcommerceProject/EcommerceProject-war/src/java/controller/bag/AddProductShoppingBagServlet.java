package controller.bag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bag.ShoppingBag;
import model.product.ProductModel;

@WebServlet(name = "AddProductShoppingBagServlet", urlPatterns = {"/adicionar_produto_sacola"})
public class AddProductShoppingBagServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Cookie cookie = ShoppingBag.getCookie(request);
        try {
            String newCookieProductList = ShoppingBag.addItem(id, 1, cookie.getValue());
            cookie.setValue(newCookieProductList);
            response.addCookie(cookie);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("sacola.jsp");
        dispatcher.forward(request, response);
    }
}
