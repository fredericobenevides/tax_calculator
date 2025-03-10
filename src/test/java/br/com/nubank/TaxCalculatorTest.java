package br.com.nubank;

import br.com.nubank.domain.entities.OperationType;
import br.com.nubank.domain.entities.Tax;
import br.com.nubank.domain.entities.Trade;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculatorTest {

    @Test
    public void testCalculateForCase1() {
        Trade trade1 = new Trade(OperationType.BUY, 10, 100);
        Trade trade2 = new Trade(OperationType.SELL, 15, 50);
        Trade trade3 = new Trade(OperationType.SELL, 15, 50);

        List<Tax> taxes = new TaxCalculator().calculate(List.of(trade1, trade2, trade3));

        assertEquals(0.00, taxes.get(0).getValue());
        assertEquals(0.00, taxes.get(1).getValue());
        assertEquals(0.00, taxes.get(2).getValue());
    }

    @Test
    public void testCalculateForCase2() {
        Trade trade1 = new Trade(OperationType.BUY, 10, 1000);
        Trade trade2 = new Trade(OperationType.SELL, 20, 5000);
        Trade trade3 = new Trade(OperationType.SELL, 5, 5000);

        List<Tax> taxes = new TaxCalculator().calculate(List.of(trade1, trade2, trade3));

        assertEquals(0.00, taxes.get(0).getValue());
        assertEquals(10000.00, taxes.get(1).getValue());
        assertEquals(0.00, taxes.get(2).getValue());
    }

    @Test
    public void testCalculateForCase3() {
        Trade trade1 = new Trade(OperationType.BUY, 10, 10000);
        Trade trade2 = new Trade(OperationType.SELL, 5, 5000);
        Trade trade3 = new Trade(OperationType.SELL, 20, 3000);

        List<Tax> taxes = new TaxCalculator().calculate(List.of(trade1, trade2, trade3));

        assertEquals(0.00, taxes.get(0).getValue());
        assertEquals(0.00, taxes.get(1).getValue());
        assertEquals(1000.00, taxes.get(2).getValue());
    }

    @Test
    public void testCalculateForCase4() {
        Trade trade1 = new Trade(OperationType.BUY, 10, 10000);
        Trade trade2 = new Trade(OperationType.BUY, 25, 5000);
        Trade trade3 = new Trade(OperationType.SELL, 15, 10000);

        List<Tax> taxes = new TaxCalculator().calculate(List.of(trade1, trade2, trade3));

        assertEquals(0.00, taxes.get(0).getValue());
        assertEquals(0.00, taxes.get(1).getValue());
        assertEquals(0.00, taxes.get(2).getValue());
    }

    @Test
    public void testCalculateForCase5() {
        Trade trade1 = new Trade(OperationType.BUY, 10, 10000);
        Trade trade2 = new Trade(OperationType.BUY, 25, 5000);
        Trade trade3 = new Trade(OperationType.SELL, 15, 10000);
        Trade trade4 = new Trade(OperationType.SELL, 25, 5000);

        List<Tax> taxes = new TaxCalculator().calculate(List.of(trade1, trade2, trade3, trade4));

        assertEquals(0.00, taxes.get(0).getValue());
        assertEquals(0.00, taxes.get(1).getValue());
        assertEquals(0.00, taxes.get(2).getValue());
        assertEquals(10000.00, taxes.get(3).getValue());
    }

    @Test
    public void testCalculateForCase6() {
        Trade trade1 = new Trade(OperationType.BUY, 10, 10000);
        Trade trade2 = new Trade(OperationType.SELL, 2, 5000);
        Trade trade3 = new Trade(OperationType.SELL, 20, 2000);
        Trade trade4 = new Trade(OperationType.SELL, 20, 2000);
        Trade trade5 = new Trade(OperationType.SELL, 25, 1000);

        List<Tax> taxes = new TaxCalculator().calculate(List.of(trade1, trade2, trade3, trade4, trade5));

        assertEquals(0.00, taxes.get(0).getValue());
        assertEquals(0.00, taxes.get(1).getValue());
        assertEquals(0.00, taxes.get(2).getValue());
        assertEquals(0.00, taxes.get(3).getValue());
        assertEquals(3000.00, taxes.get(4).getValue());
    }

    @Test
    public void testCalculateForCase7() {
        Trade trade1 = new Trade(OperationType.BUY, 10, 10000);
        Trade trade2 = new Trade(OperationType.SELL, 2, 5000);
        Trade trade3 = new Trade(OperationType.SELL, 20, 2000);
        Trade trade4 = new Trade(OperationType.SELL, 20, 2000);
        Trade trade5 = new Trade(OperationType.SELL, 25, 1000);
        Trade trade6 = new Trade(OperationType.BUY, 20, 10000);
        Trade trade7 = new Trade(OperationType.SELL, 15, 5000);
        Trade trade8 = new Trade(OperationType.SELL, 30, 4350);
        Trade trade9 = new Trade(OperationType.SELL, 30, 650);

        List<Tax> taxes = new TaxCalculator().calculate(List.of(trade1, trade2, trade3, trade4, trade5,
                                                                trade6, trade7, trade8, trade9));

        assertEquals(0.00, taxes.get(0).getValue());
        assertEquals(0.00, taxes.get(1).getValue());
        assertEquals(0.00, taxes.get(2).getValue());
        assertEquals(0.00, taxes.get(3).getValue());
        assertEquals(3000.00, taxes.get(4).getValue());
        assertEquals(0.00, taxes.get(5).getValue());
        assertEquals(0.00, taxes.get(6).getValue());
        assertEquals(3700.00, taxes.get(7).getValue());
        assertEquals(0.00, taxes.get(8).getValue());
    }

    @Test
    public void testCalculateForCase9() {
        Trade trade1 = new Trade(OperationType.BUY, 10, 10000);
        Trade trade2 = new Trade(OperationType.SELL, 50, 10000);
        Trade trade3 = new Trade(OperationType.BUY, 20, 10000);
        Trade trade4 = new Trade(OperationType.SELL, 50, 10000);

        List<Tax> taxes = new TaxCalculator().calculate(List.of(trade1, trade2, trade3, trade4));

        assertEquals(0.00, taxes.get(0).getValue());
        assertEquals(80000.00, taxes.get(1).getValue());
        assertEquals(0.00, taxes.get(2).getValue());
        assertEquals(60000.00, taxes.get(3).getValue());
    }
}
