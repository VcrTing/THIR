package com.example.stepone;

import com.example.stepone.anno.form.AnnoUserForm;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ReflectTest {

    @Test
    public void fanshe() {
        AnnoUserForm form = new AnnoUserForm("AA", true, "88889999");
        /**
         * 反射
         * @params
         * @return
         */
        // 因为只是当前线程

        // 元数据
        Class<?> c1 = form.getClass();
        // 字节码 的 元数据
        Class<?> c2 = AnnoUserForm.class;

        // Objects.equals(c1, c2) 一定为 true
        try {
            Class<?> c3 = Class.forName(c1.getName());
            // Objects.equals(c1, c3) 一定为 true

            // int 与 Integer
            System.out.println(int.class);
            System.out.println(Integer.class);
            System.out.println(Objects.equals(int.class, Integer.TYPE));
            System.out.println("----------");
            // 类型 比较
            int a = 1;
            Integer b = 1;
            Integer c = new Integer(1);
            // System.out.println(a == b); true
            // System.out.println(b == c); false
            // System.out.println(b.equals(c)); true
            // 报错 System.out.println((a instanceof Integer));

            // 新型 new
            AnnoUserForm form2 = (AnnoUserForm) c1.newInstance();

        } catch (Exception i) { }

    }

}
