package com.example.demo;

import com.netflix.discovery.EurekaClient;

import org.springframework.web.bind.annotation.*;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WareHouseController {

    private WareHouseRepository repository;

public WareHouseController(WareHouseRepository repository){
    this.repository=repository;
    repository.save(new Warehouse("Kalles Grossist","Fabriksgatan 24"));
    repository.save(new Warehouse("Outpost 32","Åkervägen 32"));
    }

    @PostMapping("/warehouse")
    public Warehouse addWarehouse (@RequestBody Warehouse warehouse){
        return repository.save(warehouse);
    }



    private EurekaClient eurekaClient;

    @GetMapping("/warehouse")
    public List<Warehouse> findAll(){

        System.out.println("anrop!");

        return repository.findAll();
    }

    @GetMapping("/warehouseid")
    public List<Long> findAllId(){
        System.out.println("Anrop all id");
        List<Long> warehousesList=new ArrayList<>();
        for (int i=0;i<repository.findAll().size();i++){
        warehousesList.add(repository.findAll().get(i).getId());
        }
        return warehousesList;
    }


    @GetMapping("/warehouse/{id}") // anropas genom exempelvis http://localhost:8585/warehouse/2
    public Warehouse FindOne(@PathVariable Long id){
        return repository.findById(id).get();
    }


}
