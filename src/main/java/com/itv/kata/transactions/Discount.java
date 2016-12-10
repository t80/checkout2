package com.itv.kata.transactions;

import java.text.DecimalFormat;

public final class Discount {
    private final Character item;
    private final int deduction;
    private final String offer;

    public Discount(Character item, int deduction, String offer) {
        this.item = item;
        this.deduction = deduction;
        this.offer = offer;
    }

    public Character getItem() {
        return item;
    }

    public int getDeduction() {
        return deduction;
    }

    public String getOffer() {
        return offer;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return offer + " applied to " + item + " -£" + (df.format(deduction / 100d));
    }
}
