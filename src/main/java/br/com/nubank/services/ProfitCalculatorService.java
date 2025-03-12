package br.com.nubank.services;

import br.com.nubank.model.Trade;

public class ProfitCalculatorService {

    public static double calculate(double currentPurchasedCost, Trade trade) {
        if (trade.getUnitCost() > currentPurchasedCost) {
            return (trade.getUnitCost() - currentPurchasedCost) * trade.getQuantity();
        } else if (trade.getUnitCost() < currentPurchasedCost) {
            return (currentPurchasedCost - trade.getUnitCost()) * trade.getQuantity() * -1;
        }
        return 0;
    }
}
