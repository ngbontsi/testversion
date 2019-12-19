package com.vaadin.service;

import com.bontsi.dev.framework.dao.GenericHibernateDAO;
import com.vaadin.constants.QuantityConstants;
import com.vaadin.db.DatabaseHandler;
import com.vaadin.db.JPAUtil;
import com.vaadin.db.dao.BillDAO;
import com.vaadin.model.Bill;
import com.vaadin.model.Customer;
import com.vaadin.model.OrderData;
import com.vaadin.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BillService extends GenericHibernateDAO<Bill,Long> implements BillDAO {

    @Override
    public List<Bill> findByExample(Bill bill) {
        return null;
    }
}
