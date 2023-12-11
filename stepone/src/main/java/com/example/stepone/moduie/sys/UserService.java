package com.example.stepone.moduie.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Order;
import com.example.entity.User;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    /**
     * 扣除 用戶 購買的 餘額
     * @params
     * @return
     */
    @Transactional
    public void removeMoney(Long userId, BigDecimal price) {
        User user = this.getById( userId );

        // 用户 金額 足夠
        BigDecimal yue = user.getMoney();
        if (yue == null) throw new MessageConversionException("用户账户没有余额");

        yue = yue.subtract( price ); // 减去 付款 金额
        if (yue.compareTo(new BigDecimal(0)) < 0) throw new MessageConversionException("用户账户余额不足");

        // 扣除 用户的 账户余额
        user.setMoney(yue);
        if (!this.updateById(user)) throw new MessageConversionException("用戶餘額扣除失敗");
    }

    /**
    * 当前 用户 接受 消息
    * @params
    * @return
    */
    public void sendEmailMsg(Long userId, String msg) {
        User user = this.getById(userId);
        System.out.println("向用户 " + user.getEmail() + " 发送 邮件 通知：{}" + msg);
    }

    /**
     * 訂單完成的 通知
     * @params
     * @return
    public void callUserOrderStatus(Long userId, Short orderStatus, Long orderId) {
        String res = "通知失败，订单或用户为空。";

        if (userId != null) {
            User user = this.getById(userId);

            if (user != null) {
                res = "下单通知：用户" + user.getEmail() + ", ";
                Short status = orderStatus;

                if (status == null) res += "无此订单"; else {
                    if (status == 0) res += "下单失败，订单未付款";
                    else if (status == 1) res += "下单成功，订单已付款";
                    else if (status == 2) res += "下单成功，订单已收货";
                }
            }
        }
        System.out.println( res + " 订单号 = " + orderId );
        System.out.println("沒法拿到最新的 訂單狀態");
    }*/
}
