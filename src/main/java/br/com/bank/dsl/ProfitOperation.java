package br.com.bank.dsl;

import br.com.bank.model.Trade;
import br.com.bank.model.Wallet;

public class ProfitOperation {
    private final SellOperation sellOperation;
    private final OperationData operationData;
    private double currentProfit;

    public ProfitOperation(SellOperation sellOperation, OperationData operationData) {
        this.sellOperation = sellOperation;
        this.operationData = operationData;
    }

    public ProfitOperation calculate() {
        Trade trade = operationData.getTrade();
        Wallet wallet = operationData.getWallet();

        if (operationData.isRunOperation()) {
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
