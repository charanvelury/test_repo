package com.example.demo.service;

import com.example.demo.service.pojo.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component("demoImplementer")
public class DemoInterfaceImplementer implements DemoInterface {

    @Override
    public List<String> getObjectsList()
    {        return Arrays.asList(" Use Core Java ", " Use Spring Core ", " Java w3eHibernate ", " Use Spring MVC ");
    }

    @Override
    public List<Employee> getEmployees()
    {
        List<Employee> employeeList=new ArrayList<Employee>();
        employeeList.add(new Employee("Emp100","ABC", "Operations Engineer",70000));
        employeeList.add(new Employee("Emp101","DEF", "QA Analyst",130000));
        employeeList.add(new Employee("Emp102","GHI", "Project Manager",300000));
        employeeList.add(new Employee("Emp103","JKL", "Software Developer",140000));

        return employeeList;
    }
}
