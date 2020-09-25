package model.bag;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import model.product.Product;
import model.product.ProductModel;

public class ShoppingBag {

    public static final String COOKIE_KEY = "shopping-bag";
    private static final String PRODUCT_SEPARATOR = "&";
    private static final String QUANTITY_SEPARATOR = ":";

    public static final Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(ShoppingBag.COOKIE_KEY)) {
                cookie = cookies[i];
                break;
            }
        }
        return cookie;
    }

    public static final List<ShoppingBagItem> getShoppingBag(String cookieProductList)
            throws Exception {
        List<ShoppingBagItem> shoppingBagItems = new ArrayList<>();

        if (cookieProductList == null
                || cookieProductList.trim().length() == 0
                || !cookieProductList.contains(QUANTITY_SEPARATOR)) {
            return shoppingBagItems;
        }

        ProductModel productModel = new ProductModel();

        if (cookieProductList.contains(PRODUCT_SEPARATOR)) {
            String[] products = cookieProductList.split(PRODUCT_SEPARATOR);
            for (int i = 0; products != null && i < products.length; i++) {
                String[] item = products[i].split(QUANTITY_SEPARATOR);

                ShoppingBagItem shoppingBagItem = new ShoppingBagItem();
                Product product = productModel.get(Integer.parseInt(item[0]));

                shoppingBagItem.setProduct(product);
                shoppingBagItem.setQuantity(Integer.parseInt(item[1]));
                shoppingBagItems.add(shoppingBagItem);
            }
        } else {
            String[] item = cookieProductList.split(QUANTITY_SEPARATOR);

            ShoppingBagItem shoppingBagItem = new ShoppingBagItem();
            Product product = productModel.get(Integer.parseInt(item[0]));

            shoppingBagItem.setProduct(product);
            shoppingBagItem.setQuantity(Integer.parseInt(item[1]));
            shoppingBagItems.add(shoppingBagItem);
        }

        return shoppingBagItems;
    }

    public static final String addItem(int productId, int quantity,
            String cookieProductList) throws Exception {
        List<ShoppingBagItem> shoppingBagItems = getShoppingBag(cookieProductList);

        if (shoppingBagItems.isEmpty()) {
            System.out.println("Tá vazio");
            return productId + QUANTITY_SEPARATOR + quantity;
        }

        boolean inserted = false;
        String newCookieProductList = "";

        for (ShoppingBagItem shoppingBagItem : shoppingBagItems) {
            if (shoppingBagItem.getProduct().getId() == productId) {
                shoppingBagItem.setQuantity(shoppingBagItem.getQuantity() + quantity);
                inserted = true;
            }

            if (!newCookieProductList.isEmpty()) {
                newCookieProductList += PRODUCT_SEPARATOR;
            }

            newCookieProductList += shoppingBagItem.getProduct().getId() + QUANTITY_SEPARATOR + shoppingBagItem.getQuantity();
        }

        if (!inserted) {
            newCookieProductList += PRODUCT_SEPARATOR + productId + QUANTITY_SEPARATOR + quantity;
        }

        return newCookieProductList;
    }
    
    public static final String updateItem(int productId, int quantity,
            String cookieProductList) throws Exception {
        List<ShoppingBagItem> shoppingBagItems = getShoppingBag(cookieProductList);

        if (shoppingBagItems.isEmpty()) {
            return productId + QUANTITY_SEPARATOR + quantity;
        }

        boolean inserted = false;
        String newCookieProductList = "";

        for (ShoppingBagItem shoppingBagItem : shoppingBagItems) {
            if (shoppingBagItem.getProduct().getId() == productId) {
                shoppingBagItem.setQuantity(quantity);
                inserted = true;
            }

            if (!newCookieProductList.isEmpty()) {
                newCookieProductList += PRODUCT_SEPARATOR;
            }

            newCookieProductList += shoppingBagItem.getProduct().getId() + QUANTITY_SEPARATOR + shoppingBagItem.getQuantity();
        }

        if (!inserted) {
            newCookieProductList += PRODUCT_SEPARATOR + productId + QUANTITY_SEPARATOR + quantity;
        }

        return newCookieProductList;
    }

    public static final String removeItem(int productId, String cookieProductList)
            throws Exception {
        List<ShoppingBagItem> shoppingBagItems = getShoppingBag(cookieProductList);

        if (shoppingBagItems.isEmpty()) {
            return "";
        }

        String newCookieProductList = "";

        for (ShoppingBagItem shoppingBagItem : shoppingBagItems) {
            if (shoppingBagItem.getProduct().getId() == productId) {
                continue;
            }

            if (!newCookieProductList.isEmpty()) {
                newCookieProductList += PRODUCT_SEPARATOR;
            }

            newCookieProductList += shoppingBagItem.getProduct().getId() + QUANTITY_SEPARATOR + shoppingBagItem.getQuantity();
        }

        return newCookieProductList;
    }
}
