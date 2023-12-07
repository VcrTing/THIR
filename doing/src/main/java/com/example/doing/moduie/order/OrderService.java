package com.example.doing.moduie.order;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.doing.moduie.product.ProductService;
import com.example.doing.moduie.sys.UserService;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    public Object buy(Order order, Product product, User user, Short quantity) {

        // 是否 足够库存
        if (product.getQuantity() == null || product.getQuantity() < quantity) return "产品库存不足";

        // 新增订单
        order.setOrder_status((short)0); // 0 = 生成中
        if (!save(order)) return "订单新增失败";
        if (order.getId() == null) return "订单新增失败";

        // 用户 库存 是否 足够
        BigDecimal yue = user.getMoney();
        if (yue == null) return "用户账户余额不足";
        yue = yue.subtract(order.getPrice()); // 减去 付款 金额
        if (yue.compareTo(new BigDecimal(0)) < 0) return "用户账户余额不足";

        // 扣除 产品 库存
        product.setQuantity( product.getQuantity() - quantity);
        productService.updateById(product);

        // 修改 订单状态为 已付款
        order.setOrder_status((short)1); // 1 = 已付款
        updateById(order);

        // 扣除 用户的 账户余额
        user.setMoney(yue);
        userService.updateById(user);

        // 返回
        return getById(order.getId());
    }

    // 通知用户
    public void callUserOrderStatus(Long userId, Long orderId) {
        User user = userService.getById(userId);
        Order order = getById(orderId);

        if (order != null && user != null) {
            String res = "下单通知：用户" + user.getEmail();
            Short status = order.getOrder_status();
            if (status == null) res += "无此订单"; else {
                if (status == 0) res += "下单失败，订单未付款";
                else if (status == 1) res += "下单成功，订单已付款";
                else if (status == 2) res += "下单成功，订单已收货";
            }
            System.out.println(res + " 订单号 = " + order.getId());
        }
        System.out.println("通知失败，订单或用户为空。");
    }
}
