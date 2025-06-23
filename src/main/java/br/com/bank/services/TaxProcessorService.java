package br.com.bank.services;

import br.com.bank.dsl.When;
import br.com.bank.model.Tax;
import br.com.bank.model.Trade;
import br.com.bank.model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class TaxProcessorService {

    public List<Tax> calculate(List<Trade> trades) {
        List<Tax> listOfTax = new ArrayList<>();
        Wallet wallet = new Wallet();

        for (Trade trade : trades) {
            Tax tax = When.operation().withTrade(trade).withWallet(wallet)
                    .isBuyOperation()
                        .addTradeToWallet()
                        .tax()
                            .free()
                    .or()
                    .isSellOperation()
                        .profit()
                            .calculate()
                        .and()
                        .loss()
                            .calculate()
                            .addToWallet()
                        .and()
                        .deductCapitalLoss()
                        .removeTradeFromWallet()
                        .tax()
                            .calculate()
                            .generate();
            listOfTax.add(tax);
        }

        return listOfTax;
    }
}
