/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.purchase;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import model.product.Product;
import model.customer.Customer;

/**
 *
 * @author Tibet
 */
public class Purchase {
    private int id;
    private List< Pair<Product, Integer> > numberProducts;
    private Customer customer;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List< Pair<Product, Integer> > getNumberProducts() {
        return numberProducts;
    }

    public void setNumberProducts(List< Pair<Product, Integer> > numberProducts) {
        this.numberProducts = numberProducts;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
