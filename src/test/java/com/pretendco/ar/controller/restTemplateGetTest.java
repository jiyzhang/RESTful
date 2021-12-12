package com.pretendco.ar.controller;

import com.pretendco.ar.entity.ARDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class restTemplateGetTest {
    @Test
    public void restTemplateGetTest() {
        RestTemplate restTemplate = new RestTemplate();
        ARDemo ARDemo = restTemplate.getForObject("https://api.aarstore.com:8080/ardemo/3"
                , ARDemo.class);
        System.out.println(ARDemo);
    }
}