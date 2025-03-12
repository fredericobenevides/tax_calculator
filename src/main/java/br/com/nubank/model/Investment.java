package br.com.nubank.model;

public class Investment {

    private double averagePurchasedCost;
    private int quantity;
    private double totalCapitalLoss;

    public void addInvestment(double unitCost, int quantity) {
        this.averagePurchasedCost = (this.averagePurchasedCost * this.quantity + unitCost * quantity) / (this.quantity + quantity);
        this.quantity += quantity;
    }

    public void removeInvestment(int quantity) {
        this.quantity -= quantity;

        if (this.quantity == 0) {
            this.averagePurchasedCost = 0;
        }
    }

    public void increaseCapitalLoss(double loss) {
        this.totalCapitalLoss += Math.abs(loss);
    }

    public void decreaseCapitalLoss(double loss) {
        if (this.totalCapitalLoss > 0) {
            this.totalCapitalLoss -= Math.abs(loss);
        }
    }

    public double getPurchasedCost() {
        return averagePurchasedCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalCapitalLoss() {
        return totalCapitalLoss;
    }
}
