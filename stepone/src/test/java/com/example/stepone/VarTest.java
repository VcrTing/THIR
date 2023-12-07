package com.example.stepone;

import com.google.common.math.Quantiles;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VarTest {

    @Test
    public void numTest() {
        BigDecimal resPrice = new BigDecimal("90");
        BigDecimal discountPrice = new BigDecimal("10");

        BigDecimal originPrice = resPrice.add(discountPrice);
        // System.out.println(100 / 150);

        BigDecimal rate = resPrice.divide(originPrice, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(rate);
    }
}
