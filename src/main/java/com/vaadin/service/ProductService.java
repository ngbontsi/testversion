package com.vaadin.service;

import com.vaadin.db.DatabaseHandler;
import com.vaadin.model.Product;
import org.apache.log4j.Logger;

import java.util.List;


public class ProductService {

    public static Product add(Product item) {
        getLogger().info("Set user " + item.getId() + " to " + item);

        return (Product)DatabaseHandler.insertEntity(item);
    }

    protected static Logger getLogger() {
        return Logger.getLogger(ProductService.class);
    }

    public static Product update(Product item) {
        getLogger().info("Set user " + item.getId() + " to " + item);
        return(Product)DatabaseHandler.insertEntity(item);


    }


    public static Product getProductByName(String productName) {
        getLogger().info("fetching product "+productName);
        return (Product) DatabaseHandler.findAll(SQLSTATEMENTS.PRODUCT_BY_NAME+"'"+productName+"'").get(0);


    }

    public static void delete(Product item) {
        getLogger().info("Delete user " + item);
//        TODO

    }

    public static List<Product> findAll() {

        List<Product> list = (List<Product>) DatabaseHandler.findAll(new Product());
        return list;

    }

    public static List<String> getAll() {

        return (List<String>)DatabaseHandler.findAll(SQLSTATEMENTS.PRODUCT_LIST);
    }
}
