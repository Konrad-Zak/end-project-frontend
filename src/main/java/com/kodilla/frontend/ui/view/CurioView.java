package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.client.config.Curio;
import com.kodilla.frontend.domian.CurioDto;
import com.kodilla.frontend.service.CurioService;
import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

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
        if(Curio.getInstance().getText() == null){
            curioText.setText(DEFAULT_TEXT);
            add(curioText);
        } else {
            curioText.setText(Curio.getInstance().getText());
            curioYear.setText("Year: " + Curio.getInstance().getYear());
            add(curioHeader,curioText,curioYear);
        }
    }

    private void readCurio(){
        if(Curio.getInstance().getText() == null){
            CurioDto curioDto = curioService.getCurioDto();
            Curio.getInstance().setText(curioDto.getText());
            Curio.getInstance().setYear(curioDto.getYear());
        }
    }

}
