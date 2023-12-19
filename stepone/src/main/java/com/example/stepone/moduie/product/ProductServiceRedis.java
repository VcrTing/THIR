package com.example.stepone.moduie.product;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Product;
import com.example.stepone.tools.QRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceRedis extends ServiceImpl<ProductMapper, Product> {

    @Autowired
    QRedisTool redisTool;

    String redisK = "product:cache:";

    // 冷门商品 存活 时间
    final static int PRODUCT_LIVE_SECOND = 60 * 60; // 一小时
    // 虚假商品 存活 时间
    final static int DIE_PRODUCT_LIVE_SECOND = 60 * 10; // 十分钟

    // 随机 缓存 时间
    // 用于 缓冲 缓存 击穿 至 数据库，同一时间 大量产品 同时失去 存活时间
    protected int randomLiveTime() { int a = (int) (Math.random() * 3_000); return PRODUCT_LIVE_SECOND + a; }

    // 空 产品
    final static String EMPTY = "{}";

    // 普通新增
    public Product pos(Product product) {
        this.save(product);
        // 新增就 存入 redis
        if (product.getId() != null) { redisTool.set(redisK + product.getId(), product, randomLiveTime()); }
        return product;
    }

    // 普通修改
    public Product upd(Product product) {
        this.updateById(product);
        // 新增就 存入 redis
        if (product.getId() != null) { redisTool.set(redisK + product.getId(), product, randomLiveTime()); }
        return product;
    }

    // 获取一个
    public Product one(Long id) {
        String src = redisTool.get(redisK + id);

        // 过滤
        if (!StringUtils.hasText(src) || src.equals(EMPTY)) return null;
        Product product = JSONUtil.toBean(src, Product.class);

        if (product == null || product.getId() == null) {
            product = this.getById(id);
            redisTool.set(redisK + product.getId(), product, randomLiveTime());
        } else {
            // 数据库里没有，给他存一个 空的数据
            redisTool.set(redisK + product.getId(), EMPTY, DIE_PRODUCT_LIVE_SECOND);
        }

        return product;
    }
}
