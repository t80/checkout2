package com.itv.kata.transactions;

import com.itv.kata.remote.PricingClient;

public class TransactionFactory {
    private final PricingClient pricingClient;

    public TransactionFactory(PricingClient pricingClient) {
        this.pricingClient = pricingClient;
    }

    public Transaction newTransaction() {
        return new Transaction(new TransactionCalculator(pricingClient.loadPrices()));
    }
}
