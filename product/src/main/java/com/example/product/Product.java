package com.example.product;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@Entity
@Data
public class Product {
    private @Id @GeneratedValue long id;
    private final String name;

    public Product(){
        this.name = "Not initialized";
    }

    public Product(String name) {
        this.name = name;
    }
}
