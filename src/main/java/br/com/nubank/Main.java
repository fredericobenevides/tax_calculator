package br.com.nubank;

import br.com.nubank.domain.entities.Tax;
import br.com.nubank.domain.entities.Trade;
import br.com.nubank.utils.JsonParser;
import br.com.nubank.utils.InputReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> listData = InputReader.readEachLine();



        for (String jsonString : listData) {
            List<Trade> trades = JsonParser.parseArrayData(jsonString);
//            System.out.println(trades);

            List<Tax> taxes = new TaxCalculator().calculate(trades);
            System.out.println(taxes);
        }
    }
}