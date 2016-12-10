package com.itv.kata.transactions;

import com.itv.kata.pricing.Price;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receipt {
    private final Map<Character, Integer> items;
    private final Map<Character, Price> prices;
    private final List<Discount> discounts;
    private final int total;

    Receipt(Map<Character, Integer> items, Map<Character, Price> prices, List<Discount> discounts, int total) {
        this.items = new HashMap<>(items);
        this.prices = new HashMap<>(prices);
        this.discounts = new ArrayList<>(discounts);
        this.total = total;
    }

    public Map<Character, Integer> getItems() {
        return new HashMap<>(items);
    }

    public List<Discount> getDiscounts() {
        return new ArrayList<>(discounts);
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");
        for(Map.Entry<Character, Integer> e: items.entrySet()) {
            sb.append(e.getKey()).append(" x ").append(e.getValue());
            sb.append("  @ £").append(df.format((e.getValue() * prices.get(e.getKey()).getPrice()) / 100d));
            sb.append("\n");
        }

        sb.append("\n").append("Additional info").append("\n");
        for (Discount d: discounts) {
            sb.append(d).append("\n");
        }

        sb.append("\n").append("Total: £").append(df.format(total / 100d));

        return sb.toString();
    }
}
