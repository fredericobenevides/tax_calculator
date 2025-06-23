package br.com.bank.dsl;

import br.com.bank.model.Trade;
import br.com.bank.model.Wallet;

public class SellOperation {

    private final ProfitOperation profitOperation;
    private final LossOperation lossOperation;
    private final OperationData operationData;

    public SellOperation(OperationData operationData) {
        this.operationData = operationData;
        this.profitOperation = new ProfitOperation(this, operationData);
        this.lossOperation = new LossOperation(this, operationData);
    }

    public ProfitOperation profit() {
        return profitOperation;
    }

    public LossOperation loss() {
        return lossOperation;
    }

    public SellOperation deductCapitalLoss() {
        Wallet wallet = operationData.getWallet();
        double currentProfit = profitOperation.getProfit();

        if (operationData.isRunOperation()) {
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

        profitOperation.setProfit(currentProfit);
        return this;
    }

    public SellOperation removeTradeFromWallet() {
        Trade trade = operationData.getTrade();
        Wallet wallet = operationData.getWallet();

        if (operationData.isRunOperation()) {
            wallet.removeInvestment(trade.getQuantity());
        }
        return this;
    }

    public TaxOperation tax() {
        return new TaxOperation(operationData, profitOperation.getProfit());
    }
}
