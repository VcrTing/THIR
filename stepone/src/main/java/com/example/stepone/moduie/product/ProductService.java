package com.example.stepone.moduie.product;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.stepone.moduie.sys.UserService;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {

    /**
     * 扣除 庫存
     * @params
     * @return
     */
    public void removeQuantity(Long productId, Short quantity) {
        Product product = this.getById( productId );

        // 是否 足够库存
        if (product.getQuantity() == null || product.getQuantity() < quantity) throw new MessageConversionException("产品库存不足");

        // 扣除 产品 库存
        product.setQuantity( product.getQuantity() - quantity);
        if (!this.updateById(product)) throw new MessageConversionException("產品庫存扣除失敗");
    }

}
