package com.itv.kata.pricing;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class PriceTest {

    @Test
    public void returnsEmptyOptionalIfNoSpecialOffer() {
        Price price = new Price(10, null);
        assertThat(price.getSpecialOffer().isPresent(), is(false));
    }

    @Test
    public void returnsSpecialOfferWhenPresent() {
        SpecialOffer specialOffer = mock(SpecialOffer.class);

        Price price = new Price(10, specialOffer);

        assertThat(price.getSpecialOffer().get(), is(specialOffer));
    }
}