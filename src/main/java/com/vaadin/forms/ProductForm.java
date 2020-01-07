package com.vaadin.forms;

import com.vaadin.MyUI;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.model.Product;
import com.vaadin.service.ProductService;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.log4j.Logger;


public class ProductForm extends Window {

    private static Logger logger = Logger.getLogger(ProductForm.class);

    private TextField productName = new TextField();
    private TextField price = new TextField();

    private Button save = new Button("Add");
    private Button delete = new Button("Cancel");


    private Product product;
    private ProductService productService;
    private MyUI myUI;
    private Binder<Product> productBinder = new Binder<>(Product.class);

    public ProductForm(MyUI myUI) {
        this.myUI = myUI;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);

        productName.setPlaceholder("Product Name");
        productName.setIcon(VaadinIcons.USER);
        productName.setRequiredIndicatorVisible(true);


        price.setPlaceholder("Product Price");
        price.setIcon(VaadinIcons.USER);
        price.setRequiredIndicatorVisible(true);

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        productBinder.forField(productName)
                .withValidator(new StringLengthValidator("Please add Product", 2, null))
                .bind(Product::getProductName, Product::setProductName);
        productBinder.forField(price)
                .withValidator(new StringLengthValidator("Enter Product Price", 2, null))
                .bind(Product::getProductPrice, Product::setProductPrice);

        productBinder.bindInstanceFields(this);

        save.addClickListener(e -> {
            productBinder.validate();
            if (productBinder.isValid()) {
                save();
                close();
            }

        });
        delete.addClickListener(e -> close());
    }


    private void save() {
        product = getCustomerInformation();
        if (product == null) {
            product = new Product();
            pupulateCustomer();
        } else {
            pupulateCustomer();
        }
        productService.save(product);

    }

    private void pupulateCustomer() {
        product.setProductName(productName.getValue());
        product.setProductPrice(price.getValue());
    }

    private Product getCustomerInformation() {

        return productService.getByName(productName.getValue());
    }

}
