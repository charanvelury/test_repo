package com.example.demo;

import com.example.demo.service.DemoService;
import com.example.demo.service.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class HelloWorldControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemoService demoService;

    @Test
    public void  testHelloWorldController() throws Exception {
        Employee employee=new Employee("Emp05","MNO","Business Analyst",300000);
      Mockito.when(demoService.showEmployeeOfId(anyString())).thenReturn(employee);
      //  String json = mapper.writeValueAsString(signUpRequest);
        mockMvc.perform(MockMvcRequestBuilders.get("/getEmployees/{id}","Emp05").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk()).andExpect(jsonPath("$.employeeName").value("MNO")).andExpect(jsonPath("$.salary").value(300000));
    }
}
