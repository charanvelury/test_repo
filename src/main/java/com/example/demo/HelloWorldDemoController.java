package com.example.demo;

import com.example.demo.service.DemoInterface;
import com.example.demo.service.DemoInterfaceImplementer;
import com.example.demo.service.DemoService;
import com.example.demo.service.RestClient;
import com.example.demo.service.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldDemoController
{

    @Autowired
    private DemoService demoService;

    @Autowired
    private RestClient restClient;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

  /* @GetMapping("/getObjects")
    public List<String> getObjects()
    {
        DemoInterface demoInterface=new DemoInterfaceImplementer();
        DemoService demoService=new DemoService(demoInterface);
        return demoService.showDemoObjectsOfUser();
    }*/

     @GetMapping("/getEmployees/{id}")
    public Employee getEmployeeOfId(@PathVariable String id)
    {
        return demoService.showEmployeeOfId(id);
    }

    @GetMapping("/callEmployeeApi/{id}")
    @ResponseBody
    public Employee callEmployeeApi(@PathVariable String id)
    {
        return restClient.fetchEmployee(id);
    }

}