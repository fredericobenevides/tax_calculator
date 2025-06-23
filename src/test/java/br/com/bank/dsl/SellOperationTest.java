package br.com.bank.dsl;

import br.com.bank.model.OperationType;
import br.com.bank.model.Trade;
import br.com.bank.model.Wallet;
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
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

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
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.profit().setProfit(5000);
        wallet.increaseCapitalLoss(10000);

        operation.deductCapitalLoss();

        assertEquals(0, operation.profit().getProfit());
        assertEquals(5000, wallet.getTotalCapitalLoss());
    }

    @Test
    public void testDeductCapitalLossWhenRunOperationIsFalse() {
        OperationData operationData = new OperationData();
        operationData.withRunOperation(false).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.profit().setProfit(10000);
        wallet.increaseCapitalLoss(5000);

        operation.deductCapitalLoss();

        assertEquals(10000, operation.profit().getProfit());
        assertEquals(5000, wallet.getTotalCapitalLoss());
    }

    @Test
    public void testRemoveTradeFromWalletWhenRunOperationIsTrue() {
        OperationData operationData = new OperationData();
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.removeTradeFromWallet();
        assertEquals(9000, wallet.getQuantity());
    }

    @Test
    public void testRemoveTradeFromWalletWhenRunOperationIsFalse() {
        OperationData operationData = new OperationData();
        operationData.withRunOperation(false).withTrade(trade).withWallet(wallet);

        SellOperation operation = new SellOperation(operationData);
        operation.removeTradeFromWallet();
        assertEquals(10000, wallet.getQuantity());
    }
}
