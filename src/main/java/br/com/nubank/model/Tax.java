package br.com.nubank.model;

public class Tax {

    private static final double LIMIT_FREE_TAX = 2000;
    private static final double PERCENTAGE = 0.20;
    private double tax;

    public void addProfit(double profit, Trade trade) {
        double total = trade.getUnitCost() * trade.getQuantity();

        if (total < LIMIT_FREE_TAX) {
            this.tax = 0.0;
        } else {
            this.tax = profit * PERCENTAGE;
        }
    }

    public double getTax() {
        return tax;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "tax=" + tax +
                '}';
    }
}
