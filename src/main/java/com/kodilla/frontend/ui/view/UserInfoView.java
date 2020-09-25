package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.*;
import com.kodilla.frontend.service.AppUserInfoService;
import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


@Route(value = "userInfoView", layout = MainView.class)
public class UserInfoView extends FormLayout {

    private TextField firstName = new TextField("First name");
    private EmailField email = new EmailField("Email");
    private Button create = new Button("Create");
    private Button update = new Button("Update");
    private AppUserInfoService appUserInfoService;

    public UserInfoView(AppUserInfoService appUserInfoService){
        this.appUserInfoService = appUserInfoService;
        update.setVisible(false);
        initValues();
        clickCreateButton();
        add(firstName, email, create, update);
    }

    private void initValues() {
        if (AppUserInfoDtoMap.getInstance().getMapAppUserInfoDto().containsKey(VaadinSession.getCurrent())) {
            getFromAppUserInfoDtoMap();
        } else {
            getFormBackend();
        }
    }

    private void getFormBackend(){
        Long appUserId = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent()).getId();
        AppUserInfoDto appUserInfoDto = appUserInfoService.getAppUserInfoByAppUserId(appUserId);
        if(appUserInfoDto.getFirstName()!= null) {
            AppUserInfoDtoMap.getInstance().addToMap(VaadinSession.getCurrent(), appUserInfoDto);
            firstName.setValue(appUserInfoDto.getFirstName());
            email.setValue(appUserInfoDto.getEmail());
            create.setVisible(false);
            update.setVisible(true);
        }
    }

    private void getFromAppUserInfoDtoMap(){
        AppUserInfoDto appUserInfoDto = AppUserInfoDtoMap.getInstance().getAppUserByKey(VaadinSession.getCurrent());
        firstName.setValue(appUserInfoDto.getFirstName());
        email.setValue(appUserInfoDto.getEmail());
        create.setVisible(false);
        update.setVisible(true);
    }


    private void clickCreateButton() {
        create.addClickListener(event -> {
            AppUserDto appUserDto = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent());
            AppUserInfo appUserInfo = new AppUserInfo(firstName.getValue(), email.getValue(),
                    new AppUser(appUserDto.getId(),appUserDto.getUsername(),appUserDto.getPassword()));
            appUserInfoService.createAppUserInfo(appUserInfo);
            initValues();
        });
    }


}
