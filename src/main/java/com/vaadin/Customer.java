package com.vaadin;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
@IgnoreExtraProperties
public class Customer implements Serializable, Cloneable {

    private Long id;

    private String firstName = "";

    private String lastName = "";

    private LocalDate orderDate;

    private String address = "";
    private String contact ="";
    private CustomerStatus product  ;
    private QuantityConstants quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of email
     *
     * @param address
     *            new value of email
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Get the value of birthDate
     *
     * @return the value of birthDate
     */
    public LocalDate getBirthDate() {
        return getOrderDate();
    }

    /**
     * Set the value of birthDate
     *
     * @param birthDate
     *            new value of birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.setOrderDate(birthDate);
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName
     *            new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName
     *            new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Customer && obj.getClass().equals(getClass())) {
            return this.id.equals(((Customer) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        return (Customer) super.clone();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public CustomerStatus getProduct() {
        return product;
    }

    public void setProduct(CustomerStatus product) {
        this.product = product;
    }

    public QuantityConstants getQuantity() {
        return quantity;
    }

    public void setQuantity(QuantityConstants quantity) {
        this.quantity = quantity;
    }
}
