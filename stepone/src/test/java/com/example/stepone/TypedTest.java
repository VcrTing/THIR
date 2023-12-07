package com.example.stepone;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TypedTest {

    String gbName = "里斯";

    public String inString() {
        String gbName = new String("張三");
        System.out.println(gbName);
        return gbName;
    }

    boolean eq(Integer i1, Integer i2) {
        return i1 == i2;
    }

    @Test
    public void stringTest() {
        String a = "H";
        String b = new String("H");
        byte[] bbb = b.getBytes();

        System.out.println(a.substring(1));

        System.out.println("------------------");
        String gbName2 = gbName;
        System.out.println(gbName2);
        gbName2 = inString();
        System.out.println(gbName2);

    }

    @Test
    public void intTest() {
        byte b = 'A';
        char c = 'A';

        System.out.println(b == c);
        System.out.println(Objects.equals(b, c));
        Character a = 'A';
        System.out.println(a == b);
        System.out.println( (a == b) || (a != null && a.equals(b)));

        System.out.println("------------------");
        Object bo = (Object) b;
        Object co = (Object) c;
        if (bo instanceof Byte) System.out.println("bo 是 Byte 包裝類");
        if (co instanceof Character) System.out.println("co 是 Character 包裝類");
        System.out.println(bo == co); // 比較 包裝 類 地址值
        System.out.println(bo.equals(co));


        System.out.println("------------------");
        String s1 = "A";
        String s2 = "A";
        String s3 = new String("A");
        System.out.println(s1 == s2);
        // 不會 自動 拆箱
        System.out.println(s1 == s3);

        System.out.println("------------------");
        int q1 = 1;
        Integer q2 = null;
        System.out.println(eq(q1, q2));

        // Short si = 1;
        Integer i = 1;
        Object i2 = (Object) i;
        System.out.println(i);
        System.out.println(i2);
        System.out.println(i == i2);
        System.out.println(Objects.equals(i, i2));
        int i3 = 1;
        System.out.println(i3);
        System.out.println(i == i3);
        System.out.println(Objects.equals(i2, i3));

        Integer i4 = new Integer(1);
        System.out.println(i4);
        // int 和 Integer 比較，Integer 會被 拆箱
        System.out.println(i3 == i4);

        System.out.println("------------------");
        System.out.println(i == i4);
        System.out.println(i2 == i4);

        System.out.println( (i2 == i4) || (i4 != null && i4.equals(i2)));
    }
}
