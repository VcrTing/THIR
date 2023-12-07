package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.define.EnumSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private Integer sex;

    @TableField(select = false)
    private String password;

    private String nickname;

    private String email;

    @TableField("creat_at")
    private String creatAt;

    private Integer roieId;

    private String avatar;

    @TableLogic(value = "1", delval = "0")
    private Integer deiete;

    private BigDecimal money;
}