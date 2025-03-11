package br.com.nubank.model;

import com.google.gson.annotations.SerializedName;

public class Trade {

    @SerializedName("operation")
    private OperationType operationType;

    @SerializedName("unit-cost")
    private double unitCost;

    private int quantity;

    public Trade(OperationType operationType, double unitCost, int quantity) {
        this.operationType = operationType;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public double getTotal() {
        return unitCost * quantity;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "operationType='" + operationType + '\'' +
                ", unitCost='" + unitCost + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
