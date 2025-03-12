package br.com.nubank.dsl;

import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class SellOperation {

    private final boolean processOperation;
    private final Trade trade;
    private final Wallet wallet;
    private double currentProfit;
    private double currentLoss;

    public SellOperation(boolean processOperation, Trade trade, Wallet wallet) {
        this.processOperation = processOperation;
        this.trade = trade;
        this.wallet = wallet;
    }

    public SellOperation calculateProfit() {
        if (processOperation) {
            if (trade.getUnitCost() > wallet.getOperationCost()) {
                currentProfit = (trade.getUnitCost() - wallet.getOperationCost()) * trade.getQuantity();
            }
        }
        return this;
    }

    public SellOperation calculateLoss() {
        if (processOperation) {
            if (trade.getUnitCost() < wallet.getOperationCost()) {
                currentLoss = (wallet.getOperationCost() - trade.getUnitCost()) * trade.getQuantity();
            }
        }
        return this;
    }

    public SellOperation addLossToWallet() {
        if (processOperation) {
            wallet.increaseCapitalLoss(currentLoss);
        }
        return this;
    }

    public SellOperation deductCapitalLoss() {
        if (processOperation) {
            if (wallet.getTotalCapitalLoss() > 0) {
                if (currentProfit > 0 && currentProfit > wallet.getTotalCapitalLoss()) {
                    currentProfit = currentProfit - wallet.getTotalCapitalLoss();
                    wallet.decreaseCapitalLoss(currentProfit);
                } else if (currentProfit > 0 && currentProfit <= wallet.getTotalCapitalLoss()) {
                    wallet.decreaseCapitalLoss(currentProfit);
                    currentProfit = 0;
                }
            }
        }
        return this;
    }

    public SellOperation removeTradeFromWallet() {
        if (processOperation) {
            wallet.removeInvestment(trade.getQuantity());
        }
        return this;
    }

    public TaxOperation tax() {
        return new TaxOperation(trade, wallet, currentProfit);
    }
}
