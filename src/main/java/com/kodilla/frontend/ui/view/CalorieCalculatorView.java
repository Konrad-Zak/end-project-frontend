package com.kodilla.frontend.ui.view;


import com.kodilla.frontend.domian.*;
import com.kodilla.frontend.service.CalorieInfoService;
import com.kodilla.frontend.ui.MainView;
import com.kodilla.frontend.validate.ValidateFormField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


@Route(value = "calorieCalculator", layout = MainView.class)
public class CalorieCalculatorView extends VerticalLayout {

    private static final String NOT_OK_NOTIFICATION = "Something gone wrong";
    private static final Double WEIGHT_MIN = 40.0;
    private static final Double WEIGHT_MAX = 200.0;
    private static final Double PHYSICAL_MIN = 1.0;
    private static final Double PHYSICAL_MAX = 5.0;

    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private Notification notOkNotification = new Notification(NOT_OK_NOTIFICATION,3000);
    private NumberField physicalField = new NumberField("Physical fitness: 1-5");
    private NumberField weightField = new NumberField("Approx weight [kg]");
    private Button calculate = new Button("Calculate");
    private Button upload = new Button("Upload");
    private Grid<AppUserCalorie> grid = new Grid<>(AppUserCalorie.class);
    private CalorieInfoService calorieInfoService;
    private ValidateFormField validateFormField;

    public CalorieCalculatorView(CalorieInfoService calorieInfoService, ValidateFormField validateFormField) {
        this.calorieInfoService = calorieInfoService;
        this.validateFormField = validateFormField;
        H2 title = new H2("Daily caloric requirement");
        initFieldsCondition();
        initValue();
        clickCalculateButton();
        clickUploadButton();
        horizontalLayout.add(physicalField,weightField);
        add(title,horizontalLayout, calculate, upload, grid);
    }


    private void initFieldsCondition() {
        setAlignItems(Alignment.CENTER);
        horizontalLayout.setAlignItems(Alignment.AUTO);
        grid.removeAllColumns();
        initPhysicalField();
        initWeightField();
    }

    private void initPhysicalField() {
        physicalField.setHasControls(true);
        physicalField.setStep(1);
        physicalField.setMin(PHYSICAL_MIN);
        physicalField.setMax(PHYSICAL_MAX);
    }

    private void initWeightField() {
        weightField.setHasControls(true);
        weightField.setMin(WEIGHT_MIN);
        weightField.setMax(WEIGHT_MAX);
        weightField.setStep(1);
    }

    private void initValue() {
        Long appUserId = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent()).getId();
        if(CalorieInfoDtoMap.getInstance().getCalorieInfoDtoMap().containsKey(VaadinSession.getCurrent())) {
            loadFromCalorieInfoMap();
        } else if (calorieInfoService.checkExistByAppUserId(appUserId)) {
            showResult(appUserId);
        } else {
            setDefaultValues();
        }
    }

    private void loadFromCalorieInfoMap() {
        CalorieInfoDto calorieInfoDto = CalorieInfoDtoMap.getInstance().getAppUserByKey(VaadinSession.getCurrent());
        setValueFromCalorieInfo(calorieInfoDto);
        showGridResult(calorieInfoDto.getAppUserCalorie());
    }

    private void setValueFromCalorieInfo(CalorieInfoDto calorieInfoDto) {
        upload.setVisible(true);
        calculate.setVisible(false);
        weightField.setValue(calorieInfoDto.getAppUserWeight());
        physicalField.setValue(calorieInfoDto.getAppUserFitness());
    }

    private void setDefaultValues() {
        calculate.setVisible(true);
        upload.setVisible(false);
        weightField.setValue(70d);
        physicalField.setValue(1d);
    }


    private void showResult(Long appUserId) {
        CalorieInfoDto calorieInfoDto = calorieInfoService.getCalorieInfoDtoByAppUserId(appUserId);
        CalorieInfoDtoMap.getInstance().addToMap(VaadinSession.getCurrent(),calorieInfoDto);
        setValueFromCalorieInfo(calorieInfoDto);
        showGridResult(calorieInfoDto.getAppUserCalorie());
    }

    private void showGridResult(AppUserCalorie appUserCalorie) {
        grid.removeAllColumns();
        grid.addColumn(AppUserCalorie::getProtein).setHeader("Protein [g]");
        grid.addColumn(AppUserCalorie::getFat).setHeader("Fat [g]");
        grid.addColumn(AppUserCalorie::getCarbohydrates).setHeader("Carbohydrates [g]");
        grid.addColumn(AppUserCalorie::getCalories).setHeader("Calories [cal]");
        grid.setItems(appUserCalorie);
    }

    private void clickCalculateButton() {
        calculate.addClickListener(event -> {
            if(validateFormField.validateNumberField(WEIGHT_MAX,WEIGHT_MIN,weightField.getValue()) &&
            validateFormField.validateNumberField(PHYSICAL_MAX,PHYSICAL_MIN,physicalField.getValue())){
                clickCalculateButtonProcess();
            } else {
                notOkNotification.open();
            }
        });
    }

    private void clickCalculateButtonProcess() {
            Long appUserId = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent()).getId();
            boolean result = calorieInfoService.createCalorieInfo(
                    appUserId,weightField.getValue(),physicalField.getValue());

            if(result){
                showResult(appUserId);
            } else {
                notOkNotification.open();
            }
    }

    private void clickUploadButton() {
        upload.addClickListener(event -> {
            if(validateFormField.validateNumberField(WEIGHT_MAX,WEIGHT_MIN,weightField.getValue()) &&
                    validateFormField.validateNumberField(PHYSICAL_MAX,PHYSICAL_MIN,physicalField.getValue())){
                clickUploadButtonProcess();
            } else {
                notOkNotification.open();
            }
        });
    }

    private void clickUploadButtonProcess() {
        Long appUserId = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent()).getId();
        calorieInfoService.uploadCalorieInfo(appUserId,weightField.getValue(),physicalField.getValue());
        showResult(appUserId);
    }

}
