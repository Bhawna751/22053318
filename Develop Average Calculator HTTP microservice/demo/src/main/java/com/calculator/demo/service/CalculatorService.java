package com.calculator.demo.service;

import com.calculator.demo.model.CalculatorResponse;
import com.calculator.demo.model.ThirdPartyResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CalculatorService {
    static ArrayList<Integer> currentList = new ArrayList<>();
    private RestTemplate restTemplate = new RestTemplate();
    public CalculatorResponse calculate( String mode){
        String token = "";

        String m = "";
        if(mode.equals("e")) m = "even";
        else if(mode.equals("f")) m = "fibo";
        else if(mode.equals("r")) m = "rand";
        else if(mode.equals("p")) m = "primes";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiZXhwIjoxNzQzNjA1NjQxLCJpYXQiOjE3NDM2MDUzNDEsImlzcyI6IkFmZm9yZG1lZCIsImp0aSI6ImQ2N2FmYjAwLTQ3N2QtNDg0MC04ODIyLTFmNWI2MTJmZTAyNiIsInN1YiI6ImJoYXduYXBpbGxhaTc1MUBnbWFpbC5jb20ifSwiZW1haWwiOiJiaGF3bmFwaWxsYWk3NTFAZ21haWwuY29tIiwibmFtZSI6ImJoYXduYSBwaWxsYWkiLCJyb2xsTm8iOiIyMjA1MzMxOCIsImFjY2Vzc0NvZGUiOiJud3B3cloiLCJjbGllbnRJRCI6ImQ2N2FmYjAwLTQ3N2QtNDg0MC04ODIyLTFmNWI2MTJmZTAyNiIsImNsaWVudFNlY3JldCI6Im1rUHd1TW5uRkVyV1Z0QkoifQ.94LMMxReVL1BKLCJhQ4BMxuyc6tLmfGQf2Legw7w_hc");

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
