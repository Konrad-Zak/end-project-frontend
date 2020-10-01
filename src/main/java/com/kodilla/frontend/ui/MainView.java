package com.kodilla.frontend.ui;

import com.kodilla.frontend.domian.AppUserDtoMap;
import com.kodilla.frontend.domian.AppUserInfoDtoMap;
import com.kodilla.frontend.domian.CurioDtoMap;
import com.kodilla.frontend.ui.view.*;
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
import com.vaadin.flow.server.VaadinSession;


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
        RouterLink home = new RouterLink("Home", HomeView.class);
        home.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(home,
                    new RouterLink("Curio", CurioView.class),
                    new RouterLink("Change Password", ChangePasswordView.class),
                    new RouterLink("User Info", UserInfoView.class),
                    new RouterLink("Calorie Calculator", CalorieCalculatorView.class),
                    new RouterLink("Contact", MessageView.class)
        ));

    }

    private void clickLogout(){
        logout.addDetachListener(event -> {
            CurioDtoMap.getInstance().deleteCurrentSession(VaadinSession.getCurrent());
            AppUserInfoDtoMap.getInstance().deleteCurrentSession(VaadinSession.getCurrent());
            CurioDtoMap.getInstance().deleteCurrentSession(VaadinSession.getCurrent());
            AppUserDtoMap.getInstance().deleteCurrentSession(VaadinSession.getCurrent());
        });
    }

}
