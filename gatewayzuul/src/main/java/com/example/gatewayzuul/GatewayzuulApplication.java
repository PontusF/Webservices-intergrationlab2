package com.example.gatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//@Author: Pontus Fredriksson
@EnableZuulProxy
@SpringBootApplication
//@EnableDiscoveryClient
public class GatewayzuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayzuulApplication.class, args);
    }

}
