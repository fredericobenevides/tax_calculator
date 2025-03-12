package br.com.nubank.dsl;

import br.com.nubank.model.OperationType;
import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class When {

    private OperationData operationData;

    public When(OperationData operationData) {
        this.operationData = operationData;
    }

    public static When operation() {
        OperationData operationData = new OperationData();
        return new When(operationData);
    }

    public When withTrade(Trade trade) {
        operationData.withTrade(trade);
        return this;
    }

    public When withWallet(Wallet wallet) {
        operationData.withWallet(wallet);
        return this;
    }

    public BuyOperation isBuyOperation() {
        Trade trade = operationData.getTrade();

        boolean runOperation = false;
        if (OperationType.BUY == trade.getOperationType()) {
            runOperation = true;
        }

        operationData.withRunOperation(runOperation);
        return new BuyOperation(operationData);
    }

    public SellOperation isSellOperation() {
        Trade trade = operationData.getTrade();

        boolean runOperation = false;
        if (OperationType.SELL == trade.getOperationType()) {
            runOperation = true;
        }

        operationData.withRunOperation(runOperation);
        return new SellOperation(operationData);
    }

}
