package com.itv.kata;

import com.itv.kata.remote.PricingClient;
import com.itv.kata.remote.ProductLookupClient;
import com.itv.kata.transactions.Receipt;
import com.itv.kata.transactions.TransactionFactory;
import org.junit.Before;
import org.junit.Test;

import static com.itv.kata.TestFixtures.barcode1;
import static com.itv.kata.TestFixtures.barcode2;
import static com.itv.kata.TestFixtures.barcode3;
import static com.itv.kata.TestFixtures.barcode4;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AcceptanceTest {
    private final ProductLookupClient productLookupClient = mock(ProductLookupClient.class);
    private final Checkout checkout = new Checkout(new TransactionFactory(new PricingClient()), productLookupClient);

    @Before
    public void setUp() throws Exception {
        when(productLookupClient.lookup(barcode1)).thenReturn('a');
        when(productLookupClient.lookup(barcode2)).thenReturn('b');
        when(productLookupClient.lookup(barcode3)).thenReturn('c');
        when(productLookupClient.lookup(barcode4)).thenReturn('d');
    }

    @Test
    public void producesCorrectReceipt() {
        // Pre discount total 365
        checkout.scanItem(barcode1);
        checkout.scanItem(barcode1);
        checkout.scanItem(barcode1); // 3 x a, expected discount of 20

        checkout.scanItem(barcode2);
        checkout.scanItem(barcode2);
        checkout.scanItem(barcode2);
        checkout.scanItem(barcode2);
        checkout.scanItem(barcode2);
        checkout.scanItem(barcode2); // 6 x b expected discount of 45

        checkout.scanItem(barcode3);
        checkout.scanItem(barcode4);

        Receipt receipt = checkout.finishTransaction();

        assertThat(receipt.getTotal(), is(300));
        assertThat(receipt.getItems().get('a'), is(3));
        assertThat(receipt.getItems().get('b'), is(6));
        assertThat(receipt.getItems().get('c'), is(1));
        assertThat(receipt.getItems().get('d'), is(1));
        assertThat(receipt.getDiscounts().size(), is(2));
        assertThat(receipt.getDiscounts().get(0).getItem(), is('a'));
        assertThat(receipt.getDiscounts().get(0).getDeduction(), is(20));
        assertThat(receipt.getDiscounts().get(1).getItem(), is('b'));
        assertThat(receipt.getDiscounts().get(1).getDeduction(), is(45));
    }
}