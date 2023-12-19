package com.example.stepone.moduie.product;

import com.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    ProductServiceRedis serviceRedis;
    /**
    * 新增
    * @params
    * @return
    */
    @PostMapping
    public Product pos(@RequestBody Product product) {
        return serviceRedis.pos(product);
    }
}
