package com.tester.microservices.limitservices.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tester.microservices.limitservices.bean.LimitConfiguration;
import com.tester.microservices.limitservices.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitServicesController {
    @Autowired
    private Configuration configuration;

    @GetMapping("/limit")
    @HystrixCommand(fallbackMethod = "loadLimitConfigurationFallBack")
    public LimitConfiguration loadLimitConfiguration() {
        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }


    @GetMapping("/limit-fall-back")
    @HystrixCommand(fallbackMethod = "loadLimitConfigurationFallBack")
    public LimitConfiguration loadLimitConfigurationFallTolorance() {
        if(true){
            throw new RuntimeException("Not Able to Connect");
        }

        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }


    @GetMapping("/limit-fallBack")
    public LimitConfiguration loadLimitConfigurationFallBack() {
        return new LimitConfiguration(10, 100);
    }



}
