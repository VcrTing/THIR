package com.example.doing.moduie.product;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.doing.moduie.order.OrderMapper;
import com.example.entity.Order;
import com.example.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {
}
