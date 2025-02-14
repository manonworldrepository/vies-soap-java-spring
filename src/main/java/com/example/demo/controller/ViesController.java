package com.example.demo.controller;

import com.example.demo.service.ViesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.example.demo.vies.CheckVatResponse;

@RestController
public class ViesController {

    @Autowired
    private ViesService viesService;

    @GetMapping(value = "/vies/checkVat/{countryCode}/{vatNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CheckVatResponse> checkVat(@PathVariable String countryCode, @PathVariable String vatNumber) {
        return viesService.checkVat(countryCode, vatNumber);
    }
}
