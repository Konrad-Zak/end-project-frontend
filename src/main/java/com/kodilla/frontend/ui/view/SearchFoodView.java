package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.AppUserCalorie;
import com.kodilla.frontend.domian.NutrientDto;
import com.kodilla.frontend.domian.SearchFoodDto;
import com.kodilla.frontend.service.SearchFoodService;
import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@CssImport("./style.css")
@Route(value = "searchFoodView", layout = MainView.class)
public class SearchFoodView extends VerticalLayout {
    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private TextField textField = new TextField("Food");
    private Button searchButton = new Button("Search");
    private H3 foodName = new H3();
    private Grid<NutrientDto> grid = new Grid<>(NutrientDto.class);
    private SearchFoodService searchFoodService;


    public SearchFoodView(SearchFoodService searchFoodService) {
        this.searchFoodService = searchFoodService;
        setAlignItems(Alignment.CENTER);
        grid.removeAllColumns();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        horizontalLayout.add(textField,searchButton);
        add(horizontalLayout,foodName,grid);
        searchButton.addClickListener(event -> {
            SearchFoodDto searchFoodDto = searchFoodService.getSearchFoodDto(textField.getValue());
            NutrientDto nutrientDto = searchFoodDto.getParsed().get(0).getFood().getNutrients();
            grid.removeAllColumns();
            foodName.setText(searchFoodDto.getText());
            grid.addColumn(NutrientDto::getProtein).setHeader("Protein [g]");
            grid.addColumn(NutrientDto::getFat).setHeader("Fat [g]");
            grid.addColumn(NutrientDto::getCarbohydrates).setHeader("Carbohydrates [g]");
            grid.addColumn(NutrientDto::getCalories).setHeader("Calories [kcal]");
            grid.setItems(nutrientDto);
        });

    }
}
