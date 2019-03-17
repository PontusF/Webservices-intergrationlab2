package com.example.demo;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@Author: Pontus Fredriksson
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {


    public void doRequest() {

    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class, args);
    }

}
