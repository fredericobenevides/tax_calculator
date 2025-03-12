package br.com.nubank.utils;

import br.com.nubank.model.Tax;
import br.com.nubank.model.Trade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class JsonParser {

    private static final Gson gson = new Gson();

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public static List<Trade> parseArrayData(String arrayData) {
        return gson.fromJson(arrayData, new TypeToken<List<Trade>>(){}.getType());
    }

    public static String parseToString(List<Tax> list) {
        List<Tax> formatedTax = list.stream()
                .map(tax -> decimalFormat.format(tax.getTax()))
                .map(taxString -> new Tax(Double.parseDouble(taxString)))
                .collect(Collectors.toList());
        return gson.toJson(formatedTax);
    }
}
