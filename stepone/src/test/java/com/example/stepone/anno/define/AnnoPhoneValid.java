package com.example.stepone.anno.define;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoPhoneValid {

    // 定義 什麼名字 的 field 需要 經過 Phone 驗證
    String fieldName() default "phone";
}
