package com.vaadin.service;


import com.bontsi.dev.framework.dao.GenericHibernateDAO;
import com.vaadin.db.dao.CustomerDAO;
import com.vaadin.model.Customer;

import java.util.List;


public class CustomerService extends GenericHibernateDAO<Customer, Long> implements CustomerDAO {


    @Override
    public List<Customer> findByExample(Customer customer) {

        return null;
    }


    public Customer getUserByNames(String firstName, String lastName) {
        return (Customer) getSession().createQuery("", Customer.class).setParameter("firstName", firstName).setParameter("lastName", lastName);

    }

    private String buildQueryString(String selectClause) {
        StringBuilder queryStringBuilder = new StringBuilder(selectClause);
        queryStringBuilder.append(" from Customer customer ");
        return queryStringBuilder.toString();
    }

}
