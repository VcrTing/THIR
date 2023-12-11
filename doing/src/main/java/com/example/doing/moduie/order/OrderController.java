package com.example.doing.moduie.order;

import com.example.entity.Order;
import com.example.utils.basic.QTypedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    OrderMapper orderMapper;

    /**
    * 下单
    * @params
    * @return
    */
    @PostMapping
    public Object buy(@RequestBody HashMap<String, Object> prm) {
        Long proId = QTypedUtil.serLong(prm.get("product"));
        Long userId = QTypedUtil.serLong(prm.get("user"));
        Short quantity = QTypedUtil.serShort(prm.get("quantity"));

        // 验证
        if (proId == null || userId == null || quantity == null) return "下单数据不完整";

        // 组装
        Order o = new Order();
        o.setPrice(new BigDecimal(0));
        o.setBuy_date(new Date());
        o.setOrder_status((short)0);
        o.setUser_sql_id(userId);
        o.setProduct_sql_id(proId);
        o.setBuy_quantity(quantity);

        // 下单
        return service.buy(o);
    }

    /**
    * 支付，默认支付 100.12
    * @params
    * @return
    */
    @PostMapping("/pay")
    public String pay(@RequestBody HashMap<String, Object> prm) {
        Long orderId = QTypedUtil.serLong(prm.get("order"));
        BigDecimal price = new BigDecimal("100.12");

        Order order = orderMapper.selectById(orderId);
        System.out.println("支付的 订单 = " + order);

        // 付款 订单
        if (service.pay(order, price) ) {

            // 付款 后 回调
            return service.payCallBack(order);
        }
        return "订单付款失败。";
    }

    @GetMapping
    public List<Order> all() {
        return service.list();
    }
}
