package com.vaadin.service;



import com.vaadin.model.Customer;
import com.vaadin.db.DatabaseHandler;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * An in memory dummy "database" for the example purposes. In a typical Java app
 * this class would be replaced by e.g. EJB or a Spring based service class.
 * <p>
 * In demos/tutorials/examples, get a reference to this service class with
 * {@link CustomerService#getInstance()}.
 */
public class CustomerService {

    private static CustomerService instance;
    private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());
    private static final String DATABASE_NAMESPACE = "ngfarms";

    private final HashMap<Long, Customer> contacts = new HashMap<>();
    private long nextId = 0;

    private CustomerService() {
    }


    public static Customer add(Customer item) {
        getLogger().info("Set user " + item.getId() + " to " + item);

       return(Customer) DatabaseHandler.insertEntity(item);
    }

    protected static Logger getLogger() {
        return Logger.getLogger("CustomerDB");
    }

    public static Customer update(Customer item) {
        getLogger().info("Set user " + item.getId() + " to " + item);
       return(Customer) DatabaseHandler.insertEntity(item);


    }

    public static void delete(Customer item) {
        getLogger().info("Delete user " + item);
//        TODO

    }

    public static List<Customer> findAll() {

        List<Customer> list = (List<Customer>) DatabaseHandler.findAll(new Customer());
        return list;

    }




}
