package com.example.demo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Warehouse {

    private @Id @GeneratedValue Long id;
    private String name;
    private  String  adress;

    public Warehouse(){}

    public Warehouse(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }
}
