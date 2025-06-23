package br.com.bank.dsl;

import br.com.bank.model.Tax;
import br.com.bank.model.Trade;
import br.com.bank.model.Wallet;

public class OperationData {

    private boolean runOperation;
    private Trade trade;
    private Wallet wallet;

    private Tax tax;

    public OperationData withRunOperation(boolean runOperation) {
        this.runOperation = runOperation;
        return this;
    }

    public boolean isRunOperation() {
        return runOperation;
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

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Tax getTax() {
        return tax;
    }
}
