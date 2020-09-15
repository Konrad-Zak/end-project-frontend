package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.CurioMap;
import com.kodilla.frontend.domian.CurioDto;
import com.kodilla.frontend.service.CurioService;
import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@CssImport("./style.css")
@Route(value = "curio", layout = MainView.class)
public class CurioView extends VerticalLayout {
    private final static String DEFAULT_TEXT = "Sorry but we are working for this section";
    H1 curioHeader = new H1("Curio of current day: ");
    H2 curioText =  new H2();
    H3 curioYear = new H3();
    private CurioService curioService;

    public CurioView(CurioService curioService){
        this.curioService = curioService;
        setClassName("curio-view");
        readCurio();
        showCurio();
    }

    private void showCurio() {
        if(CurioMap.getInstance().getMap().containsKey(VaadinSession.getCurrent())){
            curioText.setText(CurioMap.getInstance().getCurioDtoByKey(VaadinSession.getCurrent()).getText());
            curioYear.setText("Year: " + CurioMap.getInstance().getCurioDtoByKey(VaadinSession.getCurrent()).getYear());
            add(curioHeader,curioText,curioYear);
        } else {
            curioText.setText(DEFAULT_TEXT);
            add(curioText);
        }
    }

    private void readCurio(){
        if(!CurioMap.getInstance().getMap().containsKey(VaadinSession.getCurrent())){
            CurioDto curioDto = curioService.getCurioDto();
            CurioMap.getInstance().addToMap(VaadinSession.getCurrent(),curioDto);
        }
    }

}
