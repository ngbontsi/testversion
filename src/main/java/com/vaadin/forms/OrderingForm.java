package com.vaadin.forms;

import com.vaadin.MyUI;
import com.vaadin.constants.QuantityConstants;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.model.Customer;
import com.vaadin.model.Product;
import com.vaadin.server.UserError;
import com.vaadin.service.BillService;
import com.vaadin.service.CustomerService;
import com.vaadin.service.ProductService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.log4j.Logger;


public class OrderingForm extends FormLayout {

    private static Logger logger = Logger.getLogger(OrderingForm.class);

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField contact = new TextField();
    private TextField address = new TextField();
    private NativeSelect<String> product = new NativeSelect<>();
    private NativeSelect<QuantityConstants> quantity = new NativeSelect<>();

    private Button save = new Button("Order");
    private Button delete = new Button("Update");


    private Customer customer;
    private MyUI myUI;
    private Binder<Customer> customerBinder = new Binder<>(Customer.class);

    public OrderingForm(MyUI myUI) {
        this.myUI = myUI;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        firstName.setPlaceholder("first name");
        firstName.setIcon(VaadinIcons.USER);
        firstName.setRequiredIndicatorVisible(true);
        firstName.setComponentError(new UserError("Name can not be empty"));

        lastName.setPlaceholder("last name");
        lastName.setIcon(VaadinIcons.USER);
        lastName.setRequiredIndicatorVisible(true);
        lastName.setComponentError(new UserError("last Name can not be empty"));
        contact.setPlaceholder("Cell Number");
        contact.setIcon(VaadinIcons.ENVELOPE);

        address.setPlaceholder("Address");
        address.setIcon(VaadinIcons.HOME);
        address.setRequiredIndicatorVisible(true);
        address.setComponentError(new UserError("Enter Address"));

        lastName.setPlaceholder("last name");
        lastName.setIcon(VaadinIcons.USER);
        lastName.setRequiredIndicatorVisible(true);
        lastName.setComponentError(new UserError("last Name can not be empty"));
        contact.setPlaceholder("Cell Number");

        addComponents(firstName, lastName, contact, address, product, quantity, buttons);

        product.setItems(ProductService.getAll());
        quantity.setItems(QuantityConstants.values());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);


        customerBinder.forField(firstName).withValidator(name -> name.length() >= 3, "Name must contain at least three characters")
                .bind(Customer::getFirstName, Customer::setFirstName);
        customerBinder.bindInstanceFields(this);
        save.addClickListener(e -> {

            if (firstName == null || lastName == null || contact == null || address == null || product.isEmpty() || quantity.isEmpty()) {

            } else {
                this.save();
            }


        });
        delete.addClickListener(e -> this.delete());
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customerBinder.setBean(customer);

        delete.setVisible(customer.isPersisted());
        setVisible(true);
        firstName.selectAll();
    }

    private void delete() {
        CustomerService.delete(customer);
        myUI.updateList();
        setVisible(false);
    }

    private void save() {
        customer = new Customer();
        System.out.println("Customer :"+customer);
        customer.setFirstName(firstName.getValue());
        customer.setLastName(lastName.getValue());
        customer.setAddress(address.getValue());
        customer.setContact(contact.getValue());
        CustomerService.add(customer);
        Product dbProduct = ProductService.getProductByName(product.getValue());
        if(BillService.createBill(customer,dbProduct,quantity.getValue())){
                myUI.updateList();
                clearData();
        }else {
            Window popupWindow = new Window(" Adding Order ");
            VerticalLayout subContent = new VerticalLayout();
            popupWindow.setContent(subContent);
            subContent.addComponent(new Label("Sorry your order did not get completed"));
            subContent.addComponents(new Button("Ok"));
            popupWindow.center();

        }

    }

    private void clearData() {
        firstName.clear();
        lastName.clear();
        contact.clear();
        address.clear();
        quantity.clear();
        product.clear();
    }
}
