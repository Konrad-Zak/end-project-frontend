package com.kodilla.frontend.service;

import com.kodilla.frontend.client.config.AppUserMessageClient;
import com.kodilla.frontend.domian.AppUserMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserMessageService {

    private AppUserMessageClient appUserMessageClient;

    public Boolean createNewMessage(AppUserMessage appUserMessage) {
        return appUserMessageClient.createNewMessage(appUserMessage);
    }
}
