package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ord_order")
public class Order {
    private Long id;

    private Short order_status;
    private Date buy_date;

    private BigDecimal price;

    private Long user_sql_id;
    private Long  product_sql_id;
}
