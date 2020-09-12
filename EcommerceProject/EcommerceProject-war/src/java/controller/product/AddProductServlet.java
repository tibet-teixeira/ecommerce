package controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.category.Category;
import model.category.CategoryModel;
import model.product.Product;
import model.product.ProductModel;

/**
 *
 * @author Tibet
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/produto"})
public class AddProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String description = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String photo = request.getParameter("photo");
        String[] categoriesField = request.getParameterValues("category");

        try {
            List<Category> categories = new ArrayList<>();
            Category category;

            for (String categoryId : categoriesField) {
                category = new CategoryModel().get(Integer.parseInt(categoryId));
                categories.add(category);
                System.out.println("Category = " + category.getDescription());
            }

            new ProductModel().insert(new Product(description, price, quantity, photo, categories));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        request.setAttribute("message", "Categoria cadastrada com sucesso");
        RequestDispatcher dispatcher = request.getRequestDispatcher("home_adm.jsp");
        dispatcher.forward(request, response);
        System.out.println("Categoria cadastrada com sucesso");
    }

}
