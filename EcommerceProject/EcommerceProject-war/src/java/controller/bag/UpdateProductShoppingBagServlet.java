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

@WebServlet(name = "UpdateProductShoppingBagServlet", urlPatterns = {"/adicionar_produto_sacola"})
public class UpdateProductShoppingBagServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Cookie cookie = ShoppingBag.getCookie(request);
        try {

            String newCookieProductList = ShoppingBag.updateItem(Integer.parseInt(id), quantity, cookie.getValue());
            cookie.setValue(newCookieProductList);
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("sacola.jsp");
        dispatcher.forward(request, response);
    }
}
