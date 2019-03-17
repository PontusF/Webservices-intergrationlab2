package com.example.order;

public class Store {
    private long Id;
    private String name;
    private String adress;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Store() {
        this.Id = -1;
        this.name= "Not initialized";
        this.adress= "Not initialized";
    }
}
