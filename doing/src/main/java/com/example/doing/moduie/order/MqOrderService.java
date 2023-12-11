package com.example.doing.moduie.order;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.doing.mq.sender.MqSenderNotify;
import com.example.doing.mq.sender.MqSenderOrder;
import com.example.entity.Order;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqOrderService extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    MqSenderNotify senderNotify;

    /**
     * 更改訂單 為 已付款
     * @params
     * @return
     */
    public void statusPayed(Order order) {
        order.setOrder_status((short)1); // 1 = 已付款
        updateById(order);
    }

    /**
    * 更改订单 为 失效 订单
    * @params
    * @return
    */
    public void payedExpire(Long oid) {

        Order order = this.getById(oid);

        // 还是 未付款的 状态
        if (order.getOrder_status() == (short)0) {
            System.out.println("订单还是 未付款 的 状态，ID = " + oid);

            // 更改 状态 为 取消订单
            this.lambdaUpdate().set(Order::getOrder_status, (short)-1).eq(Order::getId, oid).update();

            // 通知 订单 已经 取消
            try {
                order.setNotify("您订单30秒内未付款，订单已取消。");
                senderNotify.notify(order);
            } catch (Exception ignored) { }
        }
    }

}
