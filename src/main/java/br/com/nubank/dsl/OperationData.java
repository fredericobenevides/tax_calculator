package br.com.nubank.dsl;

import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class OperationData {

    private boolean processOperation;
    private Trade trade;
    private Wallet wallet;

    public OperationData withProcessOperation(boolean processOperation) {
        this.processOperation = processOperation;
        return this;
    }

    public boolean isProcessOperation() {
        return processOperation;
    }

    public OperationData withTrade(Trade trade) {
        this.trade = trade;
        return this;
    }

    public Trade getTrade() {
        return trade;
    }

    public OperationData withWallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
