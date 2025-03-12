package br.com.nubank.dsl;

import br.com.nubank.model.OperationType;
import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellOperationTest {

    private Trade trade;
    private Wallet wallet;

    @BeforeEach
    public void setup() {
        trade = new Trade(OperationType.SELL, 10.0, 1000);
        wallet = new Wallet();
        wallet.addInvestment(10, 10000);
    }

    @Test
    public void testDeductCapitalLossWhenCapitalLossGreaterThanWalletCapitalLoss() {
        OperationData operationData = new OperationData();
        operationData.withProcessOperation(true).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.profit().setProfit(10000);
        wallet.increaseCapitalLoss(5000);

        operation.deductCapitalLoss();

        assertEquals(5000, operation.profit().getProfit());
        assertEquals(0, wallet.getTotalCapitalLoss());
    }

    @Test
    public void testDeductCapitalLossWhenCapitalLossLessThanWalletCapitalLoss() {
        OperationData operationData = new OperationData();
        operationData.withProcessOperation(true).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.profit().setProfit(5000);
        wallet.increaseCapitalLoss(10000);

        operation.deductCapitalLoss();

        assertEquals(0, operation.profit().getProfit());
        assertEquals(5000, wallet.getTotalCapitalLoss());
    }

    @Test
    public void testDeductCapitalLossWhenProcessOperationIsFalse() {
        OperationData operationData = new OperationData();
        operationData.withProcessOperation(false).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.profit().setProfit(10000);
        wallet.increaseCapitalLoss(5000);

        operation.deductCapitalLoss();

        assertEquals(10000, operation.profit().getProfit());
        assertEquals(5000, wallet.getTotalCapitalLoss());
    }

    @Test
    public void testRemoveTradeFromWalletWhenProcessOperationIsTrue() {
        OperationData operationData = new OperationData();
        operationData.withProcessOperation(true).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.removeTradeFromWallet();
        assertEquals(9000, wallet.getQuantity());
    }

    @Test
    public void testRemoveTradeFromWalletWhenProcessOperationIsFalse() {
        OperationData operationData = new OperationData();
        operationData.withProcessOperation(false).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.removeTradeFromWallet();
        assertEquals(10000, wallet.getQuantity());
    }
}
