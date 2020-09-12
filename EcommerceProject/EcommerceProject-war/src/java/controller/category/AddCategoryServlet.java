package controller.category;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.category.Category;
import model.category.CategoryModel;

/**
 *
 * @author Tibet
 */
@WebServlet(name = "AddCategoryServlet", urlPatterns = {"/categoria"})
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String description = request.getParameter("categoryName");

        try {
            new CategoryModel().insert(new Category(description));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        request.setAttribute("message", "Categoria cadastrada com sucesso");
        RequestDispatcher dispatcher = request.getRequestDispatcher("home_adm.jsp");
        dispatcher.forward(request, response);
        System.out.println("Categoria cadastrada com sucesso");
    }

}
