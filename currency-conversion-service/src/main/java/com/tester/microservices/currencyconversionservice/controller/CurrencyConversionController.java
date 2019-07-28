package com.tester.microservices.currencyconversionservice.controller;

import com.tester.microservices.currencyconversionservice.CurrencyExchangeServicesProxy;
import com.tester.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {


    @Autowired
    private CurrencyExchangeServicesProxy proxy;


    @GetMapping("/currency-convert-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversionFiegn(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionBean currencyConversionBean = proxy.retrieveExchangeValue(from, to);
        currencyConversionBean.setQuantity(quantity);
        currencyConversionBean.setTotalCalculatedAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));
        return currencyConversionBean;
    }


    @GetMapping("/currency-convert-old/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        String uri = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
        Map<String, String> uriParamMap = new HashMap<>();
        uriParamMap.put("from", from);
        uriParamMap.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(uri, CurrencyConversionBean.class, uriParamMap);
        CurrencyConversionBean entityBody = responseEntity.getBody();
        entityBody.setQuantity(quantity);
        entityBody.setTotalCalculatedAmount(quantity.multiply(entityBody.getConversionMultiple()));

        // CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(111l, from, to, BigDecimal.valueOf(65.5f), quantity, BigDecimal.valueOf(20.f), "8100");

        return entityBody;
    }

}
