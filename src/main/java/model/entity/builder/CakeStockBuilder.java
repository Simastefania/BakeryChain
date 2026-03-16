package model.entity.builder;

import model.entity.CakeStock;

import java.time.LocalDate;

public class CakeStockBuilder {

    private CakeStock cakeStock;

    public CakeStockBuilder() {
        cakeStock = new CakeStock();
    }

    public CakeStockBuilder setId(int id) {
        cakeStock.setId(id);
        return this;
    }

    public CakeStockBuilder setIdCake(int idCake) {
        cakeStock.setId_cake(idCake);
        return this;
    }

    public CakeStockBuilder setIdBakery(int idBakery) {
        cakeStock.setId_bakery(idBakery);
        return this;
    }

    public CakeStockBuilder setQuantity(int quantity) {
        cakeStock.setQuantity(quantity);
        return this;
    }

    public CakeStockBuilder setExpiryDate(LocalDate expiryDate) {
        cakeStock.setExpiryDate(expiryDate);
        return this;
    }

    public CakeStockBuilder setAvailable(boolean available) {
        cakeStock.setAvailable(available);
        return this;
    }

    public CakeStock build() {
        return cakeStock;
    }
}