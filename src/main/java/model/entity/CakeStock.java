package model.entity;

import java.time.LocalDate;

public class CakeStock {
    private int id;
    private int id_cake;
    private int id_bakery;
    private int quantity;
    private LocalDate expiryDate;
    private boolean available;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cake() {
        return id_cake;
    }

    public void setId_cake(int id_cake) {
        this.id_cake = id_cake;
    }

    public int getId_bakery() {
        return id_bakery;
    }

    public void setId_bakery(int id_bakery) {
        this.id_bakery = id_bakery;
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
