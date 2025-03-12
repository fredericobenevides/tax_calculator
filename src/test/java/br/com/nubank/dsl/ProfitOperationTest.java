package br.com.nubank.dsl;

import br.com.nubank.model.OperationType;
import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfitOperationTest {

    private Wallet wallet;
    private SellOperation sellOperation;

    @BeforeEach
    public void setup() {

        wallet = new Wallet();
        wallet.addInvestment(10, 1000);
        sellOperation = new SellOperation(true, null, wallet);
    }

    @Test
    public void testCalculateWhenUnitCostGreaterThanWalletOperationCostProfitIsCalculated() {
        Trade trade = new Trade(OperationType.BUY, 20.0, 1000);
        ProfitOperation operation = new ProfitOperation(sellOperation, true, trade, wallet);
        operation.setProfit(10);
        operation.calculate();
        assertEquals(10000, operation.getProfit());
    }

    @Test
    public void testCalculateWhenUnitCostLessThanWalletOperationCostProfitIsNotCalculated() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        ProfitOperation operation = new ProfitOperation(sellOperation, true, trade, wallet);
        operation.setProfit(10);
        operation.calculate();
        assertEquals(10, operation.getProfit());
    }

    @Test
    public void testCalculateWhenProcessIsFalseProfitIsNotCalculated() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        ProfitOperation operation = new ProfitOperation(sellOperation, false, trade, wallet);
        operation.setProfit(10);
        operation.calculate();
        assertEquals(10, operation.getProfit());
    }
}
