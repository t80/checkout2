package com.itv.kata.pricing;

import java.util.Optional;

public class Price {
    private final int price;
    private final SpecialOffer specialOffer;

    public Price(int price, SpecialOffer specialOffer) {
        this.price = price;
        this.specialOffer = specialOffer;
    }

    public int getPrice() {
        return price;
    }

    public Optional<SpecialOffer> getSpecialOffer() {
        return Optional.ofNullable(specialOffer);
    }
}
