package com.itv.kata;

import com.itv.kata.remote.ProductLookupClient;
import com.itv.kata.transactions.Transaction;
import com.itv.kata.transactions.TransactionFactory;
import org.junit.Test;

import static com.itv.kata.TestFixtures.barcode1;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class CheckoutTest {
    private TransactionFactory transactionFactory = mock(TransactionFactory.class);
    private ProductLookupClient productLookupClient = mock(ProductLookupClient.class);
    private Checkout checkout = new Checkout(transactionFactory, productLookupClient);

    @Test
    public void createNewTransactionOnlyOnFirstScan() {
        when(transactionFactory.newTransaction()).thenReturn(mock(Transaction.class));

        checkout.scanItem(barcode1);
        verify(transactionFactory, times(1)).newTransaction();

        checkout.scanItem(barcode1);
        verifyZeroInteractions(transactionFactory);
    }

    @Test
    public void addsItemToTransaction() {
        when(transactionFactory.newTransaction()).thenReturn(mock(Transaction.class));
        when(productLookupClient.lookup(barcode1)).thenReturn('a');

        checkout.scanItem(barcode1);
    }
}
