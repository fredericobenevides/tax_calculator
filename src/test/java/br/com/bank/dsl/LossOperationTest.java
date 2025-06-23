package br.com.bank.dsl;

import br.com.bank.model.OperationType;
import br.com.bank.model.Trade;
import br.com.bank.model.Wallet;
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

        sellOperation = new SellOperation(null);
    }

    @Test
    public void testCalculateWhenUnitCostLessThanWalletOperationCostLossIsCalculated() {
        Trade trade = new Trade(OperationType.BUY, 5.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

        LossOperation operation = new LossOperation(sellOperation, operationData);
        operation.calculate();
        assertEquals(5000, operation.getLoss());
    }

    @Test
    public void testCalculateWhenUnitCostGreaterThanWalletOperationCostLossIsNotCalculated() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

        ProfitOperation operation = new ProfitOperation(sellOperation, operationData);
        operation.setProfit(10);
        operation.calculate();
        assertEquals(10, operation.getProfit());
    }

    @Test
    public void testCalculateWhenProcessIsFalseLossIsNotCalculated() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withRunOperation(false).withTrade(trade).withWallet(wallet);

        ProfitOperation operation = new ProfitOperation(sellOperation, operationData);
        operation.setProfit(10);
        operation.calculate();
        assertEquals(10, operation.getProfit());
    }

    @Test
    public void testAddToWalletWhenProcessIsTrue() {
        Trade trade = new Trade(OperationType.BUY, 5.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

        LossOperation operation = new LossOperation(sellOperation, operationData);
        operation.calculate();
        operation.addToWallet();
        assertEquals(5000, wallet.getTotalCapitalLoss());
    }

    @Test
    public void testAddToWalletWhenProcessIsFalse() {
        Trade trade = new Trade(OperationType.BUY, 5.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withRunOperation(false).withTrade(trade).withWallet(wallet);

        LossOperation operation = new LossOperation(sellOperation, operationData);
        operation.calculate();
        operation.addToWallet();
        assertEquals(0, wallet.getTotalCapitalLoss());
    }
}
