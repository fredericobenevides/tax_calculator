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
    public void testAddTradeToWalletWhenProcessOperationIsTrue() {
        BuyOperation operation = new BuyOperation(true, trade, wallet);
        operation.addTradeToWallet();
        assertEquals(1000, wallet.getQuantity());
    }

    @Test
    public void testAddTradeToWalletWhenProcessOperationIsFalse() {
        BuyOperation operation = new BuyOperation(false, trade, wallet);
        operation.addTradeToWallet();
        assertEquals(0, wallet.getQuantity());
    }
}
