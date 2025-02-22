package com.example.demo.steps;

import com.example.demo.service.ViesService;
import com.example.demo.vies.CheckVat;
import com.example.demo.vies.CheckVatResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.client.core.WebServiceTemplate;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ViesStepDefinitions {

    @Mock
    private WebServiceTemplate webServiceTemplate;

    @InjectMocks
    private ViesService viesService;

    private ResponseEntity<CheckVatResponse> response;
    private String countryCode;
    private String vatNumber;

    @Given("a country code {string} and a VAT number {string}")
    public void a_country_code_and_a_vat_number(String countryCode, String vatNumber) {
        MockitoAnnotations.openMocks(this);
        this.countryCode = countryCode;
        this.vatNumber = vatNumber;
    }

    @When("I check the VAT number")
    public void i_check_the_vat_number() throws Exception {
        CheckVatResponse mockResponse = MockResponseFactory.createMockResponse();

        when(webServiceTemplate.marshalSendAndReceive(any(CheckVat.class)))
                .thenReturn(mockResponse);

        Mono<CheckVatResponse> result = viesService.checkVat(countryCode, vatNumber);

        CheckVatResponse responseBody = result.block();
        response = ResponseEntity.ok(responseBody);
    }

    @Then("the response should be valid")
    public void the_response_should_be_valid() {
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isValid());
    }

    @Then("the response should contain the country code {string}")
    public void the_response_should_contain_the_country_code(String expectedCountryCode) {
        assertEquals(expectedCountryCode, Objects.requireNonNull(response.getBody()).getCountryCode());
    }

    @Then("the response should contain the VAT number {string}")
    public void the_response_should_contain_the_vat_number(String expectedVatNumber) {
        assertEquals(expectedVatNumber, Objects.requireNonNull(response.getBody()).getVatNumber());
    }
}