package com.vaadin.service;

import com.vaadin.model.Bill;
import com.vaadin.model.Customer;
import com.vaadin.model.Product;

public class SQLSTATEMENTS {

    public static final String CUSTOMER_BY_NAMES = "SELECT cus FROM " + Customer.class.getSimpleName() + " cus WHERE firstName = ";

    public static final String PRODUCT_BY_NAME = "SELECT p FROM " + Product.class.getSimpleName() + " p WHERE productName = ";
    public static final String PRODUCT_LIST = "SELECT productName FROM " + Product.class.getSimpleName() ;
    public static final String PRODUCT_BY_PRICE = "SELECT p FROM " + Product.class.getSimpleName() + " WHERE productPrice = ";
    public static final String ODER_BY_USER = "SELECT p FROM " + Bill.class.getSimpleName() + " p WHERE paid = false and customerId = ";
    public static final String ODER_BY_PRODUCT = "SELECT p FROM " + Bill.class.getSimpleName() + " p WHERE customerId = ";
    public static final String ODER_BY_ORDER_DATE = "SELECT p FROM " + Bill.class.getSimpleName() + " p WHERE orderDate = ";
    public static final String ODER_BY_PAY_DATE = "SELECT p FROM " + Bill.class.getSimpleName() + " p WHERE paidDate = ";
    public static final String CUSTOM_ODER_FOR_ALL = "SELECT cus.uid,cus.firstName,cus.lastName,bil.orderDate,pro.productName,bil.billPrice  productPrice, bil.payment " +
                                                        "from "+Bill.class.getSimpleName().toLowerCase() +" bil ,"+ Customer.class.getSimpleName().toLowerCase()+" cus ,"+
                                                            Product.class.getSimpleName().toLowerCase()+
                                                                " pro where bil.customerId = cus.uid and bil.productId = pro.uid AND bil.paid = false";
}
