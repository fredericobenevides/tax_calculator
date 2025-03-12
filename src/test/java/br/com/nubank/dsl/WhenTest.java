package br.com.nubank.dsl;

import br.com.nubank.model.OperationType;
import br.com.nubank.model.Trade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WhenTest {

    @Test
    public void testIsBuyOperationWhenTradeIsBuy() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withTrade(trade);
        new When(operationData).isBuyOperation();
        assertTrue(operationData.isRunOperation());
    }

    @Test
    public void testIsBuyOperationWhenTradeIsSell() {
        Trade trade = new Trade(OperationType.SELL, 10.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withTrade(trade);
        new When(operationData).isBuyOperation();
        assertFalse(operationData.isRunOperation());
    }

    @Test
    public void testIsSellOperationWhenTradeIsSell() {
        Trade trade = new Trade(OperationType.SELL, 10.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withTrade(trade);
        new When(operationData).isSellOperation();
        assertTrue(operationData.isRunOperation());
    }

    @Test
    public void testIsSellOperationWhenTradeIsBuy() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        OperationData operationData = new OperationData();
        operationData.withTrade(trade);
        new When(operationData).isSellOperation();
        assertFalse(operationData.isRunOperation());
    }
}
