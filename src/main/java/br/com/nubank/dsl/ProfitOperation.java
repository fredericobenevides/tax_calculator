package br.com.nubank.dsl;

import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class ProfitOperation {
    private final SellOperation sellOperation;
    private final boolean processOperation;
    private final Trade trade;
    private final Wallet wallet;
    private double currentProfit;

    public ProfitOperation(SellOperation sellOperation, boolean processOperation, Trade trade, Wallet wallet) {
        this.sellOperation = sellOperation;
        this.processOperation = processOperation;
        this.trade = trade;
        this.wallet = wallet;
    }

    public ProfitOperation calculate() {
        if (processOperation) {
            if (trade.getUnitCost() > wallet.getOperationCost()) {
                currentProfit = (trade.getUnitCost() - wallet.getOperationCost()) * trade.getQuantity();
            }
        }
        return this;
    }

    public SellOperation and() {
        return sellOperation;
    }

    public double getProfit() {
        return currentProfit;
    }

    public void setProfit(double currentProfit) {
        this.currentProfit = currentProfit;
    }
}
