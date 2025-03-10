package br.com.nubank.utils;

import br.com.nubank.domain.entities.Trade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonParser {

    private static final Gson gson = new Gson();

    public static List<Trade> parseArrayData(String arrayData) {
        return gson.fromJson(arrayData, new TypeToken<List<Trade>>(){}.getType());
    }
}
