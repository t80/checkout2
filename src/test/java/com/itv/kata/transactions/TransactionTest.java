package com.itv.kata.transactions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentCaptor.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TransactionTest {

    private TransactionCalculator calculator;
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        calculator = mock(TransactionCalculator.class);
        transaction = new Transaction(calculator);
    }

    @Test
    public void addScannedItemsToCalculator() {
        transaction.addItem('a');

        verify(calculator, times(1)).add('a');
    }

    @Test
    public void aggregatesDuplicateItemsInTransaction() {
        transaction.addItem('a');
        transaction.addItem('a');
        transaction.addItem('a');

        ArgumentCaptor<Map<Character, Integer>> captor = forClass(Map.class);

        transaction.close();

        verify(calculator).applyDiscounts(captor.capture());

        assertThat(captor.getValue().get('a'), is(3));
    }


    @Test
    public void appliesDiscountsOnCloseOfTransaction() {
        transaction.close();

        verify(calculator, times(1)).applyDiscounts(anyMap());
    }
}