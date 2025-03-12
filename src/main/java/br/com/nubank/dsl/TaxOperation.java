package br.com.nubank.dsl;

import br.com.nubank.model.Tax;
import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

public class TaxOperation {

    private static final double LIMIT_FREE_TAX = 2000;
    private static final double PERCENTAGE = 0.20;

    private final Trade trade;
    private final Wallet wallet;
    private final double profit;
    private Tax tax;

    public TaxOperation(Trade trade, Wallet wallet, double profit) {
        this.trade = trade;
        this.wallet = wallet;
        this.profit = profit;
    }

    public TaxOperation free() {
        this.tax = Tax.emptyTax();
        return this;
    }

    public TaxOperation calculate() {
        double total = trade.getUnitCost() * trade.getQuantity();
        if (total > LIMIT_FREE_TAX) {
            this.tax = new Tax(profit * PERCENTAGE);
        } else {
            tax = Tax.emptyTax();
        }
        return this;
    }

    public Tax generate() {
        return tax;
    }

    public When or() {
        return new When(this.trade, this.wallet);
    }
}
