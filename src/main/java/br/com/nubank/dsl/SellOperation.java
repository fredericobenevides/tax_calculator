package br.com.nubank.dsl;

import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class SellOperation {

    private final boolean processOperation;
    private final Trade trade;
    private final Wallet wallet;

    private ProfitOperation profitOperation;
    private LossOperation lossOperation;

    public SellOperation(boolean processOperation, Trade trade, Wallet wallet) {
        this.processOperation = processOperation;
        this.trade = trade;
        this.wallet = wallet;
        this.profitOperation = new ProfitOperation(this, processOperation, trade, wallet);
        this.lossOperation = new LossOperation(this, processOperation, trade, wallet);
    }

    public ProfitOperation profit() {
        return profitOperation;
    }

    public LossOperation loss() {
        return lossOperation;
    }

    public SellOperation deductCapitalLoss() {
        double currentProfit = profitOperation.getProfit();

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

        profitOperation.setProfit(currentProfit);
        return this;
    }

    public SellOperation removeTradeFromWallet() {
        if (processOperation) {
            wallet.removeInvestment(trade.getQuantity());
        }
        return this;
    }

    public TaxOperation tax() {
        return new TaxOperation(trade, wallet, profitOperation.getProfit());
    }
}
