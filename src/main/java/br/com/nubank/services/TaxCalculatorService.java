package br.com.nubank.services;

import br.com.nubank.model.Tax;
import br.com.nubank.model.Trade;

public class TaxCalculatorService {

    private static final double LIMIT_FREE_TAX = 2000;
    private static final double PERCENTAGE = 0.20;

    public static Tax calculateTax(double profit, Trade trade) {
        double total = trade.getUnitCost() * trade.getQuantity();
        if (total > LIMIT_FREE_TAX) {
            return new Tax(profit * PERCENTAGE);
        }
        return Tax.emptyTax();
    }
}
