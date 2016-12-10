package com.itv.kata.transactions;

import com.itv.kata.remote.PricingClient;
import org.junit.Test;
import org.mockito.exceptions.verification.junit.ArgumentsAreDifferent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TransactionFactoryTest {
    private final PricingClient pricingClient = mock(PricingClient.class);
    private final TransactionFactory transactionFactory = new TransactionFactory(pricingClient);

    @Test
    public void buildsNewTransactionWithUpdatedPrices() {
        Transaction transaction1 = transactionFactory.newTransaction();
        Transaction transaction2 = transactionFactory.newTransaction();

        assertThat(transaction1, is(not(equalTo(transaction2))));

        verify(pricingClient, times(2)).loadPrices();
    }
}