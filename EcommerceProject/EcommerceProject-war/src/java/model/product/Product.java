/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import java.util.ArrayList;
import java.util.List;
import model.category.Category;

/**
 *
 * @author Tibet
 */
public class Product {
    private int id;
    private String description;
    private double price;
    private int quantity;
    private String picture;
    private List<Category> categories;

    public Product() {
    }

    
    public Product(String description, double price, int quantity, String picture, List<Category> categories) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.picture = picture;
        this.categories = categories;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    
}
