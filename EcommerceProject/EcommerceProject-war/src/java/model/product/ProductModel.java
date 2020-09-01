package model.product;

/**
 *
 * @author Tibet
 */
public class ProductModel {

    public Product get(int id) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.get(id);
    }

    public void insert(Product product) throws Exception {
        if (product == null) {
            throw new Exception("Não foram informados todos os campos obrigatórios");
        }
        
        ProductDAO productDAO = new ProductDAO();
        productDAO.insert(product);
    }
}
