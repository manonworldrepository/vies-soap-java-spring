package com.example.demo.config;

import com.example.demo.DemoApplication;
import com.example.demo.controller.ViesController;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration
@SpringBootTest(classes = { DemoApplication.class, CucumberRunner.OnDemandTestConfig.class })
@CucumberContextConfiguration
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.example.demo"},
    plugin = {"pretty", "html:build/cucumber-reports"}
)
public class CucumberRunner {
    @EnableAutoConfiguration
    @Import({ ViesController.class })
    public static class OnDemandTestConfig {}
}