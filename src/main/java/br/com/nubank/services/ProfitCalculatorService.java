package br.com.nubank.services;

import br.com.nubank.model.Portfolio;
import br.com.nubank.model.Trade;

import java.util.Optional;

public class ProfitCalculatorService {

    public static Optional<Double> calculate(Portfolio portfolio, Trade trade) {
        double cost = Math.abs(trade.getUnitCost() - portfolio.getAveragePurchasePrice());
        double total = cost * trade.getQuantity();

        if (trade.getUnitCost() > portfolio.getAveragePurchasePrice()) {
            if (total > portfolio.getTotalCapitalLoss()) {
                total -= portfolio.getTotalCapitalLoss();
                return Optional.of(total);
            } else {
                portfolio.decreaseCapitalLoss(total);
                return Optional.empty();
            }
        } else if (trade.getUnitCost() < portfolio.getAveragePurchasePrice()) {
            portfolio.increaseCapitalLoss(total);
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }
}
