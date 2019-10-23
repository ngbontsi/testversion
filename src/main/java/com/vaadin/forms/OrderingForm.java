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
import com.vaadin.utils.ValidatorUtil;
import org.apache.log4j.Logger;


public class OrderingForm extends FormLayout {

    private static Logger logger = Logger.getLogger(OrderingForm.class);

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField contact = new TextField();
    private TextField address = new TextField();
    private CheckBox existingCustomer = new CheckBox();
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
        existingCustomer.setCaption("Existing Customer?");
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


        addComponents(existingCustomer,firstName, lastName, contact, address, product, quantity, buttons);

        product.setItems(ProductService.getAll());
        quantity.setItems(QuantityConstants.values());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        customerBinder.bindInstanceFields(this);


        existingCustomer.addValueChangeListener(e->{
            if(e.getValue()){
                firstName.setVisible(false);
                lastName.setVisible(false);
                address.setVisible(false);
                contact.setVisible(false);
            }else{
                firstName.setVisible(true);
                lastName.setVisible(true);
                address.setVisible(true);
                contact.setVisible(true);
            }


        });
        save.addClickListener(e -> {

            if (validateFields()) {
                this.save();
            }

        });
        delete.addClickListener(e -> this.delete());
    }

    private boolean validateFields() {
        boolean passed = true;
        if (ValidatorUtil.isEmpty(lastName) &&!existingCustomer.getValue()) {
            lastName.setComponentError(new UserError("Last Name can not be empty"));
            passed = false;
        }
        if (ValidatorUtil.isEmpty(firstName)&&!existingCustomer.getValue()) {
            firstName.setComponentError(new UserError("Name can not be empty"));
            passed = false;

        }
        if (ValidatorUtil.isEmpty(contact)&&!existingCustomer.getValue()) {
            contact.setComponentError(new UserError("Contact number can not be empty"));
            passed = false;
        }
        if (ValidatorUtil.isEmpty(address)&&!existingCustomer.getValue()) {
            address.setComponentError(new UserError("Enter Address"));
            passed = false;
        }
        if (product.isEmpty()) {
            product.setComponentError(new UserError("You need to select product"));
            passed = false;
        }
        if (quantity.isEmpty()) {
            quantity.setComponentError(new UserError("Select a number of eggs you want"));
            passed = false;
        }
        return passed;
    }

    private void delete() {
        CustomerService.delete(customer);
        myUI.updateList();
    }

    private void save() {
        customer = getCustomerInformation();
        if(customer == null){
            pupulateCustomer();
        }

        Product dbProduct = ProductService.getProductByName(product.getValue());
        if (BillService.createBill(customer, dbProduct, quantity.getValue())) {
            myUI.updateList();
            clearData();
        } else {
            Window popupWindow = new Window(" Adding Order ");
            VerticalLayout subContent = new VerticalLayout();
            popupWindow.setContent(subContent);
            subContent.addComponent(new Label("Sorry your order did not get completed"));
            subContent.addComponents(new Button("Ok"));
            popupWindow.center();

        }

    }

    private void pupulateCustomer() {
        customer.setFirstName(firstName.getValue());
        customer.setLastName(lastName.getValue());
        customer.setAddress(address.getValue());
        customer.setContact(contact.getValue());
        CustomerService.add(customer);
    }

    private Customer getCustomerInformation() {

        return CustomerService.getUserByNames(firstName.getValue(),lastName.getValue());
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
