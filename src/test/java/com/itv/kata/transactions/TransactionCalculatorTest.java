package com.itv.kata.transactions;

import com.itv.kata.TestFixtures;
import com.itv.kata.pricing.Price;
import com.itv.kata.pricing.SpecialOffer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionCalculatorTest {
    private TransactionCalculator transactionCalculator;
    private Map<Character, Price> prices;

    @Before
    public void setUp() throws Exception {
        prices = TestFixtures.prices();
        transactionCalculator = new TransactionCalculator(prices);
    }

    @Test
    public void keepsRunningPreDiscountTotal() {
        transactionCalculator.add('a');
        transactionCalculator.add('b');

        assertThat(transactionCalculator.getTotal(), is(80));
    }

    @Test
    public void appliesDiscounts() {
        char itemCodeA = 'a';
        Price price = mock(Price.class);
        when(price.getPrice()).thenReturn(50);

        SpecialOffer specialOffer = mock(SpecialOffer.class);
        when(price.getSpecialOffer()).thenReturn(Optional.of(specialOffer));
        when(specialOffer.apply(4, 50)).thenReturn(20);

        Map<Character, Price> mockPrices = mock(Map.class);
        when(mockPrices.get(itemCodeA)).thenReturn(price);

        transactionCalculator.add(itemCodeA);
        transactionCalculator.add(itemCodeA);
        transactionCalculator.add(itemCodeA);
        transactionCalculator.add(itemCodeA);

        List<Discount> discounts = transactionCalculator.applyDiscounts(singletonMap(itemCodeA, 4));

        assertThat(transactionCalculator.getTotal(), is(180));
        assertThat(discounts.size(), is(1));
        assertThat(discounts.get(0).getDeduction(), is(20));
        assertThat(discounts.get(0).getItem(), is('a'));
    }
}