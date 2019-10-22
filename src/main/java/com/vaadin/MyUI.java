package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.forms.OrderingForm;
import com.vaadin.forms.TabSheetForm;
import com.vaadin.forms.UpdateOrderForm;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.model.OrderData;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.service.BillService;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {


    private Grid<OrderData> grid = new Grid<>(OrderData.class);
    private TextField filter = new TextField();
    private OrderingForm form = new OrderingForm(this);
    private TabSheetForm tabForm = new TabSheetForm(this);
    private BillService billService = BillService.getInstance();
    private Window popupWindow;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        try {
//            FireBase.initialise();

            final VerticalLayout layout = new VerticalLayout();
            Panel panel = new Panel("Place an Order");
            panel.setSizeUndefined();
            panel.setContent(form);
//            setTheme("newtheme");

            filter.setPlaceholder("search by name...");
            filter.addValueChangeListener(e -> updateList());
            filter.setValueChangeMode(ValueChangeMode.LAZY);

            Button clearbtn = new Button(VaadinIcons.CLOSE);
            clearbtn.setDescription("Clean the search");
            clearbtn.addClickListener(e -> filter.clear());

            CssLayout styling = new CssLayout();
            styling.addComponents(filter, clearbtn);
            styling.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);


            grid.setColumns("firstName", "lastName", "orderDate", "productName", "productPrice");

            HorizontalLayout main = new HorizontalLayout(grid, panel);
            main.setSizeFull();
            grid.setSizeFull();
            tabForm.setSizeFull();
            main.setExpandRatio(grid, 2);
            layout.addComponents(styling, main, tabForm);
            layout.addComponents(styling, main, tabForm);

            grid.addItemClickListener(e -> {
                Panel paymentPanel = new Panel("Order Pyment");
                paymentPanel.setSizeUndefined();
                paymentPanel.setContent(new UpdateOrderForm(this, e.getItem()));
                popupWindow = new Window();
                popupWindow.center();
                popupWindow.setClosable(false);
                popupWindow.setModal(true);
                popupWindow.setResizable(false);
                popupWindow.setContent(paymentPanel);
                openWindow();

            });
            updateList();
            setContent(layout);

        } catch (Exception e) {
            setContent(new Label(
                    "Unable to setup Firebase connection. See the log for details"));
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Failed to init firebase", e);

        }
    }

    public void updateList() {
//         fetch list of Customers from service and assign it to Grid
        tabForm.updateScreen();
        grid.setItems(billService.findAll());
    }

    public void openWindow() {
        if (popupWindow != null) {
            addWindow(popupWindow);
        }else {
            popupWindow = new Window();
            addWindow(popupWindow);
        }
    }

    public void closeWindow() {
        popupWindow.close();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
