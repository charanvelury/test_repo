package com.example.demo.service;

import com.example.demo.service.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DemoService {


    //public DemoInterface demoInterface;

    @Autowired
    private NumberProvisionService nps;

    @Autowired
    @Qualifier("demoImplementer")
    DemoInterface demoInterface;
/*
   public DemoService(DemoInterface demoInterface) {
        this.demoInterface = demoInterface;
    }

  public List<String> showDemoObjectsOfUser() {
        List<String> objectList = demoInterface.getObjectsList();
        List<String> result = objectList.stream().filter(s -> s.contains("Java")).collect(Collectors.toList());
        return result;
    }*/

    public int calculate() {
        // NumberProvisionService nps=new NumberProvisionService();
        List<Integer> list = Arrays.asList(10, 11, 12, 13, 14);
        List<Integer> result = nps.getNumbers(list);
        return result.size();
    }

    public String getSomeName(String name) {
        String result = nps.getName(name);
        return result;
    }

    public void pintSomeName(String name) {
        nps.printName(name);
    }

    public Employee showEmployeeOfId(String employeeId) {
        List<Employee> employeeList = demoInterface.getEmployees();
        Employee employee = employeeList.stream().filter(s -> s.getEmployeeId().equalsIgnoreCase(employeeId)).collect(Collectors.toList()).get(0);
        return employee;
    }
}