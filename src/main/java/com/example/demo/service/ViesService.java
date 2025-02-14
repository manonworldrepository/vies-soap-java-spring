package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import reactor.core.publisher.Mono;
import com.example.demo.vies.CheckVat;
import com.example.demo.vies.CheckVatResponse;

@Service
public class ViesService {

    private final WebServiceTemplate webServiceTemplate;

    public ViesService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public Mono<CheckVatResponse> checkVat(String countryCode, String vatNumber) {
        return Mono.fromCallable(() -> {
            CheckVat request = new CheckVat();
            request.setCountryCode(countryCode);
            request.setVatNumber(vatNumber);

            return (CheckVatResponse) webServiceTemplate.marshalSendAndReceive(request);
        });
    }
}


