package com.example.demo.steps;

import com.example.demo.vies.CheckVatResponse;
import jakarta.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;

public class MockResponseFactory {

    public static CheckVatResponse createMockResponse() throws Exception {
        CheckVatResponse response = new CheckVatResponse();
        response.setCountryCode("DE");
        response.setVatNumber("123456789");
        response.setRequestDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2023-10-01"));
        response.setValid(true);

        QName nameQName = new QName("urn:ec.europa.eu:taxud:vies:services:checkVat:types", "name");
        JAXBElement<String> name = new JAXBElement<>(nameQName, String.class, "Test Company");
        response.setName(name);

        QName addressQName = new QName("urn:ec.europa.eu:taxud:vies:services:checkVat:types", "address");
        JAXBElement<String> address = new JAXBElement<>(addressQName, String.class, "Test Address");
        response.setAddress(address);

        return response;
    }
}