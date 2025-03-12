package br.com.nubank;

import br.com.nubank.model.Tax;
import br.com.nubank.model.Trade;
import br.com.nubank.services.TaxProcessorService;
import br.com.nubank.utils.InputReader;
import br.com.nubank.utils.JsonParser;

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