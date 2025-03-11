package br.com.nubank.services;

import br.com.nubank.model.OperationType;
import br.com.nubank.model.Portfolio;
import br.com.nubank.model.Tax;
import br.com.nubank.model.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaxService {

    public List<Tax> calculate(List<Trade> trades) {
        List<Tax> listOfTax = new ArrayList<>();
        Portfolio portfolio = new Portfolio();

        for (Trade trade : trades) {
            Tax tax = new Tax();

            if (OperationType.BUY == trade.getOperationType()) {
                portfolio.addInvestment(trade);
            } else {
                portfolio.removeInvestment(trade);
                Optional<Double> profit = ProfitCalculatorService.calculate(portfolio, trade);
                profit.ifPresent(p -> tax.addProfit(p, trade));
            }

            listOfTax.add(tax);
        }

        return listOfTax;
    }
}
