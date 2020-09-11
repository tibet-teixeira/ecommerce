package model.product;

import java.util.List;
import model.category.Category;

/**
 *
 * @author Tibet
 */
public class ProductModel {

    public Product get(int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.get(id);
    }
    
    public Product get(String description) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.get(description);
    }
    
    public List<Product> get(Category category) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.get(category);
    }
    
    public List<Product> getAll() throws Exception {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.getAll();
    }
    
    public void update(Product product, int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.update(product, id);
    }
    
    public void delete(int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        productDAO.delete(id);
    }

    public void insert(Product product) throws Exception {
        if (product == null) {
            throw new Exception("Não foram informados todos os campos obrigatórios");
        }
        
        ProductDAO productDAO = new ProductDAO();
        productDAO.insert(product);
    }
}
