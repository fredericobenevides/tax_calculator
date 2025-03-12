package br.com.nubank.dsl;

import br.com.nubank.model.Tax;
import br.com.nubank.model.Trade;

public class TaxOperation {

    private static final double LIMIT_FREE_TAX = 20000;
    private static final double PERCENTAGE = 0.20;

    private final double profit;
    private static Tax tax;
    private final OperationData operationData;

    public TaxOperation(OperationData operationData, double profit) {
        this.operationData = operationData;
        this.profit = profit;
    }

    public TaxOperation free() {
        if (this.operationData.isProcessOperation()) {
            this.tax = Tax.emptyTax();
        }
        return this;
    }

    public TaxOperation calculate() {
        Trade trade = operationData.getTrade();

        if (this.operationData.isProcessOperation()) {
            double total = trade.getUnitCost() * trade.getQuantity();
            if (total > LIMIT_FREE_TAX) {
                this.tax = new Tax(profit * PERCENTAGE);
            } else {
                tax = Tax.emptyTax();
            }
        }
        return this;
    }

    public Tax generate() {
        return tax;
    }

    public When or() {
        return new When(operationData);
    }

    public static void setTax(Tax tax) {
        TaxOperation.tax = tax;
    }
}
