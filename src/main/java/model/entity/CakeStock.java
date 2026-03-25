package model.entity;

import java.time.LocalDate;

public class CakeStock {
    private int id;
    private int cakeId;
    private int bakeryId;
    private int quantity;
    private LocalDate expiryDate;
    private boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setCakeId(int cakeId) {
        this.cakeId = cakeId;
    }

    public int getBakeryId() {
        return bakeryId;
    }

    public void setBakeryId(int bakeryId) {
        this.bakeryId = bakeryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}