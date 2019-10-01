package com.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerForm extends FormLayout {

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField contact = new TextField();
    private TextField address = new TextField();
    private NativeSelect<CustomerStatus> product = new NativeSelect<>();
    private NativeSelect<QuantityConstants> quantity = new NativeSelect<>();

    private InlineDateField orderDate = new InlineDateField();
    private Button save = new Button("Order");
    private Button delete = new Button("Update");


    private  CustomerService customerService = CustomerService.getInstance();
    private Customer customer;
    private MyUI myUI;
    private Binder<Customer> customerBinder = new Binder<>(Customer.class);

    public CustomerForm(MyUI myUI){
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

        addComponents(firstName, lastName, contact, address,product, quantity,orderDate,buttons);

        product.setItems(CustomerStatus.values());
        quantity.setItems(QuantityConstants.values());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);



        customerBinder.bindInstanceFields(this);
        save.addClickListener(e-> this.save());
        delete.addClickListener(e-> this.delete());
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customerBinder.setBean(customer);

        delete.setVisible(customer.isPersisted());
        setVisible(true);
        firstName.selectAll();
    }

    private void delete(){
        customerService.delete(customer);
        myUI.updateList();
        setVisible(false);
    }

    private void save(){

        Window popupWindow = new Window(" Action being performed ");
        VerticalLayout subContent = new VerticalLayout();
        popupWindow.setContent(subContent);
        subContent.addComponent(new Label("You are about to add Customer are you sure?"));
        subContent.addComponents(new Button("Yes"),new Button("No"));
        popupWindow.center();
        myUI.openWindow(popupWindow);


//        customerService.save(customer);
//        myUI.updateList();
//        setVisible(true);
    }
}
