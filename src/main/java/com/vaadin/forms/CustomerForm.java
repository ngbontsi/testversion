package com.vaadin.forms;

import com.vaadin.MyUI;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.model.Customer;
import com.vaadin.service.CustomerService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.log4j.Logger;


public class CustomerForm extends Window {

    private static Logger logger = Logger.getLogger(CustomerForm.class);

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField contact = new TextField();
    private TextField address = new TextField();

    private Button save = new Button("Order");
    private Button delete = new Button("Update");


    private Customer customer;
    private CustomerService customerService;
    private MyUI myUI;
    private Binder<Customer> customerBinder = new Binder<>(Customer.class);

    public CustomerForm(MyUI myUI) {
        this.myUI = myUI;
        customerService = new CustomerService();
        setModal(true);
        setClosable(true);
        setResizable(true);
        VerticalLayout verticalLayout = new VerticalLayout();

        FormLayout formLayout = new FormLayout();

        HorizontalLayout buttons = new HorizontalLayout(save, delete);

        firstName.setPlaceholder("first name");
        firstName.setIcon(VaadinIcons.USER);
        firstName.setRequiredIndicatorVisible(true);


        lastName.setPlaceholder("last name");
        lastName.setIcon(VaadinIcons.USER);
        lastName.setRequiredIndicatorVisible(true);

        contact.setPlaceholder("Cell Number");
        contact.setIcon(VaadinIcons.ENVELOPE);
        contact.setRequiredIndicatorVisible(true);


        address.setPlaceholder("Address");
        address.setIcon(VaadinIcons.HOME);
        address.setRequiredIndicatorVisible(true);


        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        formLayout.addComponents(firstName, lastName, address, contact, buttons);
        customerBinder.forField(firstName)
                .withValidator(new StringLengthValidator("Please enter Customer Name", 3, null))
                .bind(Customer::getFirstName, Customer::setFirstName);
        customerBinder.forField(lastName)
                .withValidator(new StringLengthValidator("Please enter Customer Last Name", 3, null))
                .bind(Customer::getLastName, Customer::setLastName);
        customerBinder.forField(contact)
                .withValidator(new StringLengthValidator("Contact can not be less than 10 numbers", 10, null))
                .bind(Customer::getContact, Customer::setContact);

        save.addClickListener(e -> {
            customerBinder.validate();
            if (customerBinder.isValid()) {
                save();
                close();
            }

        });
        delete.addClickListener(e -> close());
    }


    private void save() {
        customer = getCustomerInformation();
        if (customer == null) {
            customer = new Customer();
            pupulateCustomer();
        } else {
            pupulateCustomer();
        }

        customerService.save(customer);
    }

    private void pupulateCustomer() {
        customer.setFirstName(firstName.getValue());
        customer.setLastName(lastName.getValue());
        customer.setAddress(address.getValue());
        customer.setContact(contact.getValue());

    }

    private Customer getCustomerInformation() {

        return customerService.getUserByNames(firstName.getValue(), lastName.getValue());
    }

    private void clearData() {
        firstName.clear();
        lastName.clear();
        contact.clear();
        address.clear();
    }
}
