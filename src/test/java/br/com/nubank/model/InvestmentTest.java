package br.com.nubank.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestmentTest {

    @Test
    public void testAddInvestmentForASingleInvestment() {
        Investment investment = new Investment();
        investment.addInvestment(10.0, 10000);
        assertEquals(10, investment.getPurchasedCost());
        assertEquals(10000, investment.getQuantity());
    }

    @Test
    public void testAddInvestmentForTwoInvestmentCalculateAveragePrice() {
        Investment investment = new Investment();
        investment.addInvestment(10.0, 10000);
        investment.addInvestment(25.0, 5000);
        assertEquals(15, investment.getPurchasedCost());
        assertEquals(15000, investment.getQuantity());
    }

    @Test
    public void testRemoveInvestment() {
        Investment investment = new Investment();
        investment.addInvestment(10.0, 10000);

        investment.removeInvestment(7000);
        assertEquals(10.0, investment.getPurchasedCost());
        assertEquals(3000, investment.getQuantity());

        investment.removeInvestment(3000);
        assertEquals(0.0, investment.getPurchasedCost());
        assertEquals(0, investment.getQuantity());
    }
}
