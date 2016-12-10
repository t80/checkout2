package com.itv.kata.pricing;

import java.text.DecimalFormat;

import static java.lang.Math.floorDiv;

public class MultiBuyDiscount implements SpecialOffer {
    private final int threshold;
    private final int discountPrice;

    public MultiBuyDiscount(int threshold, int discountPrice) {
        this.threshold = threshold;
        this.discountPrice = discountPrice;
    }

    public int apply(int quantity, int price) {
        if(quantity < threshold)
            return 0;

        int discountApplications = floorDiv(quantity, threshold);
        int originalCostOfDiscountedItems = threshold * discountApplications * price;

        return originalCostOfDiscountedItems - (discountApplications * discountPrice);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return threshold + " for £" + df.format(discountPrice / 100d);
    }
}
