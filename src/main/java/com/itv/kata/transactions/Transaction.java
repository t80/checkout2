package com.itv.kata.transactions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
    private final TransactionCalculator calculator;
    private Map<Character, Integer> items = new HashMap<>();

    Transaction(TransactionCalculator calculator) {
        this.calculator = calculator;
    }

    public void addItem(Character itemCode) {
        calculator.add(itemCode);
        items.merge(itemCode, 1, Integer::sum);
    }

    public Receipt close() {
        List<Discount> discounts = calculator.applyDiscounts(items);
        return new Receipt(items, calculator.getPrices(), discounts, calculator.getTotal());
    }
}