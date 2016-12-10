package com.itv.kata;

import com.itv.kata.pricing.MultiBuyDiscount;
import com.itv.kata.pricing.Price;
import com.itv.kata.remote.Barcode;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class TestFixtures {

    public final static Barcode barcode1 = mock(Barcode.class);
    public final static Barcode barcode2 = mock(Barcode.class);
    public final static Barcode barcode3 = mock(Barcode.class);
    public final static Barcode barcode4 = mock(Barcode.class);

    @SuppressWarnings("Duplicates")
    public static Map<Character, Price> prices() {
        Map<Character, Price> prices = new HashMap<>();
        prices.put('a', new Price(50, new MultiBuyDiscount(3, 130)));
        prices.put('b', new Price(30, new MultiBuyDiscount(2, 45)));
        prices.put('c', new Price(20, null));
        prices.put('d', new Price(15, null));

        return prices;
    }
}
