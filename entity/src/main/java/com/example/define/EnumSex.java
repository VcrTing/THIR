package com.example.define;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EnumSex {
    GIRL(0, "女生"),
    BOY(1, "男生"),
    SECURITY(2, "保密");

    private Integer code;
    private String name;

    public static EnumSex codeToMe(Integer c) {
        return Arrays.stream(values()).filter(one -> Objects.equals(one.code, c)).findFirst().orElse(EnumSex.SECURITY);
    }
    public static String codeToString(Integer c) {
        return Arrays.stream(values()).filter(one -> Objects.equals(one.code, c)).findFirst().map(EnumSex::getName).orElse(EnumSex.SECURITY.getName());
    }
}
