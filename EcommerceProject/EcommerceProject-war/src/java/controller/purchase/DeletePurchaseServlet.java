package controller.purchase;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.purchase.Purchase;
import model.purchase.PurchaseModel;

/**
 *
 * @author Tibet
 */
@WebServlet(name = "DeletePurchaseServlet", urlPatterns = {"/remover_compra"})
public class DeletePurchaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));    
        PurchaseModel purchaseModel = new PurchaseModel();

        boolean success = false;

        try {
            purchaseModel.delete(id);
            success = true;
        } catch (Exception ex) {
            success = false;
        }

        if (success) {
            request.setAttribute("message", "Compra removida com sucesso");
            System.out.println("Compra removida com sucesso");
        } else {
            System.out.println("Não foi possível remover esta Compra");
            request.setAttribute("message", "Não foi possível remover esta Compra");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("visualizar_compras_adm.jsp");
        dispatcher.forward(request, response);
    }
}
