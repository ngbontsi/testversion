package com.vaadin.service;

import com.vaadin.constants.QuantityConstants;
import com.vaadin.db.DatabaseHandler;
import com.vaadin.db.JPAUtil;
import com.vaadin.model.Bill;
import com.vaadin.model.Customer;
import com.vaadin.model.OrderData;
import com.vaadin.model.Product;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BillService {
    private static BillService billService;

    public BillService() {

    }

    public static Bill add(Bill item) {
        getLogger().info("Set user " + item.getId() + " to " + item);

        return (Bill) DatabaseHandler.insertEntity(item);
    }

    protected static Logger getLogger() {
        return Logger.getLogger("CustomerDB");
    }

    public static Bill update(Bill item) {
        getLogger().info("Set user " + item.getId() + " to " + item);
        return (Bill) DatabaseHandler.insertEntity(item);


    }

    public static Boolean createBill(Customer customer, Product product, QuantityConstants quantity) {
        Bill bill = new Bill();
        bill.setCustomerId(customer.getId());
        bill.setProductId(product.getId());
        bill.setOrderDate(LocalDate.now());
        bill.setPaid(false);
        bill.setQuantity(Long.valueOf(quantity.quantity));
        bill.setBillPrice(calculatePrice(product, bill.getQuantity()));
        bill = (Bill) DatabaseHandler.insertEntity(bill);

        if (bill.getId() != null)
            return true;
        return false;


    }

    private static BigDecimal calculatePrice(Product product, Long quantity) {
        return product.getProductPrice().multiply(new BigDecimal(quantity));
    }


    public static void delete(Bill item) {
        getLogger().info("Delete user " + item);
//        TODO

    }

    public static List<OrderData> findAll() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<OrderData> list1 = new ArrayList<>();
        list1 = entityManager.createNativeQuery(SQLSTATEMENTS.CUSTOM_ODER_FOR_ALL,OrderData.class).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        for (OrderData data: list1)
            System.out.println("Printing Data: "+data);
        return list1;

    }

    public static List<Bill> getAll() {

        return (List<Bill>) DatabaseHandler.findAll(new Bill());

    }

    public static BillService getInstance() {
        if (billService == null) {
            billService = new BillService();
        }
        return billService;
    }
}
