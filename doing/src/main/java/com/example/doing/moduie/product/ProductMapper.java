package com.example.doing.moduie.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Order;
import com.example.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
