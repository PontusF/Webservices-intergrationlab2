package com.example.order;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    private OrderRepository orderRepository;
    RestTemplate restTemplate;

    public OrderController(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
    }

    //Get list of all orders
    @RequestMapping("/order/all")
    public List<Order> order() {
        return orderRepository.findAll();
    }


    @PostMapping("/order/new")
    public ResponseEntity<?> addOrder(@RequestBody Order order, @RequestHeader(name = "Authorization") String token) {

        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", token);
        HttpEntity httpEntity = new HttpEntity<>(headers);


       // ResponseEntity<Store>  storeresponse = restTemplate.exchange(
       //        "http://store/store/"+order.getStoreId(), HttpMethod.GET, httpEntity, Store.class);
        System.out.println("TestStorePassed");

        ResponseEntity<Product> productresponse = restTemplate.exchange(
                "http://product/product/"+order.getProductId(), HttpMethod.GET, httpEntity, Product.class);
        System.out.println("TestProductPassed");

        ResponseEntity<Warehouse> warehouseresponse = restTemplate.exchange(
                "http://joelapp/warehouse/"+order.getWarehouseId(), HttpMethod.GET, httpEntity, Warehouse.class);
        System.out.println("TestJoelApp");

        orderRepository.save(order);
        System.out.println("WORKED");
        return new ResponseEntity<>("order added", HttpStatus.OK);

    }

    @RequestMapping("/order/{id}")
    public  Order getOne(@PathVariable Long id){return orderRepository.findById(id).get();}

    @RequestMapping("/order/{id}/details")
    public List getdetails(@PathVariable Long id,@RequestHeader(name = "Authorization") String token){
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", token);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        Order order= orderRepository.findById(id).get();

        //ResponseEntity<Store>  storeresponse = restTemplate.exchange(
        //        "http://store/store/"+order.getStoreId(), HttpMethod.GET, httpEntity, Store.class);

        ResponseEntity<Warehouse>  warehouseresponse = restTemplate.exchange(
                "http://joelapp/warehouse/"+order.getWarehouseId(), HttpMethod.GET, httpEntity, Warehouse.class);

        ResponseEntity<Product>  productresponse = restTemplate.exchange(
                "http://product/product/"+order.getProductId(), HttpMethod.GET, httpEntity, Product.class);


        List<Object> output= new ArrayList<>();
        output.add(productresponse.getBody());
        output.add(warehouseresponse.getBody());
        //output.add(storeresponse.getBody());
        return output;
    }

}
