package br.com.bank.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletTest {

    @Test
    public void testAddInvestmentForASingleInvestment() {
        Wallet wallet = new Wallet();
        wallet.addInvestment(10.0, 10000);
        assertEquals(10, wallet.getOperationCost());
        assertEquals(10000, wallet.getQuantity());
    }

    @Test
    public void testAddInvestmentForTwoInvestmentCalculateAveragePrice() {
        Wallet wallet = new Wallet();
        wallet.addInvestment(10.0, 10000);
        wallet.addInvestment(25.0, 5000);
        assertEquals(15, wallet.getOperationCost());
        assertEquals(15000, wallet.getQuantity());
    }

    @Test
    public void testAddInvestmentForMultipleInvestmentCalculateAveragePrice() {
        Wallet wallet = new Wallet();
        wallet.addInvestment(10.0, 10000);
        wallet.addInvestment(25.0, 5000);
        wallet.addInvestment(50.0, 1000);
        assertEquals(17.1875, wallet.getOperationCost());
        assertEquals(16000, wallet.getQuantity());
    }

    @Test
    public void testRemoveInvestment() {
        Wallet wallet = new Wallet();
        wallet.addInvestment(10.0, 10000);

        wallet.removeInvestment(7000);
        assertEquals(10.0, wallet.getOperationCost());
        assertEquals(3000, wallet.getQuantity());

        wallet.removeInvestment(3000);
        assertEquals(0.0, wallet.getOperationCost());
        assertEquals(0, wallet.getQuantity());
    }
}
