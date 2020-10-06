package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.CurioDtoMap;
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

    private static final String DEFAULT_TEXT = "Sorry but we do not have curio of today... Try later";
    private H1 curioHeader = new H1("Curio of current day: ");
    private H2 curioText =  new H2();
    private H3 curioYear = new H3();
    private CurioService curioService;

    public CurioView(CurioService curioService) {
        this.curioService = curioService;
        setClassName("curio-view");
        readCurio();
        showCurio();
    }

    private void showCurio() {
        if(CurioDtoMap.getInstance().getMap().containsKey(VaadinSession.getCurrent())){
            curioText.setText(CurioDtoMap.getInstance().getCurioDtoByKey(VaadinSession.getCurrent()).getText());
            curioYear.setText("Year: " + CurioDtoMap.getInstance().getCurioDtoByKey(VaadinSession.getCurrent()).getYear());
            add(curioHeader,curioText,curioYear);
        } else {
            notFoundCurioProcess();
        }
    }

    private void readCurio() {
        if(!CurioDtoMap.getInstance().getMap().containsKey(VaadinSession.getCurrent())){
            readCurioProcess();
        }
    }

    private void readCurioProcess() {
        CurioDto curioDto = curioService.getCurioDto();
        if(curioDto.getYear()!=null){
            CurioDtoMap.getInstance().addToMap(VaadinSession.getCurrent(),curioDto);
        } else {
            notFoundCurioProcess();
        }
    }

    private void notFoundCurioProcess() {
        curioText.setText(DEFAULT_TEXT);
        add(curioText);
    }

}
