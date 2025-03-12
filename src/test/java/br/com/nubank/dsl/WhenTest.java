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
        BuyOperation operation = When.trade(trade).isBuyOperation();
        assertTrue(operation.isProcessOperation());
    }

    @Test
    public void testIsBuyOperationWhenTradeIsSell() {
        Trade trade = new Trade(OperationType.SELL, 10.0, 1000);
        BuyOperation operation = When.trade(trade).isBuyOperation();
        assertFalse(operation.isProcessOperation());
    }

    @Test
    public void testIsSellOperationWhenTradeIsSell() {
        Trade trade = new Trade(OperationType.SELL, 10.0, 1000);
        SellOperation operation = When.trade(trade).isSellOperation();
        assertTrue(operation.isProcessOperation());
    }

    @Test
    public void testIsSellOperationWhenTradeIsBuy() {
        Trade trade = new Trade(OperationType.BUY, 10.0, 1000);
        SellOperation operation = When.trade(trade).isSellOperation();
        assertFalse(operation.isProcessOperation());
    }
}
