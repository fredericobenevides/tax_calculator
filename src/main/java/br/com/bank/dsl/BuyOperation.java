package br.com.bank.dsl;

import br.com.bank.model.Trade;
import br.com.bank.model.Wallet;

public class BuyOperation {

    private final OperationData operationData;

    public BuyOperation(OperationData operationData) {
        this.operationData = operationData;
    }

    public BuyOperation addTradeToWallet() {
        Trade trade = operationData.getTrade();
        Wallet wallet = operationData.getWallet();

        if (operationData.isRunOperation()) {
            wallet.addInvestment(trade.getUnitCost(), trade.getQuantity());
        }
        return this;
    }

    public TaxOperation tax() {
        return new TaxOperation(operationData, 0);
    }
}
