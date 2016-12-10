package com.itv.kata.pricing;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultiBuyDiscountTest {
    private final MultiBuyDiscount discount = new MultiBuyDiscount(2, 150);

    @Test
    public void handlesZeroDiscountApplications() {
        assertThat(discount.apply(1, 80), is(0));
    }

    @Test
    public void handlesOneDiscountApplication() {
        assertThat(discount.apply(2, 80), is(10));
    }

    @Test
    public void handlesLeftOverItems() {
        assertThat(discount.apply(3, 80), is(10));
    }

    @Test
    public void handlesMultipleDiscountApplications() {
        assertThat(discount.apply(4, 80), is(20));
    }

    @Test
    public void handlesZeroQuantity() {
        assertThat(discount.apply(0, 80), is(0));
    }

    @Test
    public void handlesNegativeQuantity() {
        assertThat(discount.apply(-2, 80), is(0));
    }

}