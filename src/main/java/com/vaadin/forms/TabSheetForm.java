package com.vaadin.forms;

import com.vaadin.MyUI;
import com.vaadin.constants.CustomerStatus;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.model.Bill;
import com.vaadin.model.Customer;
import com.vaadin.service.BillService;
import com.vaadin.ui.*;

import java.util.List;

public class TabSheetForm extends FormLayout {

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField email = new TextField();
    private NativeSelect<CustomerStatus> status = new NativeSelect<>();
    private InlineDateField birthdate = new InlineDateField();
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private TabSheet tabSheet;
    private MyUI myUI;
    private Binder<Customer> customerBinder = new Binder<>(Customer.class);
    private VerticalLayout tab3 = new VerticalLayout();
    private VerticalLayout tab1 = new VerticalLayout();
    private VerticalLayout tab2 = new VerticalLayout();

    public TabSheetForm(MyUI myUI){
        this.myUI = myUI;
        setSizeUndefined();
         tabSheet = new TabSheet();
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(tabSheet);
        tab1.addComponent(new Label("A brief summary of our product growth"));tab1.setCaption("Product Summary");
        tabSheet.addTab(tab1).setIcon(VaadinIcons.PACKAGE);
        tab2.addComponent(new Label("A brief summary of our customer growth"));tab2.setCaption("Customer Summary");
        tabSheet.addTab(tab2).setIcon(VaadinIcons.USER);
        tab3.addComponent(new Label("List of stock went out"));tab3.setCaption("Stork list");

        addComponent(layout);
    }

    public void updateScreen(){


        final long[] total_quantity = {0};

        List<Bill> bills = BillService.getAll();

        bills.forEach(e-> {
            System.out.println(e.getQuantity());

            total_quantity[0] = Long.sum(total_quantity[0],e.getQuantity());
        });
        tab3.addComponent(new Label("Total quantity is :"+ total_quantity[0]));

        tabSheet.addTab(tab3).setIcon(VaadinIcons.USER);

    }


}
