package br.com.nubank.services;

import br.com.nubank.model.Investment;
import br.com.nubank.model.OperationType;
import br.com.nubank.model.Tax;
import br.com.nubank.model.Trade;

import java.util.ArrayList;
import java.util.List;

public class TaxProcessorService {

    public List<Tax> calculate(List<Trade> trades) {
        List<Tax> listOfTax = new ArrayList<>();
        Investment investment = new Investment();

        for (Trade trade : trades) {
            Tax tax;

            if (OperationType.BUY == trade.getOperationType()) {
                investment.addInvestment(trade.getUnitCost(), trade.getQuantity());
                tax = Tax.emptyTax();
            } else {
                double profitOrLoss = ProfitCalculatorService.calculate(investment.getPurchasedCost(), trade);

                if (investment.getTotalCapitalLoss() > 0) {
                    if (profitOrLoss > 0 && investment.getTotalCapitalLoss() >= profitOrLoss) {
                        profitOrLoss = 0;
                        investment.decreaseCapitalLoss(profitOrLoss);
                    } else if (profitOrLoss > 0 && investment.getTotalCapitalLoss() < profitOrLoss) {
                        profitOrLoss = investment.getTotalCapitalLoss() - profitOrLoss;
                        investment.decreaseCapitalLoss(profitOrLoss);
                    } else if (profitOrLoss < 0) {
                        investment.increaseCapitalLoss(profitOrLoss);
                    }
                }

                if (profitOrLoss > 0) {
                    tax = TaxCalculatorService.calculateTax(profitOrLoss, trade);
                } else {
                    tax = Tax.emptyTax();
                }

                investment.removeInvestment(trade.getQuantity());
            }

            listOfTax.add(tax);
        }

        return listOfTax;
    }
}
