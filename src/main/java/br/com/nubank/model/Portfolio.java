package br.com.nubank.model;

public class Portfolio {

    private double averagePurchasePrice;

    private int quantity;

    private double totalCapitalLoss;

    public void addInvestment(Trade trade) {
        this.averagePurchasePrice = (this.averagePurchasePrice * this.quantity + trade.getUnitCost() * trade.getQuantity()) / (this.quantity + trade.getQuantity());
        this.quantity += trade.getQuantity();
    }

    public void removeInvestment(Trade trade) {
        this.quantity -= trade.getQuantity();
    }

    public void increaseCapitalLoss(double loss) {
        this.totalCapitalLoss += loss;
    }

    public void decreaseCapitalLoss(double loss) {
        this.totalCapitalLoss -= loss;
    }

    public double getAveragePurchasePrice() {
        return averagePurchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalCapitalLoss() {
        return totalCapitalLoss;
    }
}
