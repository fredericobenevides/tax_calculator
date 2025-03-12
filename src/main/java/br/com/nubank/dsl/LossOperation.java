package br.com.nubank.dsl;

import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class LossOperation {
    private final SellOperation sellOperation;
    private final boolean processOperation;
    private final Trade trade;
    private final Wallet wallet;
    private double currentLoss;

    public LossOperation(SellOperation sellOperation, boolean processOperation, Trade trade, Wallet wallet) {
        this.sellOperation = sellOperation;
        this.processOperation = processOperation;
        this.trade = trade;
        this.wallet = wallet;
    }

    public LossOperation calculate() {
        if (processOperation) {
            if (trade.getUnitCost() < wallet.getOperationCost()) {
                currentLoss = (wallet.getOperationCost() - trade.getUnitCost()) * trade.getQuantity();
            }
        }
        return this;
    }

    public LossOperation addToWallet() {
        if (processOperation) {
            wallet.increaseCapitalLoss(currentLoss);
        }
        return this;
    }

    public SellOperation and() {
        return sellOperation;
    }
}
