package br.com.nubank.dsl;

import br.com.nubank.model.OperationType;
import br.com.nubank.model.Trade;
import br.com.nubank.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyOperationTest {

    private Trade trade;
    private Wallet wallet;

    @BeforeEach
    public void setup() {
        trade = new Trade(OperationType.BUY, 10.0, 1000);
        wallet = new Wallet();
    }

    @Test
    public void testAddTradeToWalletWhenRunOperationIsTrue() {
        OperationData operationData = new OperationData();
        operationData.withRunOperation(true).withTrade(trade).withWallet(wallet);

        BuyOperation operation = new BuyOperation(operationData);
        operation.addTradeToWallet();
        assertEquals(1000, wallet.getQuantity());
    }

    @Test
    public void testAddTradeToWalletWhenRunOperationIsFalse() {
        OperationData operationData = new OperationData();
        operationData.withRunOperation(false).withTrade(trade).withWallet(wallet);

        BuyOperation operation = new BuyOperation(operationData);
        operation.addTradeToWallet();
        assertEquals(0, wallet.getQuantity());
    }
}
