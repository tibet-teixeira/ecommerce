/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.category;

import java.util.ArrayList;
import model.product.Product;

/**
 *
 * @author Tibet
 */
public class Category {
    private int id;
    private String description;
    private ArrayList<Product> products;

    public Category() {
    }

    public Category(int id, String description) {
        this.id = id;
        this.description = description;
    }
    
    public Category(String description) {
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    
    
}
