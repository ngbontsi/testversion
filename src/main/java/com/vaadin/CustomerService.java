package com.vaadin;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.vaadin.db.FireBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
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

    public static DatabaseReference getUsersDb() {
        return FireBase.getDb().child(DATABASE_NAMESPACE);
    }

    public static void maybeCreateInitialData(DataSnapshot snapshot) {
        if (snapshot.hasChild(DATABASE_NAMESPACE)) {
            return;
        }
        Customer firstCust = new Customer();
        Customer secondCust = new Customer();
        firstCust.setFirstName("Ndira");
        secondCust.setFirstName("Ndira");
        firstCust.setLastName("bontsi");
        secondCust.setLastName("skeyi");
        firstCust.setAddress("iokjkjhjsa");
        secondCust.setAddress("kahdsgjh");
        firstCust.setContact("09876534213");
        secondCust.setFirstName("08976454213");
        firstCust.setProduct(CustomerStatus.ImportedLead);
        secondCust.setProduct(CustomerStatus.Contacted);
        firstCust.setQuantity(QuantityConstants.TWO);
        secondCust.setQuantity(QuantityConstants.THREE);


        add(firstCust);
        add(secondCust);
    }

    public static void add(Customer item) {
        getUsersDb().push().setValueAsync(item);
    }

    protected static Logger getLogger() {
        return Logger.getLogger("UserDB");
    }

    public static void update(String key, Customer item) {
        getLogger().info("Set user " + key + " to " + item);
        HashMap<String, Object> toUpdate = new HashMap<>();
        toUpdate.put(key, item);
        getUsersDb().updateChildrenAsync(toUpdate);
    }

    public static void delete(Customer item) {
        getLogger().info("Delete user " + item);
        HashMap<String, Object> toUpdate = new HashMap<>();
        toUpdate.put(item.getId().toString(), null);
        getUsersDb().updateChildrenAsync(toUpdate);

    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();

        }
        return instance;
    }






}
