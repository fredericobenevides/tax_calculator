package br.com.nubank.model;

public class Tax {

    private double tax;

    public Tax(double tax) {
        this.tax = tax;
    }

    public static Tax emptyTax() {
        return new Tax(0.0);
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
