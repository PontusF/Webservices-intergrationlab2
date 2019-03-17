package com.example.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository= productRepository;
        productRepository.save(new Product("Samsung E9"));
        productRepository.save(new Product("Arla Mellanmjölk 1l"));
        productRepository.save(new Product("Swedish Match Grovsnus Portion"));
        productRepository.save(new Product("Findus mustig Laxsoppa"));
    }

    @GetMapping("/product")
    public List<Product> products(){return  productRepository.findAll();}

    @PostMapping("/product/new")
    public Product postProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @GetMapping("/product/{id}")
    public Product getOne(@PathVariable Long id){return productRepository.findById(id).get();}
}
