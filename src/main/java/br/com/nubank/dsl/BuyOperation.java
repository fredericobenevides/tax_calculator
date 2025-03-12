package br.com.nubank.dsl;

import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class BuyOperation {

    private final boolean processOperation;
    private final Trade trade;
    private final Wallet wallet;

    public BuyOperation(boolean processOperation, Trade trade, Wallet wallet) {
        this.processOperation = processOperation;
        this.trade = trade;
        this.wallet = wallet;
    }

    public BuyOperation addTradeToWallet() {
        if (processOperation) {
            wallet.addInvestment(trade.getUnitCost(), trade.getQuantity());
        }
        return this;
    }

    public TaxOperation tax() {
        return new TaxOperation(processOperation, trade, wallet, 0);
    }

    public boolean isProcessOperation() {
        return processOperation;
    }
}
