package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ord_order")
public class Order implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "order_status")
    private Short order_status;
    @TableField(value = "buy_date")
    private Date buy_date;

    private BigDecimal price;
    @TableField(value = "buy_quantity")
    private Short buy_quantity;

    @TableField(value = "user_sql_id")
    private Long user_sql_id;
    @TableField(value = "product_sql_id")
    private Long  product_sql_id;

    @TableField(exist = false)
    private String notify;
}
