package com.example.demo.service;

import com.example.demo.service.pojo.Employee;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Component
public class RestClient {

 @Autowired
 RestTemplate restTemplate;

 public Employee fetchEmployee(String employeeId) {
     String url = "http://localhost:9003/getEmployees/" + employeeId;
    Employee employee= restTemplate.getForObject(url,Employee.class);

     return employee;
 }


}
