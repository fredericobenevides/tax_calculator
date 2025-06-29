package br.com.bank.dsl;

import br.com.bank.model.OperationType;
import br.com.bank.model.Tax;
import br.com.bank.model.Trade;
import br.com.bank.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaxOperationTest {

    private Wallet wallet;

    @BeforeEach
    public void setup() {
        wallet = new Wallet();
        wallet.addInvestment(10, 1000);
    }

    @Test
    public void testFreeWhenProcessIsTrue() {
        OperationData operationData = new OperationData();
        operationData.withRunOperation(true).withTrade(null).withWallet(wallet);

        TaxOperation operation = new TaxOperation(operationData, 1000);
        Tax tax = operation.free().generate();
        assertEquals(0, tax.getTax());
    }

    @Test
    public void testFreeWhenProcessIsFalse() {
        OperationData operationData = new OperationData();
        operationData.withRunOperation(false).withTrade(null).withWallet(wallet);

        TaxOperation operation = new TaxOperation(operationData, 1000);
        Tax tax = operation.free().generate();
        assertNull(tax);
    }

    @Test
    public void testCalculateWhenTotalIsLessThanFreeTaxReturnsAnEmptyTax() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

        TaxOperation operation = new TaxOperation(operationData, 1000);
        Tax tax = operation.calculate().generate();
        assertEquals(0, tax.getTax());
    }

    @Test
    public void testCalculateWhenTotalIsGreaterThanFreeTaxReturnsTax() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 10000);
        OperationData operationData = new OperationData();
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

        TaxOperation operation = new TaxOperation(operationData, 1000);
        Tax tax = operation.calculate().generate();
        assertEquals(200, tax.getTax());
    }

    @Test
    public void testCalculateWhenProcessIsFalseReturnsAnEmptyTax() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 10000);
        OperationData operationData = new OperationData();
        operationData.withRunOperation(false).withTrade(trade).withWallet(wallet);

        TaxOperation operation = new TaxOperation(operationData, 1000);
        Tax tax = operation.calculate().generate();
        assertNull(tax);
    }
}
