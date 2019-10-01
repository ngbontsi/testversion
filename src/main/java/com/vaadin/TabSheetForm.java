package com.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class TabSheetForm extends FormLayout {

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField email = new TextField();
    private NativeSelect<CustomerStatus> status = new NativeSelect<>();
    private InlineDateField birthdate = new InlineDateField();
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");


    private  CustomerService customerService = CustomerService.getInstance();
    private Customer customer;
    private MyUI myUI;
    private Binder<Customer> customerBinder = new Binder<>(Customer.class);

    public TabSheetForm(MyUI myUI){
        this.myUI = myUI;
        setSizeUndefined();
        TabSheet tabSheet = new TabSheet();
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(tabSheet);

        VerticalLayout tab1 = new VerticalLayout();
        tab1.addComponent(new Label("A brief summary of our product growth"));tab1.setCaption("Product Summary");
        tabSheet.addTab(tab1).setIcon(VaadinIcons.PACKAGE);


        VerticalLayout tab2 = new VerticalLayout();
        tab2.addComponent(new Label("A brief summary of our customer growth"));tab2.setCaption("Customer Summary");
        tabSheet.addTab(tab2).setIcon(VaadinIcons.USER);
        addComponent(layout);
    }


}
