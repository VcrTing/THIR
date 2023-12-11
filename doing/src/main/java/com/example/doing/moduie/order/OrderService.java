package com.example.doing.moduie.order;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.doing.mq.sender.MqSenderNotify;
import com.example.doing.mq.sender.MqSenderOrder;
import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    MqSenderOrder senderOrder;

    @Autowired
    MqSenderNotify senderNotify;

    /**
    * 下单
    * @params
    * @return
    */
    @Transactional
    public Order buy(Order order) {

        // 新增订单
        order.setOrder_status((short)0); // 0 = 未付款
        if (!save(order)) throw new RuntimeException("订单新增失败");
        if (order.getId() == null) throw new RuntimeException("订单新增失败");

        // 調用 移除 庫存
        try {
            senderOrder.orderRemoveQuantity(order);
        } catch (Exception ignored) { }

        // 調用 下单 通知
        try {
            order.setNotify("您已经购买了产品，请在30秒内付款，否则订单会取消。");
            senderNotify.notify(order);
        } catch (Exception ignored) { }

        // 調用 30s 未 付款
        try {
            senderOrder.orderCheckExpire(order);
        } catch (Exception ignored) { }

        // 返回
        return order;
    }

    /**
    * 支付
    * @params
    * @return
    */
    @Transactional
    public boolean pay(Order order, BigDecimal payMoney) {

        // 订单 不是 取消的 状态
        if (order.getOrder_status() == null || order.getOrder_status() < (short)0) throw new RuntimeException("订单 已是 无用 状态");
        if (order.getOrder_status() > (short)0) throw new RuntimeException("订单 无需付款，已付款或已完成");

        // 订单 金额 入账
        order.setPrice(payMoney);

        // 更新 订单 入账 金额
        this.lambdaUpdate().set(Order::getPrice, payMoney).eq(Order::getId, order.getId()).update();

        // 調用 移除 用户 金钱
        try {
            senderOrder.orderRemoveMoney(order);
        } catch (Exception ignored) { }

        // 調用 订单状态为 已付款
        try {
            senderOrder.orderPayedStatus(order);
        } catch (Exception ignored) { }

        return true;
    }

    /**
    * 完成支付 / 支付 回调
    * @params
    * @return
    */
    public String payCallBack(Order order) {
        // 队列 通知 用户 订单 已 付款
        String msg = "您已经成功付款了，订单稍后发货，详情请查看订单状态。";
        try {
            order.setNotify(msg);
            senderNotify.notify(order);
        } catch (Exception ignored) { } return msg;
    }
}
