package model.purchase;

/**
 *
 * @author Tibet
 */
public class PurchaseModel {

    public Purchase get(int id) throws Exception {

        PurchaseDAO purchaseDAO = new PurchaseDAO();
        return purchaseDAO.get(id);
    }

    public void insert(Purchase purchase) throws Exception {
        if (purchase == null) {
            throw new Exception("Não foram informados todos os campos obrigatórios");
        }
        
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        purchaseDAO.insert(purchase);
    }
}
