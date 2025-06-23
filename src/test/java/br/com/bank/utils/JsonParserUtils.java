package br.com.bank.utils;

import br.com.bank.model.Tax;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParserUtils {

    @Test
    public void testParseToStringForAListOfTaxToHave2Decimals() {
        assertEquals("[{\"tax\":10.12}]", JsonParser.parseToString(List.of(new Tax(10.12))));
        assertEquals("[{\"tax\":10.12}]", JsonParser.parseToString(List.of(new Tax(10.12345))));
    }
}
