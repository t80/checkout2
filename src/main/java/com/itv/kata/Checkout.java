package com.itv.kata;

import com.itv.kata.remote.Barcode;
import com.itv.kata.remote.ProductLookupClient;
import com.itv.kata.transactions.Receipt;
import com.itv.kata.transactions.Transaction;
import com.itv.kata.transactions.TransactionFactory;

final class Checkout {
    private Transaction currentTransaction;
    private final TransactionFactory transactionFactory;
    private final ProductLookupClient productLookupClient;

    Checkout(TransactionFactory transactionFactory, ProductLookupClient productLookupClient) {
        this.transactionFactory = transactionFactory;
        this.productLookupClient = productLookupClient;
    }

    void scanItem(Barcode barcode) {
        if(null==currentTransaction) {
            currentTransaction = transactionFactory.newTransaction();
        }
        char itemCode = productLookupClient.lookup(barcode);
        currentTransaction.addItem(itemCode);
    }

    Receipt finishTransaction() {
        Receipt receipt = currentTransaction.close();
        currentTransaction = null;
        return receipt;
    }
}