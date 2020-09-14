package com.kodilla.frontend.service;

import com.kodilla.frontend.client.CurioClient;
import com.kodilla.frontend.client.config.Curio;
import com.kodilla.frontend.domian.CurioDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurioService {

    private CurioClient curioClient;


    public CurioDto getCurioDto(){
       return curioClient.getCurio();
    }

}
