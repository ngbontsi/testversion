package com.vaadin.service;

import com.bontsi.dev.framework.dao.GenericHibernateDAO;
import com.vaadin.db.dao.ProductDAO;
import com.vaadin.model.Product;

import java.util.List;


public class ProductService extends GenericHibernateDAO<Product, Long> implements ProductDAO {


    @Override
    public List<Product> findByExample(Product product) {
        return null;
    }
}
