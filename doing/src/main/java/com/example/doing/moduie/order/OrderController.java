package com.example.doing.moduie.order;

import com.example.doing.moduie.product.ProductService;
import com.example.doing.moduie.sys.UserService;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.utils.basic.QTypedUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping
    public Object buy(@RequestBody HashMap<String, Object> prm) throws NoSuchMethodException {
        Long proId = QTypedUtil.serLong(prm.get("product"));
        Long userId = QTypedUtil.serLong(prm.get("user"));
        Short quantity = QTypedUtil.serShort(prm.get("quantity"));
        BigDecimal price = new BigDecimal("100.12");

        System.out.println(prm.get("product") + " " + prm.get("user") + " " + quantity + " " + price);
        // 验证
        if (proId == null || userId == null || quantity == null) return "下单数据不完整";

        // 组装
        Order o = new Order();
        o.setPrice(price); o.setBuy_date(new Date()); o.setOrder_status((short)0);
        o.setUser_sql_id(userId); o.setProduct_sql_id(proId);

        // 下单
        Object res = service.buy(o, productService.getById(proId), userService.getById(userId), quantity);
        if (res instanceof String) return res; else {
            Order resOrder = (Order) res;
            // 通知 用户 下单成功
            service.callUserOrderStatus(userId, resOrder.getId());
        }
        // 结束
        return "下单成功，更多详情请看通知";
    }
}
