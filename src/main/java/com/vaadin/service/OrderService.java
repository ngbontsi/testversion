package com.vaadin.service;

import com.bontsi.dev.framework.dao.GenericHibernateDAO;
import com.vaadin.db.dao.OrderDAO;
import com.vaadin.model.OrderData;

import java.util.List;

public class OrderService extends GenericHibernateDAO<OrderData, Long> implements OrderDAO {

    @Override
    public List<OrderData> findByExample(OrderData orderData) {
        return null;
    }
}
