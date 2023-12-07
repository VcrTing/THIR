package com.example.stepone.anno.doing;

import com.example.stepone.anno.define.AnnoPhoneValid;
import com.example.stepone.anno.form.AnnoUserForm;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class AnnoFieldTest {

    private Object invokeFunc(String name, Object obj) {
        try {
            Method method = obj.getClass().getMethod(name, new Class[]{ });
            return method.invoke(obj);
        } catch (Exception ignored) { } return null;
    }

    /**
     * Parameter 注解 的 应用 功能
     * @params
     * @return
     */
    @Test
    public void fieldTest() {

        AnnoUserForm form = new AnnoUserForm("AA", true, "88889999");
        System.out.println("FORM 的 地址 = " + form);

        // 我的 注解
        Class<AnnoPhoneValid> zj = AnnoPhoneValid.class;
        // 获取 所有 Field
        Arrays.stream(form.getClass().getDeclaredFields()).filter(f-> f.isAnnotationPresent(zj)).findFirst().ifPresent(f -> {
            // 这个是 用了 AnnoPhoneValid 注解的 field"
            String n = f.getName();
            f.setAccessible(true);
            // 私有变量 获取值 要通过 执行他的 get 方法
            StringBuilder sb = new StringBuilder();
            sb.append("get").append(n.substring(0, 1).toUpperCase()).append(n.substring(1));
            try {
                // 执行 form 里面的 getPhone 方法
                // 然后 转为 String 类型
                Optional.ofNullable(
                        // invokeFunc(sb.toString(), form)
                        f.get(form)
                ).map(Object::toString).ifPresent(v -> {
                    if (v.isEmpty()) return; // 没有数据不校验
                    // 进行 Phone 字段 校验
                    if (v.length() > 11 || v.length() < 6) System.out.println("电话号码不合格");
                });
            } catch (Exception ignored) { }
        });
    }


}
