package model.bag;

import model.product.Product;

public class ShoppingBagItem {
    
    private Product product;
    private int quantity;

    public ShoppingBagItem() {
    }

    public ShoppingBagItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
