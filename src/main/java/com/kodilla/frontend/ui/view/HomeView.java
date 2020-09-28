package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@CssImport("./style.css")
@Route(value = "", layout = MainView.class)
public class HomeView extends VerticalLayout {

    H1 h1 = new H1("Welcome in App nutritional ");

    public HomeView() {
        addClassName("home-view");
        add(h1);
    }
}
