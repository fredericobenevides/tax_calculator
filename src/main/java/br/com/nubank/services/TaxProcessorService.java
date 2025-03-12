package br.com.nubank.services;

import br.com.nubank.dsl.When;
import br.com.nubank.model.Tax;
import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;

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
