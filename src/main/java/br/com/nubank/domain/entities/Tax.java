package br.com.nubank.domain.entities;

public class Tax {

    private double value;

    public Tax() {}

    public Tax(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "value=" + value +
                '}';
    }
}
