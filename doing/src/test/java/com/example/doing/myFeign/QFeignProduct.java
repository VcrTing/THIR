package com.example.doing.myFeign;

import org.springframework.web.bind.annotation.RequestMapping;

public interface QFeignProduct {

    String name = "cloud-product";

    @RequestMapping("/v1/products/{id}")
    Object byId(Long id);
}
