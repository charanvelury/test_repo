package com.example.demo;

import com.example.demo.service.DemoInterface;
import com.example.demo.service.DemoService;
import com.example.demo.service.NumberProvisionService;
import com.example.demo.service.RestClient;
import com.example.demo.service.pojo.Employee;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DemoServiceTest {

    @InjectMocks
    DemoService demoService;

    @Mock
    NumberProvisionService nps;

    @Captor
    ArgumentCaptor argCaptor;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    RestClient restClient;


    @Test
    @Disabled
    void testGetObjects() {
        DemoInterface demoInterface = mock(DemoInterface.class);

        List<String> combinedList = Arrays.asList(" Use Core Java ", " Use Spring Core ", " Java w3eHibernate ", " Use Spring MVC ");
        when(demoInterface.getObjectsList()).thenReturn(combinedList);
       // DemoService demoService=new DemoService(demoInterface);
        // List<String> result=demoService.showDemoObjectsOfUser();
        verify(demoInterface).getObjectsList();
        //assertEquals(2,result.size());

    }

    @Test
    void testCalculate()
    {
        List<Integer> list = Arrays.asList(20,21,22,23,24,25,26);
        verifyNoInteractions(nps);
        nps.getName("Sample");
        verify(nps).getName("Sample");
        verify(nps).getName((String) argCaptor.capture());
        assertEquals("Sample",argCaptor.getValue());
        when(nps.getName(any())).thenReturn("list");
        boolean status=nps.getName("Something").equalsIgnoreCase("list");
        when(nps.getNumbers(any())).thenReturn(list);
        assertEquals(7,demoService.calculate());
        assertEquals("list",demoService.getSomeName("Anything"));
        assertTrue(status);
        verify(nps,times(0)).printName("Sample");
    }

    @Test
    public void whenConfigNonVoidRetunMethodToThrowEx_thenExIsThrown() {
        when(nps.getName(anyString())).thenThrow(NullPointerException.class);
        Throwable exception = assertThrows(NullPointerException.class,
                ()->{
            demoService.getSomeName("Anything");
        } );

    }
@Test
    public void whenConfigVoidRetunMethodToThrowEx_thenExIsThrown() {

      /*  doThrow(NullPointerException.class)
                .when(nps)
                .printName(anyString());

        Throwable exception = assertThrows(NullPointerException.class,
                ()->{
                    demoService.pintSomeName("Anything");
                } );*/

   doCallRealMethod() .when(nps)
           .printName(any());
    Throwable exception = assertThrows(NullPointerException.class,
            ()->{
                nps.printName(null);
            } );
    }

    @Test
    public void testRestCall()
    {
        Employee result=new Employee("Emp05","MNO","Business Analyst",300000);
        when(restTemplate.getForObject(anyString(),any())).thenReturn(result);
        assertEquals("MNO",restClient.fetchEmployee("Emp05").getEmployeeName());

    }
}
