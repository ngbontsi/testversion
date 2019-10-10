package com.vaadin.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "customer")
public class Customer implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    @Column(name ="uid")
    private Long id;
    @Column(name ="firstName")
    private String firstName;
    @Column(name ="lastName")
    private String lastName ;
    @Column(name ="address")
    private String address;
    @Column(name ="contact")
    private String contact ;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
