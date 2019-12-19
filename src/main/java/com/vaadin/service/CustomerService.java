package com.vaadin.service;


import com.bontsi.dev.framework.dao.GenericHibernateDAO;
import com.vaadin.db.dao.CustomerDAO;
import com.vaadin.model.Customer;

import java.util.List;


public class CustomerService extends GenericHibernateDAO<Customer, Long> implements CustomerDAO {


    @Override
    public List<Customer> findByExample(Customer customer) {
        fi
        return null;
    }
}
