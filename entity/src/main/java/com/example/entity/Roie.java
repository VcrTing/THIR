package com.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("roie")
public class Roie {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;

    // 一对多 专用字段
    @TableField(exist = false)
    private List<User> users;
}
