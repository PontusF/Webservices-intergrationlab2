package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@SpringBootApplication
//@EnableDiscoveryClient
public class WarehouseApplication {


    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
