package com.example.doing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyTest {

    @Test
    void one() {
        BigDecimal one = new BigDecimal(4);
        BigDecimal two = one.multiply(new BigDecimal("37.5"));
        BigDecimal three = two.multiply(new BigDecimal("0.95"));
        System.out.println(two);
        System.out.println(three);
    }
    @Test
    void two() {
        BigDecimal one = new BigDecimal(4);
        BigDecimal two = one.multiply(new BigDecimal("37.5"));
        BigDecimal three = two.multiply(new BigDecimal("0.95"));
        System.out.println(two);
        System.out.println(three);
    }
}
