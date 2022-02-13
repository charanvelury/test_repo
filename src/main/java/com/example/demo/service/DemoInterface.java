package com.example.demo.service;

import com.example.demo.service.pojo.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DemoInterface {
    public List<String> getObjectsList();
    public List<Employee> getEmployees();
}
