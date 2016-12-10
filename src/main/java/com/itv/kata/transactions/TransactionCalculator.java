package com.itv.kata.transactions;

import com.itv.kata.pricing.Price;
import com.itv.kata.pricing.SpecialOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class TransactionCalculator {
    private int total = 0;
    private final Map<Character, Price> prices;

    TransactionCalculator(Map<Character, Price> prices) {
        this.prices = prices;
    }

    void add(Character itemCode) {
        total += prices.get(itemCode).getPrice();
    }

    List<Discount> applyDiscounts(Map<Character, Integer> basket) {
        List<Discount> discounts = new ArrayList<>();
        basket.forEach((item, quantity) -> {
            Optional<SpecialOffer> offer = prices.get(item).getSpecialOffer();
            if(offer.isPresent()) {
                int deduction = offer.get().apply(quantity, prices.get(item).getPrice());
                if(deduction > 0) {
                    discounts.add(new Discount(item, deduction, offer.get().toString()));
                    total -= deduction;
                }
            }
        });

        return discounts;
    }

    int getTotal() {
        return total;
    }

    Map<Character, Price> getPrices() {
        return prices;
    }
}
