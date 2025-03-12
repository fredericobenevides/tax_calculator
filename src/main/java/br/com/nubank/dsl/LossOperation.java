package br.com.nubank.dsl;

import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class LossOperation {

    private final SellOperation sellOperation;
    private final OperationData operationData;
    private double currentLoss;

    public LossOperation(SellOperation sellOperation, OperationData operationData) {
        this.sellOperation = sellOperation;
        this.operationData = operationData;
    }

    public LossOperation calculate() {
        Trade trade = operationData.getTrade();
        Wallet wallet = operationData.getWallet();

        if (operationData.isRunOperation()) {
            if (trade.getUnitCost() < wallet.getOperationCost()) {
                currentLoss = (wallet.getOperationCost() - trade.getUnitCost()) * trade.getQuantity();
            }
        }
        return this;
    }

    public LossOperation addToWallet() {
        Wallet wallet = operationData.getWallet();

        if (operationData.isRunOperation()) {
            wallet.increaseCapitalLoss(currentLoss);
        }
        return this;
    }

    public SellOperation and() {
        return sellOperation;
    }

    public double getLoss() {
        return currentLoss;
    }
}
