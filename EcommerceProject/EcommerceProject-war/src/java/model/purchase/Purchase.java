/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.purchase;

import java.util.ArrayList;
import javafx.util.Pair;
import model.product.Product;
import model.customer.Customer;

/**
 *
 * @author Tibet
 */
public class Purchase {
    private int id;
    private ArrayList< Pair<Product, Integer> > products;
    private Customer customer;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Pair<Product, Integer>> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Pair<Product, Integer>> products) {
        this.products = products;
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
