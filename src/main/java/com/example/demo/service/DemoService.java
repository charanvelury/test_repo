package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


public class DemoService {


    public DemoInterface demoInterface;

    public DemoService(DemoInterface demoInterface) {
        this.demoInterface = demoInterface;
    }

    public List<String> showDemoObjectsOfUser() {
        List<String> objectList = demoInterface.getObjectsList();
        List<String> result = objectList.stream().filter(s -> s.contains("Java")).collect(Collectors.toList());
        return result;
    }
}