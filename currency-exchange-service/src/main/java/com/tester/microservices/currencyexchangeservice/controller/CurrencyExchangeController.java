package com.tester.microservices.currencyexchangeservice.controller;

import com.tester.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.tester.microservices.currencyexchangeservice.excetion.ExchangeValueNotFoundException;
import com.tester.microservices.currencyexchangeservice.repository.ExchangeJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurrencyExchangeController {
    @Autowired
    private Environment enviroment;
    @Autowired
    private ExchangeJPARepository exchangeJPARepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeJPARepository.findByFromAndTo(from, to);
        if (exchangeValue == null) {
            throw new ExchangeValueNotFoundException("Exchange Value not found for " + from + " to " + to);
        }
        exchangeValue.setPort(enviroment.getProperty("local.server.port"));
        return exchangeValue;
    }

    @PutMapping("/currency-exchanges")
    public ExchangeValue update(@RequestBody ExchangeValue exchangeValue) {
        ExchangeValue exchangeValueUpdated = exchangeJPARepository.save(exchangeValue);
        if (exchangeValueUpdated == null) {
            throw new ExchangeValueNotFoundException("Exchange Value not found for " + exchangeValue.getFrom() + " to " + exchangeValue.getTo());
        }
        return exchangeValueUpdated;
    }


    @GetMapping("/currency-exchanges")
    public List<ExchangeValue> retrieveAllExchangeValue() {
        return exchangeJPARepository.findAll();
    }

}
