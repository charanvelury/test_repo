package com.example.demo;

import com.example.demo.service.DemoInterface;
import com.example.demo.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DemoServiceTest {


    @Test
    void testGetObjects() {
        DemoInterface demoInterface = mock(DemoInterface.class);

        List<String> combinedList = Arrays.asList(" Use Core Java ", " Use Spring Core ", " Java w3eHibernate ", " Use Spring MVC ");
        when(demoInterface.getObjectsList()).thenReturn(combinedList);
        DemoService demoService=new DemoService(demoInterface);
        List<String> result=demoService.showDemoObjectsOfUser();
        assertEquals(2,result.size());

    }
}
