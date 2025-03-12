package br.com.nubank.dsl;

import br.com.nubank.model.OperationType;
import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class When {

    private Trade trade;
    private Wallet wallet;

    public When(Trade trade, Wallet wallet) {
        this.trade = trade;
        this.wallet = wallet;
    };

    public When(Trade trade) {
        this.trade = trade;
    };

    public static When trade(Trade trade) {
        return new When(trade);
    }

    public When withWallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public BuyOperation isBuyOperation() {
        boolean processOperation = false;
        if (OperationType.BUY == trade.getOperationType()) {
            processOperation = true;
        }
        return new BuyOperation(processOperation, this.trade, this.wallet);
    }

    public SellOperation isSellOperation() {
        boolean processOperation = false;
        if (OperationType.SELL == trade.getOperationType()) {
            processOperation = true;
        }
        return new SellOperation(processOperation, this.trade, this.wallet);
    }
}
