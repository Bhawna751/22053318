package com.calculator.demo.controller;

import com.calculator.demo.model.CalculatorResponse;
import com.calculator.demo.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    @Autowired
    private CalculatorService service;
    @GetMapping("/numbers/{mode}")
    public CalculatorResponse calculate(@PathVariable("mode") String mode){
        return service.calculate(mode);
    }
}
