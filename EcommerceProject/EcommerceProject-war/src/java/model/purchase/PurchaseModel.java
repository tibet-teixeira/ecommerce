package model.purchase;

import java.util.List;
import model.customer.Customer;
import model.product.Product;
import model.product.ProductDAO;

/**
 *
 * @author Tibet
 */
public class PurchaseModel {

    public Purchase get(int id) throws Exception {
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        return purchaseDAO.get(id);
    }
    
    public List<Purchase> get(Customer customer) throws Exception {
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        return purchaseDAO.get(customer);
    }
    
    public List<Purchase> getAll() throws Exception {
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        return purchaseDAO.getAll();
    }
    
    public void update(Purchase purchase, int id) throws Exception {
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        purchaseDAO.update(purchase, id);
    }
    
    public void delete(int id) throws Exception {
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        purchaseDAO.delete(id);
    }

    public void insert(Purchase purchase) throws Exception {
        if (purchase == null) {
            throw new Exception("Não foram informados todos os campos obrigatórios");
        }
        
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        purchaseDAO.insert(purchase);
    }
}
