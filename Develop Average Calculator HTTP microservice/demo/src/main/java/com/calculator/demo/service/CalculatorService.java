package com.calculator.demo.service;

import com.calculator.demo.model.AuthRequest;
import com.calculator.demo.model.AuthResponse;
import com.calculator.demo.model.CalculatorResponse;
import com.calculator.demo.model.ThirdPartyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CalculatorService {

    static ArrayList<Integer> currentList = new ArrayList<>();
    private RestTemplate restTemplate = new RestTemplate();
    public CalculatorResponse calculate( String mode) throws IOException {
        String token = "";
        ObjectMapper objectMapper = new ObjectMapper();

        //read JSON file and convert to a customer object
        AuthRequest authRequest = objectMapper.readValue(new File("src/main/resources/static/auth.json"), AuthRequest.class);
        HttpEntity authEntity = new HttpEntity(authRequest);
        ResponseEntity<AuthResponse> authresponse =  restTemplate.exchange(
                "http://20.244.56.144/evaluation-service/auth", HttpMethod.POST, authEntity, AuthResponse.class);
        token = authresponse.getBody().getAccessToken();

        String m = "";
        if(mode.equals("e")) m = "even";
        else if(mode.equals("f")) m = "fibo";
        else if(mode.equals("r")) m = "rand";
        else if(mode.equals("p")) m = "primes";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity requestEntity = new HttpEntity<>(headers);
        ResponseEntity<ThirdPartyResponse> response =  restTemplate.exchange(
                "http://20.244.56.144/evaluation-service/" + m, HttpMethod.GET, requestEntity, ThirdPartyResponse.class);
        CalculatorResponse result = new CalculatorResponse();
        result.setWindowPrevState(new ArrayList<>(currentList));

        for(int val: response.getBody().getNumbers()){
            boolean flag=false;
            for(int it: currentList){
                if(it == val) {
                    flag = true;
                    break;
                }
            }
            if(!flag) currentList.add(val);

            int size = currentList.size();
            if(size > 10) currentList.remove(0);

        }
        result.setWindowCurrState(new ArrayList<>(currentList));
        double sum=0;
        for(int it: currentList){
            sum += it;
        }
        result.setNumbers(response.getBody().getNumbers());
        result.setAvg(Double.valueOf(sum/currentList.size()));
        return result;
    }

}
