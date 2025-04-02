package com.calculator.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class CalculatorResponse {
    private ArrayList<Integer> windowPrevState;
    private ArrayList<Integer> windowCurrState;
    private ArrayList<Integer> numbers;
    private Double avg;

    public ArrayList<Integer> getWindowPrevState() {
        return windowPrevState;
    }

    public void setWindowPrevState(ArrayList<Integer> windowPrevState) {
        this.windowPrevState = windowPrevState;
    }

    public ArrayList<Integer> getWindowCurrState() {
        return windowCurrState;
    }

    public void setWindowCurrState(ArrayList<Integer> windowCurrState) {
        this.windowCurrState = windowCurrState;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
