package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.config.Curio;
import com.kodilla.frontend.ui.view.CreateUser;
import com.kodilla.frontend.ui.view.CurioView;
import com.kodilla.frontend.ui.view.HomeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

@CssImport("./style.css")
public class MainView extends AppLayout {

    private Anchor logout = new Anchor("logout", "Log out");

    public MainView() {
        createHeader();
        createDrawer();
        clickLogout();
    }


    private void createHeader() {
        H1 logo = new H1("App nutritional plan");
        logo.addClassName("logo");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink routerLink = new RouterLink("Home", HomeView.class);
        routerLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(routerLink,
                    new RouterLink("Curio", CurioView.class),
                    new RouterLink("CreateUser",CreateUser.class)));
    }

    private void clickLogout(){
        logout.addAttachListener(event -> {
            Curio.getInstance().setText(null);
            Curio.getInstance().setYear(null);
        });
    }

}
