package com.vaadin.forms;

import com.vaadin.MyUI;
import com.vaadin.constants.QuantityConstants;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.model.Customer;
import com.vaadin.model.OrderData;
import com.vaadin.model.Product;
import com.vaadin.server.UserError;
import com.vaadin.service.BillService;
import com.vaadin.service.CustomerService;
import com.vaadin.service.ProductService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.log4j.Logger;


public class UpdateOrderForm extends FormLayout {

    private static Logger logger = Logger.getLogger(UpdateOrderForm.class);

    private TextField firstName= new TextField();
    private TextField lastName = new TextField();
    private TextField payment = new TextField();
    private TextField productName= new TextField();
    private TextField productPrice= new TextField();

    private Button save = new Button("Update");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");


    private Customer customer;
    private MyUI myUI;
    private Binder<OrderData> customerBinder = new Binder<>(OrderData.class);

    public UpdateOrderForm(MyUI myUI, OrderData orderData) {
        this.myUI = myUI;


        Panel panel = new Panel("Order Payment");
        panel.setSizeUndefined();
        panel.setContent(this);

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete,cancel);
        firstName.setValue(orderData.getFirstName());
        firstName.setEnabled(false);
        lastName .setValue(orderData.getLastName());
        lastName.setEnabled(false);
        productName .setValue(orderData.getProductName());
        productName.setEnabled(false);
        productPrice .setValue(orderData.getProductPrice().toString());
        productPrice.setEnabled(false);

        payment.setPlaceholder("Enter amount paid");
        payment.setIcon(VaadinIcons.COINS);
        payment.setRequiredIndicatorVisible(true);



        addComponents(firstName, lastName, productName, productPrice,payment, buttons);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
        cancel.addClickListener(e->this.close());
    }

    private void delete() {
//        CustomerService.delete(customer);
        myUI.updateList();
        this.close();
    }

    private void save() {
        if(payment.getValue().equals("")){
            payment.setComponentError(new UserError("You need to enter amount"));
        }else{
            myUI.updateList();
            this.close();
        }

    }

    private void close(){
        myUI.closeWindow();
    }

}
