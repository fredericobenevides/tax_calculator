package br.com.nubank;

import br.com.nubank.domain.entities.OperationType;
import br.com.nubank.domain.entities.Tax;
import br.com.nubank.domain.entities.Trade;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculator {

    private double averageBuyCost;

    private int quantity;

    private int totalLoss;

    private List<Tax> listOfTax = new ArrayList<>();

    public List<Tax> calculate(List<Trade> trades) {
        for (Trade trade : trades) {
            if (OperationType.BUY == trade.getOperationType()) {
                averageBuyCost = (averageBuyCost * quantity + trade.getUnitCost() * trade.getQuantity()) / (quantity + trade.getQuantity());
                quantity += trade.getQuantity();
                listOfTax.add(new Tax());
            } else {
                quantity -= trade.getQuantity();
                boolean noTax = false;

                double totalSell = trade.getTotal();
                if (totalSell < 20000) {
                    noTax = true;
                }

                if (trade.getUnitCost() > averageBuyCost) {
                    double profit = (trade.getUnitCost() - averageBuyCost) * trade.getQuantity();

                    if (totalLoss > profit) {
                        totalLoss -= profit;
                        noTax = true;
                    } else {
                        profit -= totalLoss;
                        totalLoss = 0;
                    }

                    if (profit == 0) {
                        noTax = true;
                    }

                    if (noTax) {
                        listOfTax.add(new Tax());
                    } else {
                        listOfTax.add(new Tax(profit * 0.2));
                    }
                } else if (trade.getUnitCost() < averageBuyCost ) {
                    totalLoss += (averageBuyCost - trade.getUnitCost()) * trade.getQuantity();
                    listOfTax.add(new Tax());
                } else {
                    listOfTax.add(new Tax());
                }
            }
        }

        return listOfTax;
    }
}
