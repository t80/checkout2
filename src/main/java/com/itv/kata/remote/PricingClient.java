package com.itv.kata.remote;

import com.itv.kata.pricing.MultiBuyDiscount;
import com.itv.kata.pricing.Price;

import java.util.HashMap;
import java.util.Map;

public class PricingClient {

    // Mocked data as per the spec
    public Map<Character, Price> loadPrices() {
        Map<Character, Price> prices = new HashMap<>();
        prices.put('a', new Price(50, new MultiBuyDiscount(3, 130)));
        prices.put('b', new Price(30, new MultiBuyDiscount(2, 45)));
        prices.put('c', new Price(20, null));
        prices.put('d', new Price(15, null));

        return prices;
    }
}
