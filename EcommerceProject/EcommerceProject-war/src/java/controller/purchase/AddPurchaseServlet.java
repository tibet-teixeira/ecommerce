package controller.purchase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bag.ShoppingBag;
import model.bag.ShoppingBagItem;
import model.purchase.PurchaseModel;
import model.purchase.Purchase;
import model.customer.CustomerModel;
import model.customer.Customer;

/**
 *
 * @author Tibet
 */
@WebServlet(name = "AddPurchaseServlet", urlPatterns = {"/comprar"})
public class AddPurchaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCustomer = Integer.parseInt(request.getParameter("id_customer"));
        String purchaseList = request.getParameter("purchaseList");
        
        try {
            List<ShoppingBagItem> shoppingBagItems = ShoppingBag.getShoppingBag(purchaseList);
            Customer customer = new CustomerModel().get(idCustomer);
            Purchase purchase = new Purchase(shoppingBagItems, customer, new Date());

            new PurchaseModel().insert(purchase);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        request.setAttribute("message", "Compra realizada com sucesso");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}
