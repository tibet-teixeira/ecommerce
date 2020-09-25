package model.purchase;

import java.util.Date;
import java.util.List;
import model.customer.Customer;
import model.bag.ShoppingBagItem;

/**
 *
 * @author Tibet
 */
public class Purchase {
    private int id;
    private List<ShoppingBagItem> shoppingBagItems;
    private Customer customer;
    private Date date;

    public Purchase() {
    }

    public Purchase(List<ShoppingBagItem> shoppingBagItems, Customer customer, Date date) {
        this.shoppingBagItems = shoppingBagItems;
        this.customer = customer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ShoppingBagItem> getShoppingBagItems() {
        return shoppingBagItems;
    }

    public void setShoppingBagItems(List<ShoppingBagItem> shoppingBagItems) {
        this.shoppingBagItems = shoppingBagItems;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
