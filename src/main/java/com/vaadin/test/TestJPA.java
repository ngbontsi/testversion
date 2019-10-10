package com.vaadin.test;

import com.vaadin.model.Customer;
import com.vaadin.constants.QuantityConstants;
import com.vaadin.model.Product;
import com.vaadin.service.CustomerService;
import com.vaadin.service.ProductService;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class TestJPA {
    private static EntityManager entityManager;
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NGFarms");
//        entityManager = entityManagerFactory.createEntityManager();
            System.out.println(QuantityConstants.SIX);
            System.out.println(QuantityConstants.SIX.quantity);
//        try {
//
//            createData();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
    private static void createData() throws IllegalAccessException {

        Customer fist = new Customer();
        fist.setFirstName("ndira");
        fist.setLastName("bon");
        fist.setAddress("hlkglgbk");
        fist.setContact("98765432");
//        testReflections(fist);
        CustomerService.add(fist);

        Product medium = new Product();
        medium.setProductName("Medium");
        medium.setProductPrice(BigDecimal.valueOf(55));
        ProductService.add(medium);

        Product large = new Product();
        large.setProductName("Large");
        large.setProductPrice(BigDecimal.valueOf(60));
        ProductService.add(large);

        Product jumbo = new Product();
        jumbo.setProductName("Jumbo");
        jumbo.setProductPrice(BigDecimal.valueOf(65));
        ProductService.add(jumbo);

        Product eLarge = new Product();
        eLarge.setProductName("Extra Large");
        eLarge.setProductPrice(BigDecimal.valueOf(65));
        ProductService.add(eLarge);

//        entityManager.getTransaction().begin();
//        entityManager.persist(fist);
//        entityManager.getTransaction().commit();

    }

    public static void testReflections(Object obj ) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field: fields) {
            System.out.println("Field " + field);
            System.out.println("Field Name " + field.getName());
            field.setAccessible(true);
            System.out.println("Field value " + field.get(obj));



        }
    }
}
