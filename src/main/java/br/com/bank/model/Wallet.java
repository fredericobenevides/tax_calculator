package br.com.bank.model;

public class Wallet {

    private double averageCost;
    private int quantity;
    private double totalCapitalLoss;

    public void addInvestment(double unitCost, int quantity) {
        this.averageCost = (this.averageCost * this.quantity + unitCost * quantity) / (this.quantity + quantity);
        this.quantity += quantity;
    }

    public void removeInvestment(int quantity) {
        this.quantity -= quantity;

        if (this.quantity == 0) {
            this.averageCost = 0;
        }
    }

    public void increaseCapitalLoss(double loss) {
        this.totalCapitalLoss += loss;
    }

    public void decreaseCapitalLoss(double loss) {
        if (this.totalCapitalLoss > 0) {
            this.totalCapitalLoss -= Math.abs(loss);
        }
    }

    public double getOperationCost() {
        return averageCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalCapitalLoss() {
        return totalCapitalLoss;
    }
}
