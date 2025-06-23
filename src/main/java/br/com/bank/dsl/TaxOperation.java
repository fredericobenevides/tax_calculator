package br.com.bank.dsl;

import br.com.bank.model.Tax;
import br.com.bank.model.Trade;

public class TaxOperation {

    private static final double LIMIT_FREE_TAX = 20000;
    private static final double PERCENTAGE = 0.20;

    private final double profit;
    private final OperationData operationData;

    public TaxOperation(OperationData operationData, double profit) {
        this.operationData = operationData;
        this.profit = profit;
    }

    public TaxOperation free() {
        if (this.operationData.isRunOperation()) {
            this.operationData.setTax(Tax.emptyTax());
        }
        return this;
    }

    public TaxOperation calculate() {
        if (this.operationData.isRunOperation()) {
            Trade trade = operationData.getTrade();
            Tax tax = null;

            if (this.operationData.isRunOperation()) {
                double total = trade.getUnitCost() * trade.getQuantity();
                if (total > LIMIT_FREE_TAX) {
                    tax = new Tax(profit * PERCENTAGE);
                } else {
                    tax = Tax.emptyTax();
                }
            }

            this.operationData.setTax(tax);
        }

        return this;
    }

    public Tax generate() {
        return operationData.getTax();
    }

    public When or() {
        return new When(operationData);
    }
}
