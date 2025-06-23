package br.com.bank;

import br.com.bank.model.Tax;
import br.com.bank.model.Trade;
import br.com.bank.services.TaxProcessorService;
import br.com.bank.utils.InputReader;
import br.com.bank.utils.JsonParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> listData = InputReader.readEachLine();

        for (String jsonString : listData) {
            List<Trade> trades = JsonParser.parseArrayData(jsonString);

            List<Tax> taxes = new TaxProcessorService().calculate(trades);
            String jsonData = JsonParser.parseToString(taxes);
            System.out.println(jsonData);
        }
    }
}