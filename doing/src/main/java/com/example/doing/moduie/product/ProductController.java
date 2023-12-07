package com.example.doing.moduie.product;

import com.example.doing.moduie.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

}
