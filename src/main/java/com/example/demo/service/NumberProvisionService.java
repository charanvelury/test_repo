package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NumberProvisionService {

   // private List<Integer> numbers;

    public List<Integer> getNumbers(List<Integer> numbers)
    {
        List<Integer> result=new ArrayList<Integer>();

        for(Integer number: numbers)
        {
            if(number % 2==0)
               result.add(number);
        }
        return result;
    }

    public String getName(String input)
    {
        return input + "Data";
    }
    public void printName(String input)
    {
        System.out.println(input.length());
    }
}
