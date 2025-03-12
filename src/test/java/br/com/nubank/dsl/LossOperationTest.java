package br.com.nubank.dsl;

import br.com.nubank.model.OperationType;
import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LossOperationTest {

    private Wallet wallet;
    private SellOperation sellOperation;

    @BeforeEach
    public void setup() {
        wallet = new Wallet();
        wallet.addInvestment(10, 1000);
        sellOperation = new SellOperation(true, null, wallet);
    }

    @Test
    public void testCalculateWhenUnitCostLessThanWalletOperationCostLossIsCalculated() {
        Trade trade = new Trade(OperationType.BUY, 5.0, 1000);
        LossOperation operation = new LossOperation(sellOperation, true, trade, wallet);
        operation.calculate();
        assertEquals(5000, operation.getLoss());
    }

    @Test
    public void testCalculateWhenUnitCostGreaterThanWalletOperationCostLossIsNotCalculated() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        ProfitOperation operation = new ProfitOperation(sellOperation, true, trade, wallet);
        operation.setProfit(10);
        operation.calculate();
        assertEquals(10, operation.getProfit());
    }

    @Test
    public void testCalculateWhenProcessIsFalseLossIsNotCalculated() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        ProfitOperation operation = new ProfitOperation(sellOperation, false, trade, wallet);
        operation.setProfit(10);
        operation.calculate();
        assertEquals(10, operation.getProfit());
    }

    @Test
    public void testAddToWalletWhenProcessIsTrue() {
        Trade trade = new Trade(OperationType.BUY, 5.0, 1000);
        LossOperation operation = new LossOperation(sellOperation, true, trade, wallet);
        operation.calculate();
        operation.addToWallet();
        assertEquals(5000, wallet.getTotalCapitalLoss());
    }

    @Test
    public void testAddToWalletWhenProcessIsFalse() {
        Trade trade = new Trade(OperationType.BUY, 5.0, 1000);
        LossOperation operation = new LossOperation(sellOperation, false, trade, wallet);
        operation.calculate();
        operation.addToWallet();
        assertEquals(0, wallet.getTotalCapitalLoss());
    }
}
