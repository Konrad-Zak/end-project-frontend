package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.NutrientDto;
import com.kodilla.frontend.domian.SearchFoodDto;
import com.kodilla.frontend.service.SearchFoodService;
import com.kodilla.frontend.ui.MainView;
import com.kodilla.frontend.validate.ValidateFormField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "searchFoodView", layout = MainView.class)
public class SearchFoodView extends VerticalLayout {

    private static final String NOT_OK_NOTIFICATION = "Something gone wrong";
    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private TextField searchFoodField = new TextField("Food");
    private Button searchButton = new Button("Search");
    private H3 foodName = new H3();
    private H2 noFoundInfo = new H2();
    private Grid<NutrientDto> grid = new Grid<>(NutrientDto.class);
    private Notification notOKNotification = new Notification(NOT_OK_NOTIFICATION,3000);
    private SearchFoodService searchFoodService;
    private ValidateFormField validateFormField;


    public SearchFoodView(SearchFoodService searchFoodService, ValidateFormField validateFormField) {
        this.searchFoodService = searchFoodService;
        this.validateFormField = validateFormField;
        init();
        add(horizontalLayout,foodName,noFoundInfo,grid);
        clickSearchButton();
    }

    private void init() {
        setAlignItems(Alignment.CENTER);
        grid.removeAllColumns();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        horizontalLayout.add(searchFoodField,searchButton);
    }

    private void clickSearchButton() {
        searchButton.addClickListener(event -> {
            if(validateFormField.validateSearchFoodField(searchFoodField.getValue())){
                clickSearchButtonProcess();
            } else {
                notOKNotification.open();
            }
            searchFoodField.clear();
        });
    }

    private void clickSearchButtonProcess() {
        SearchFoodDto searchFoodDto = searchFoodService.getSearchFoodDto(searchFoodField.getValue());
        if(searchFoodDto.getParsed().size()>0){
            showResult(searchFoodDto);
        } else {
            noFoundInfo.setText("Sorry but is not info about search food: " + searchFoodField.getValue());
            notOKNotification.open();
        }
    }

    private void showResult(SearchFoodDto searchFoodDto) {
        NutrientDto nutrientDto = searchFoodDto.getParsed().get(0).getFood().getNutrients();
        foodName.setText(searchFoodDto.getText());
        grid.removeAllColumns();
        grid.addColumn(NutrientDto::getProtein).setHeader("Protein [g]");
        grid.addColumn(NutrientDto::getFat).setHeader("Fat [g]");
        grid.addColumn(NutrientDto::getCarbohydrates).setHeader("Carbohydrates [g]");
        grid.addColumn(NutrientDto::getCalories).setHeader("Calories [kcal]");
        grid.setItems(nutrientDto);
    }
}
